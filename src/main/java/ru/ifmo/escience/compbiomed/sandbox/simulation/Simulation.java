package ru.ifmo.escience.compbiomed.sandbox.simulation;

import ru.ifmo.escience.compbiomed.sandbox.agent.Agent;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Particle;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.sensor.AdaptedSensor;
import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;
import ru.ifmo.escience.compbiomed.sandbox.util.function.Action;

import java.util.*;


public class Simulation {

    private List<PedSource<? extends Pedestrian>> sources = new ArrayList<>();
    private List<? super RealCareParticipant> observables = new ArrayList<>();
    private Map<? super RealCareParticipant, List<Particle>> pseudoObservables = new HashMap<>();
    private List<? super AdaptedSensor> observers = new ArrayList<>();
    private Queue<Event> initQueue = new Queue<>();
    private Queue<Event> eventQueue = new Queue<>();
    private long startTime;
    private long finishTime;
    private double acceleration;
    private double timeout;
    private boolean finished = false;


    public Simulation(final double acceleration, final double timeout) {
        this.acceleration = acceleration;
        this.timeout = timeout;
    }

    public Simulation(final double acceleration) {
        this(acceleration, 0.0);
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

    private boolean isTimeoutOver() {
        final double nanoTimeout = timeout * 1e9;
        return Double.compare(nanoTimeout, 0.0) != 0 && Double.compare(nanoTimeout, getTime()) < 0;
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
        while (!eventQueue.isEmpty() && !isTimeoutOver()) {
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

    public boolean isFinished() {
        return finished;
    }

    public List<? super RealCareParticipant> getObservables() {
        return observables;
    }

    public Map<? super RealCareParticipant, List<Particle>> getPseudoObservables() {
        return pseudoObservables;
    }

    public List<? super AdaptedSensor> getObservers() {
        return observers;
    }
}
