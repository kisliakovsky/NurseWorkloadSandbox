package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseParticleSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.block.SensorSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;

public class ParticleSimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation(25.0, 250.0);
    }

    @Test
    public void checkParticleEvents() {
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