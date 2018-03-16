package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.sensor.BasicSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Sensor;
import ru.ifmo.escience.compbiomed.sandbox.sensor.SimplestSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class SensorSourceStub extends AbstractPedSource<BasicSensorStub> {

    public SensorSourceStub(final Simulation simulation) {
        super(simulation);
    }

    @Override
    public void inject(int num) {
        final Simulation simulation = getSimulation();
        final List<BasicSensorStub> sensors = peds();
        simulation.addFirst(() -> {
            for (int i = 0; i < num; ++i) {
                final BasicSensorStub sensor = new BasicSensorStub(Location.byCoordinates(40.0, 50.0), i);
                sensor.onCreate();
                sensors.add(sensor);
            }
        });
    }

}
