package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.SimplePedestrian;

public class SensorStub extends SimplePedestrian {

    private Pedestrian ped;
    private double detectionRadius;

    public SensorStub(final Attractor attractor, final Pedestrian ped, final double detectionRadius) {
        super(attractor.getX(), attractor.getY());
        this.ped = ped;
        this.detectionRadius = detectionRadius;
    }

    public boolean check() {
        final double distance = ped.distanceTo(this);
        return distance < detectionRadius;
    }

}
