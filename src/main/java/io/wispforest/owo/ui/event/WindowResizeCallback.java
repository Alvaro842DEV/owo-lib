package io.wispforest.owo.ui.event;

import io.wispforest.owo.util.EventStream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public interface WindowResizeCallback {

    EventStream<WindowResizeCallback> EVENT = new EventStream<>(callbacks -> (client, window) -> {
        for (var callback : callbacks) {
            callback.onResized(client, window);
        }
    });

    /**
     * Called after the client's window has been resized
     *
     * @param client The currently active client
     * @param window The window which was resized
     */
    void onResized(MinecraftClient client, Window window);

}
