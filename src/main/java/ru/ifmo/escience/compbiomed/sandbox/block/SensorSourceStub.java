package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.TargetedPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.sensor.BasicSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class SensorSourceStub extends AbstractPedSource<BasicSensorStub> {

    public SensorSourceStub(final Simulation simulation) {
        super(simulation);
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
                            System.out.println("Detected " + ped.toString());
                        }
                    }
                }
                makePoll(simulation, sensor, time + 1e-3);
            }});
    }

    @Override
    public void inject(int num) {
        final Simulation simulation = getSimulation();
        final List<BasicSensorStub> sensors = peds();
        simulation.addInitEvent(() -> {
            for (int i = 0; i < num; ++i) {
                final BasicSensorStub sensor = new BasicSensorStub(
                        Location.byCoordinates(40.0, 50.0), i
                );
                sensor.onCreate();
                sensors.add(sensor);
                makePoll(simulation, sensor, simulation.getTime());
            }
        });
    }

}
