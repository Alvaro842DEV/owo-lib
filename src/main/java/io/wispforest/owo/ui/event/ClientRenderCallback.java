package io.wispforest.owo.ui.event;

import io.wispforest.owo.util.EventStream;
import net.minecraft.client.MinecraftClient;

public interface ClientRenderCallback {

    /**
     * Invoked just before the client's window enters the 'Render' phase, after the client
     * has ticked and cleared the render task queue
     */
    EventStream<ClientRenderCallback> BEFORE = new EventStream<>(callbacks -> (client) -> {
        for (var callback : callbacks) {
            callback.onRender(client);
        }
    });

    /**
     * Called just after the client has finished rendering and drawing the
     * current frame and swapped buffers
     */
    EventStream<ClientRenderCallback> AFTER = new EventStream<>(callbacks -> (client) -> {
        for (var callback : callbacks) {
            callback.onRender(client);
        }
    });

    void onRender(MinecraftClient client);
}
