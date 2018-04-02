package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.TargetedPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.sensor.BasicSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class SensorSourceStub extends AbstractPedSource<BasicSensorStub> {

    private final List<Location> locations;

    public SensorSourceStub(final Simulation simulation,
                            final List<Location> locations) {
        super(simulation);
        this.locations = locations;
    }

    private static void makePoll(final Simulation simulation,
                                 final BasicSensorStub sensor,
                                 final double time) {
        simulation.addEvent(new AbstractEvent(time) {
            @Override
            public void execute() {
                final List<? super Pedestrian> peds = simulation.getPeds();
                for (final Object ped: peds) {
                    if (ped instanceof TargetedPedestrian) {
                        if (sensor.check((Pedestrian) ped)) {
                            System.out.println("Detected " + ped.toString() + " by " + sensor.toString());
                        }
                    }
                }
                makePoll(simulation, sensor, time + 1e-3);
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
                    makePoll(simulation, sensor, simulation.getTime());
                } else {
                    throw new IllegalArgumentException(
                            "There are not enough locations"
                    );
                }
            }
        });
    }

}
