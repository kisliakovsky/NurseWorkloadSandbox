package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class SimplestSensorStub extends StaticPedestrian implements Sensor {

    private double detectionRadius;

    public SimplestSensorStub(final Location location,
                              final double detectionRadius) {
        super(location);
        this.detectionRadius = detectionRadius;
    }

    @Override
    public boolean check(final Pedestrian ped) {
        final double distance = ped.distanceTo(this);
        return distance < detectionRadius;
    }

}
