package ru.ifmo.escience.compbiomed.sandbox.simulation;

import ru.ifmo.escience.compbiomed.sandbox.agent.Agent;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;
import ru.ifmo.escience.compbiomed.sandbox.util.function.Action;

import java.util.LinkedList;
import java.util.List;


public class Simulation {

    private List<PedSource<? extends Pedestrian>> sources = new LinkedList<>();
    private Queue<Event> initQueue = new Queue<>();
    private Queue<Event> eventQueue = new Queue<>();
    private long startTime;
    private double acceleration;

    public Simulation(double acceleration) {
        this.acceleration = acceleration;
    }

    public Simulation() {
        this(1.0);
    }

    public double getSimulationTime() {
        double time = 0.0;
        if (startTime != 0L) {
            time = acceleration * (System.nanoTime() - startTime);
        }
        return time;
    }

    private void runInit() {
        while (!initQueue.isEmpty()) {
            final Event nextEvent = initQueue.poll();
            nextEvent.execute();
        }
    }

    @SuppressWarnings("UnnecessaryContinue")
    public void run() {
        runInit();
        sources.forEach(source -> source.peds().forEach((Agent::onStartup)));
        System.out.println("Simulation started");
        startTime = System.nanoTime();
        while (!eventQueue.isEmpty()) {
            final Event nextEvent = eventQueue.poll();
            while (nextEvent.getTimeNano() > getSimulationTime()) {
                continue;
            }
            nextEvent.execute();
        }
        System.out.println("Simulation finished");
    }

    public void addEvent(final Event event) {
        eventQueue.offer(event);
    }

    private void addInitEvent(final Event event) {
        initQueue.offer(event);
    }

    public void addFirst(final Action action) {
        addInitEvent(new AbstractEvent() {
            @Override
            public void execute() {
                action.perform();
            }
        });
    }

    public void addSource(final PedSource<? extends Pedestrian> source) {
        sources.add(source);
    }

}
