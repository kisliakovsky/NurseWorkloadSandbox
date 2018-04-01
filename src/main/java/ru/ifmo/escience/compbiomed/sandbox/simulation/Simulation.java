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
    private List<? super Pedestrian> peds = new LinkedList<>();
    private Queue<Event> initQueue = new Queue<>();
    private Queue<Event> eventQueue = new Queue<>();
    private long startTime;
    private long finishTime;
    private double acceleration;
    private boolean finished = false;


    public Simulation(double acceleration) {
        this.acceleration = acceleration;
    }

    public Simulation() {
        this(1.0);
    }

    public double getTime() {
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
        sources.forEach(source -> source.peds().forEach(Agent::onStartup));
        System.out.println("Simulation started");
        startTime = System.nanoTime();
        while (!eventQueue.isEmpty()) {
            final Event nextEvent = eventQueue.poll();
            while (nextEvent.getTimeNano() > getTime()) {
                continue;
            }
            nextEvent.execute();
        }
        finished = true;
        finishTime = System.nanoTime();
        System.out.println("Simulation finished");
    }

    public void addEvent(final Event event) {
        eventQueue.offer(event);
    }

    public void addInitEvent(final Action action) {
        initQueue.offer(new AbstractEvent() {
            @Override
            public void execute() {
                action.perform();
            }
        });
    }

    public void addSource(final PedSource<? extends Pedestrian> source) {
        sources.add(source);
    }

    public void updatePeds() {
        peds = new LinkedList<>();
        for (final PedSource<? extends Pedestrian> pedSource: sources) {
            peds.addAll(pedSource.peds());
        }
    }

    public List<? super Pedestrian> getPeds() {
        return peds;
    }

    public boolean isFinished() {
        return finished;
    }
}
