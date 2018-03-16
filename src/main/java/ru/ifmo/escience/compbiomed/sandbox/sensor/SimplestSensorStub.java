package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class SimplestSensorStub extends StaticPedestrian implements Sensor {

    private Pedestrian ped;
    private double detectionRadius;

    public SimplestSensorStub(final Attractor attractor, final Pedestrian ped, final double detectionRadius) {
        super(Location.byCoordinates(attractor.getX(), attractor.getY()));
        this.ped = ped;
        this.detectionRadius = detectionRadius;
    }

    @Override
    public boolean check() {
        final double distance = ped.distanceTo(this);
        return distance < detectionRadius;
    }

}
