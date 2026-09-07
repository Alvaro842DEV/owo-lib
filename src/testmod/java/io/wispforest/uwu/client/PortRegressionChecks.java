package io.wispforest.uwu.client;

import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.core.OwoUIDrawContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

import java.net.URI;
import java.nio.file.Files;
import java.util.List;

public final class PortRegressionChecks {
    private static boolean ran;

    public static void install() {
        if (!Boolean.getBoolean("owo.portRegressionChecks")) return;
        NeoForge.EVENT_BUS.addListener(ClientTickEvent.Post.class, event -> {
            var client = MinecraftClient.getInstance();
            if (ran || !(client.currentScreen instanceof TitleScreen) || client.getOverlay() != null) return;
            ran = true;
            try {
                checkMenuLinks(client);
                checkFractionalGlyphWidths(client);
                Files.writeString(client.runDirectory.toPath().resolve("port-regression-checks.txt"), "PASS: menu links, return screen, player guard, fractional width hit detection\n");
                client.scheduleStop();
            } catch (Throwable failure) {
                try {
                    Files.writeString(client.runDirectory.toPath().resolve("port-regression-checks.txt"), "FAIL: " + failure + "\n");
                } catch (Exception ignored) {}
                throw new AssertionError("Port regression checks failed", failure);
            }
        });
    }

    private static void checkMenuLinks(MinecraftClient client) {
        require(client.player == null, "Expected a title-menu test without a player");
        var source = client.currentScreen;
        var utility = OwoUIDrawContext.utilityScreen();
        boolean links = client.options.getChatLinks().getValue();
        boolean prompt = client.options.getChatLinksPrompt().getValue();
        try {
            client.options.getChatLinks().setValue(true);
            client.options.getChatLinksPrompt().setValue(true);
            utility.captureLinkSource();
            var link = Style.EMPTY.withClickEvent(new ClickEvent.OpenUrl(URI.create("https://example.invalid/owo-regression")));
            require(utility.handleTextClick(link), "Menu URL was not handled");
            utility.getAndClearLinkSource();
            require(client.currentScreen instanceof ConfirmLinkScreen, "Missing confirmation screen");
            client.currentScreen.keyPressed(new KeyInput(GLFW.GLFW_KEY_ESCAPE, 0, 0));
            require(client.currentScreen == source, "Cancelling the link did not restore the original screen");

            utility.captureLinkSource();
            require(!utility.handleTextClick(Style.EMPTY.withClickEvent(new ClickEvent.RunCommand("/help"))),
                    "Commands must be rejected without a player");
            require(client.currentScreen == source, "Rejected command changed the screen");
            client.options.getChatLinks().setValue(false);
            require(!utility.handleTextClick(link), "Disabled links should not be handled");
            require(client.currentScreen == source, "Disabled link opened a screen");
        } finally {
            utility.getAndClearLinkSource();
            client.options.getChatLinks().setValue(links);
            client.options.getChatLinksPrompt().setValue(prompt);
        }
    }

    private static void checkFractionalGlyphWidths(MinecraftClient client) {
        var font = new StyleSpriteSource.Font(Identifier.of("uwu", "port_regression"));
        var prefixStyle = Style.EMPTY.withFont(font);
        var linkStyle = prefixStyle.withClickEvent(new ClickEvent.RunCommand("/help"));
        var prefix = Text.literal("\uE000".repeat(4)).setStyle(prefixStyle);
        require(client.textRenderer.getTextHandler().getWidth(prefix) == 9f, "Fractional test font was not loaded");
        var label = new TestLabel(prefix.append(Text.literal("\uE001").setStyle(linkStyle)));
        require(label.hit(8).getClickEvent() == null, "Link begins before its rendered position");
        require(linkStyle.getClickEvent().equals(label.hit(9).getClickEvent()), "Link boundary rounded per glyph");
        require(linkStyle.getClickEvent().equals(label.hit(12).getClickEvent()), "Link ends before its rendered position");
        require(label.hit(13) == null, "Link extends past the rendered text");
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static final class TestLabel extends LabelComponent {
        private TestLabel(Text text) {
            super(text);
            this.wrappedText = List.of(text.asOrderedText());
        }

        private Style hit(int x) {
            return this.styleAt(x, 0);
        }
    }
}
