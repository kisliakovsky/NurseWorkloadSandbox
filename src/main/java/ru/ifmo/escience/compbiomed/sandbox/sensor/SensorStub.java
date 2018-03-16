package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.DestinedPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class SensorStub extends StaticPedestrian {

    private Pedestrian ped;
    private double detectionRadius;

    public SensorStub(final Attractor attractor, final Pedestrian ped, final double detectionRadius) {
        super(new Location(attractor.getX(), attractor.getY()));
        this.ped = ped;
        this.detectionRadius = detectionRadius;
    }

    public boolean check() {
        final double distance = ped.distanceTo(this);
        return distance < detectionRadius;
    }

}
