package ru.ifmo.escience.compbiomed.sandbox.simulation;

import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;


// TODO: Check if Ivanov's eTime means the same.
public class Simulation {

    private Queue<Event> eventQueue = new Queue<>();
    private double time;

    public void run(final double finishTime) {
        System.out.println("Simulation started");
        while (!eventQueue.isEmpty() && time < finishTime) {
            final Event nextEvent = eventQueue.poll();
            if (nextEvent.eTime() <= finishTime) {
                nextEvent.execute();
                time = nextEvent.eTime();
            }
        }
        System.out.println("Simulation finished");
    }

    public void run() {
        run(Double.MAX_VALUE);
    }

    public void addEvent(final Event event) {
        eventQueue.offer(event);
    }

}
