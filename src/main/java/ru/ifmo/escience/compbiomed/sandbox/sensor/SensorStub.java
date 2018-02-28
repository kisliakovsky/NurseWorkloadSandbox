package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Agent;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;

public class SensorStub extends Agent implements Pedestrian {

    private Agent agent;
    private double detectionRadius;

    public SensorStub(final Attractor attractor, final Agent agent, final double detectionRadius) {
        super(attractor.getX(), attractor.getY());
        this.agent = agent;
        this.detectionRadius = detectionRadius;
    }

    public boolean check() {
        final double distance = agent.distanceTo(this);
        return distance < detectionRadius;
    }

}
