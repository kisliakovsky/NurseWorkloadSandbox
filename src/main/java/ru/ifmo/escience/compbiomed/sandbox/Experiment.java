package ru.ifmo.escience.compbiomed.sandbox;

import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseParticleSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.block.SensorSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;
import java.util.List;


public class Experiment {

    // These variables correspond to Main variables in the AnyLogic project
    public static final List<Attractor> PATIENT_ROOMS_FLOOR_7 = new ArrayList<>();

    static {
        for (int i = 0; i < 17; ++i) {
            PATIENT_ROOMS_FLOOR_7.add(new Attractor(i, i));
        }
    }

    public static void main(String[] args) {
        final Simulation simulation = new Simulation(25.0, 250.0);
        final PedSource<? extends RealCareParticipant> nurseParticleSource = new NurseParticleSourceStub(simulation);
        final PedSource<? extends StaticPedestrian> sensorSource =
                new SensorSourceStub(simulation, new ArrayList<Location>() {{
                    add(Location.byCoordinates(40.0, 30.0));
                    add(Location.byCoordinates(40.0, 50.0));
                    add(Location.byCoordinates(70.0, 70.0));
                }});
        simulation.addSource(nurseParticleSource);
        simulation.addSource(sensorSource);
        nurseParticleSource.inject(1);
        sensorSource.inject(3);
        simulation.run();
    }

}
