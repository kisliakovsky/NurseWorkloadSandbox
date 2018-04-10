package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class AdaptedSensor extends StaticPedestrian implements Sensor {

    private long index;
    private double detectionRadius;

    public AdaptedSensor(final Location location, final long index) {
        super(location);
        this.index = index;
        this.detectionRadius = 10.0;
    }

    @Override
    public boolean check(final Pedestrian ped) {
        return Double.compare(distanceTo(ped), detectionRadius) < 0;
    }

    @Override
    public String toString() {
        return "sensor_" + index;
    }
}
