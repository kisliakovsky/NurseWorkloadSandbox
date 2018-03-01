package ru.ifmo.escience.compbiomed.sandbox.simulation;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.block.PedBlock;
import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;
import ru.ifmo.escience.compbiomed.sandbox.util.function.Action;

import java.util.LinkedList;
import java.util.List;


public class Simulation {

    private List<PedBlock<? extends Pedestrian>> blocks = new LinkedList<>();
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

    private double time() {
        return acceleration * (System.nanoTime() - startTime);
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
        for (final PedBlock<? extends Pedestrian> block: blocks) {
            for (final Pedestrian ped: block.peds()) {
                ped.onStartup();
            }
        }
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

    public void addBlock(final PedBlock<? extends Pedestrian> block) {
        blocks.add(block);
    }

}
