package ru.ifmo.escience.compbiomed.sandbox.simulation;

import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;


public class Simulation {

    private Queue<Event> eventQueue = new Queue<>();
    private long startTime;
    private double acceleration;

    public Simulation(double acceleration) {
        this.acceleration = acceleration;
    }

    public Simulation() {
        this(1.0);
    }

    private double time() {
        return acceleration * (System.nanoTime() - startTime);
    }

    public void run() {
        System.out.println("Simulation started");
        startTime = System.nanoTime();
        while (!eventQueue.isEmpty()) {
            final Event nextEvent = eventQueue.poll();
            while (nextEvent.getTimeNano() > time()) {
                continue;
            }
            nextEvent.execute();
        }
        System.out.println("Simulation finished");
    }

    public void addEvent(final Event event) {
        eventQueue.offer(event);
    }

}
