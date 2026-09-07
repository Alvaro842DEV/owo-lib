package io.wispforest.owo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class EventStreamRegressionTest {

    public static void main(String[] args) {
        var calls = new ArrayList<String>();
        var event = new EventStream<Runnable>(callbacks -> () -> callbacks.forEach(Runnable::run));
        var registered = new AtomicBoolean();
        event.register(() -> {
            calls.add("first");
            if (registered.compareAndSet(false, true)) event.register(() -> calls.add("late"));
        });
        var second = event.source().subscribe(() -> calls.add("second"));
        event.register(second::cancel);

        var originalInvoker = event.invoker();
        originalInvoker.run();
        expect(calls, List.of("first", "second"));

        calls.clear();
        event.invoker().run();
        expect(calls, List.of("first", "late"));

        calls.clear();
        originalInvoker.run();
        expect(calls, List.of("first", "second"));

        calls.clear();
        var removalEvent = new EventStream<Runnable>(callbacks -> () -> callbacks.forEach(Runnable::run));
        var cancellation = new Runnable[1];
        removalEvent.register(() -> cancellation[0].run());
        var pending = removalEvent.source().subscribe(() -> calls.add("pending"));
        cancellation[0] = pending::cancel;
        removalEvent.invoker().run();
        expect(calls, List.of("pending"));
        calls.clear();
        removalEvent.invoker().run();
        expect(calls, List.of());

        System.out.println("EventStream regression checks passed");
    }

    private static void expect(List<String> actual, List<String> expected) {
        if (!actual.equals(expected)) throw new AssertionError("Expected " + expected + ", got " + actual);
    }
}
