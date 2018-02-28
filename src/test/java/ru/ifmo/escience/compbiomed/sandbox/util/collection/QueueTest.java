package ru.ifmo.escience.compbiomed.sandbox.util.collection;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Event;
import ru.ifmo.escience.compbiomed.sandbox.simulation.SimpleEvent;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class QueueTest {

    private Queue<Event> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void checkPushAndPop() {
        final double[] times = {1.1, 4.1, 8.3, 5.0, 11.2};
        queue.offer(new SimpleEvent(1.1));
        queue.offer(new SimpleEvent(4.1));
        queue.offer(new SimpleEvent(8.3));
        queue.offer(new SimpleEvent(5.0));
        queue.offer(new SimpleEvent(11.2));
        final double[] orderedTimes = times.clone();
        Arrays.sort(orderedTimes);
        final int size = queue.size();
        for (int i = 0; i < size; ++i) {
            final Event event = queue.poll();
            assertThat(event.eTime(), closeTo(orderedTimes[i], 0.001));
        }
    }
}