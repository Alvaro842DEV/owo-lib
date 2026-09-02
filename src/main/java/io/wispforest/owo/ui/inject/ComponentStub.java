package io.wispforest.owo.ui.inject;

import io.wispforest.owo.ui.component.VanillaWidgetComponent;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.event.*;
import io.wispforest.owo.ui.util.FocusHandler;
import io.wispforest.owo.util.EventSource;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Stub-version of component which adds implementations for all methods
 * that unconditionally throw - used for interface-injecting onto
 * vanilla widgets
 */
public interface ComponentStub extends Component {

    @Override
    default void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default @Nullable ParentComponent parent() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default @Nullable FocusHandler focusHandler() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component positioning(Positioning positioning) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default AnimatableProperty<Positioning> positioning() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component margins(Insets margins) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default AnimatableProperty<Insets> margins() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component horizontalSizing(Sizing horizontalSizing) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component verticalSizing(Sizing verticalSizing) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default AnimatableProperty<Sizing> horizontalSizing() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default AnimatableProperty<Sizing> verticalSizing() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<MouseEnter> mouseEnter() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<MouseLeave> mouseLeave() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default CursorStyle cursorStyle() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component cursorStyle(CursorStyle style) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default Component tooltip(List<TooltipComponent> tooltip) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default List<TooltipComponent> tooltip() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void inflate(Size space) {
        this.widgetWrapper().inflate(space);
    }

    @Override
    default void mount(ParentComponent parent, int x, int y) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void dismount(DismountReason reason) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default <C extends Component> C configure(Consumer<C> closure) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default int width() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default int height() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onMouseDown(Click click, boolean doubled) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<MouseDown> mouseDown() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onMouseUp(Click click) {
        return this.widgetWrapper().onMouseUp(click);
    }

    @Override
    default EventSource<MouseUp> mouseUp() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onMouseScroll(double mouseX, double mouseY, double amount) {
        return this.widgetWrapper().onMouseScroll(mouseX, mouseY, amount);
    }

    @Override
    default EventSource<MouseScroll> mouseScroll() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onMouseDrag(Click click, double deltaX, double deltaY) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<MouseDrag> mouseDrag() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onKeyPress(KeyInput input) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<KeyPress> keyPress() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default boolean onCharTyped(CharInput input) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<CharTyped> charTyped() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void onFocusGained(FocusSource source) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<FocusGained> focusGained() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void onFocusLost() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default EventSource<FocusLost> focusLost() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default int x() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void updateX(int x) {
        this.widgetWrapper().updateX(x);
    }

    @Override
    default int y() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void updateY(int y) {
        this.widgetWrapper().updateY(y);
    }

    @Override
    default Component id(@Nullable String id) {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default @Nullable String id() {
        throw new IllegalStateException("Interface stub method called");
    }

    default VanillaWidgetComponent widgetWrapper() {
        throw new IllegalStateException("Interface stub method called");
    }

    default int xOffset() {
        throw new IllegalStateException("Interface stub method called");
    }

    default int yOffset() {
        throw new IllegalStateException("Interface stub method called");
    }

    default int widthOffset() {
        throw new IllegalStateException("Interface stub method called");
    }

    default int heightOffset() {
        throw new IllegalStateException("Interface stub method called");
    }

    @Override
    default void update(float delta, int mouseX, int mouseY) {
        this.widgetWrapper().update(delta, mouseX, mouseY);
    }

    @Override
    default void parseProperties(io.wispforest.owo.ui.parsing.UIModel model, Element element, Map<String, Element> children) {
        Component.super.parseProperties(model, element, children);

        if (this instanceof ClickableWidget widget) {
            io.wispforest.owo.ui.parsing.UIParsing.apply(children, "active", io.wispforest.owo.ui.parsing.UIParsing::parseBool, active -> widget.active = active);
        }
    }
}
