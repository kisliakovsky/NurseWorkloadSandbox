package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.sensor.BasicSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.sensor.SensorVector;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.*;

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
                final Map<CareParticipant, List<BasicSensorStub>> pedsData = new HashMap<>();
                for (final Object ped : peds) {
                    if (ped instanceof CareParticipant) {
                        final List<BasicSensorStub> pedData = new LinkedList<>();
                        for (final BasicSensorStub sensor: sensors) {
                            if (sensor.check((Pedestrian) ped)) {
                                pedData.add(sensor);
                            } else {
                                pedData.add(null);
                            }
                        }
                        pedsData.put((CareParticipant) ped, pedData);
                    }
                }
                System.out.println(new SensorVector(pedsData));
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
