package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.TargetedPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.sensor.BasicSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.sensor.SensorVector;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SensorSourceStub extends AbstractPedSource<BasicSensorStub> {

    private final List<Location> locations;

    public SensorSourceStub(final Simulation simulation,
                            final List<Location> locations) {
        super(simulation);
        this.locations = locations;
    }

    private static void makePoll(final Simulation simulation,
                                 final List<BasicSensorStub> sensors,
                                 final double time) {
        simulation.addEvent(new AbstractEvent(time) {
            @Override
            public void execute() {
                final List<? super Pedestrian> peds = simulation.getPeds();
                final List<List<TargetedPedestrian>> sensorsData = new LinkedList<>();
                for (final BasicSensorStub sensor: sensors) {
                    final List<TargetedPedestrian> sensorData = new LinkedList<>();
                    for (final Object ped : peds) {
                        if (ped instanceof TargetedPedestrian) {
                            if (sensor.check((Pedestrian) ped)) {
                                sensorData.add((TargetedPedestrian) ped);
                            }
                        }
                    }
                    sensorsData.add(sensorData);
                }
                System.out.println(new SensorVector(sensorsData));
                makePoll(simulation, sensors, time + 1e-3);
            }});
    }

    @Override
    public void inject(final int num) {
        final Simulation simulation = getSimulation();
        final List<BasicSensorStub> sensors = peds();
        simulation.addInitEvent(() -> {
            for (int i = 0; i < num; ++i) {
                if (i < locations.size()) {
                    final Location location = locations.get(i);
                    final BasicSensorStub sensor = new BasicSensorStub(
                            location, i
                    );
                    sensor.onCreate();
                    sensors.add(sensor);
                    simulation.updatePeds();
                } else {
                    throw new IllegalArgumentException(
                            "There are not enough locations"
                    );
                }
            }
            makePoll(simulation, sensors, simulation.getTime());
        });
    }

}
