package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class BasicSensorStub extends StaticPedestrian implements Sensor {

    private long index;
    private double detectionRadius;

    public BasicSensorStub(final Location location, final long index) {
        super(location);
        this.index = index;
        this.detectionRadius = 10.0;
    }


    @Override
    public boolean check() {
        return false;
    }

    @Override
    public String toString() {
        return "sensor_" + index;
    }
}
