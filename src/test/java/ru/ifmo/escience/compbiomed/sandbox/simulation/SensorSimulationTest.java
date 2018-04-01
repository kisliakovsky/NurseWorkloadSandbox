package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.StaticPedestrian;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.block.SensorSourceStub;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class SensorSimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation(25.0);
    }

    @Test
    public void checkPedestrianEvents() {
        final PedSource<? extends CareParticipant> nurseSource = new NurseSourceStub(simulation);
        final PedSource<? extends StaticPedestrian> sensorSource = new SensorSourceStub(simulation);
        simulation.addSource(nurseSource);
        simulation.addSource(sensorSource);
        nurseSource.inject(1);
        sensorSource.inject(1);
        simulation.run();
    }

}