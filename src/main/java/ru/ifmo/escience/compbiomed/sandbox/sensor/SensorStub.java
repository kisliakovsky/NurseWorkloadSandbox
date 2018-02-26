package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Agent;

public class SensorStub {

    private Attractor attractor;
    private Agent agent;
    private double detectionRadius;

    public SensorStub(Attractor attractor, Agent agent, double detectionRadius) {
        this.attractor = attractor;
        this.agent = agent;
        this.detectionRadius = detectionRadius;
    }

    public boolean check() {
        final double distance = this.agent.distanceTo(this.attractor.getX(), this.attractor.getY());
        return distance < this.detectionRadius;
    }

}
