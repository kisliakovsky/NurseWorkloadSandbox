package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class PedSimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation(25.0);
    }

    @Test
    public void checkPedestrianEvents() {
        final PedSource<? extends CareParticipant> source = new NurseSourceStub(simulation);
        simulation.addSource(source);
        source.inject(1);
        simulation.run();
        final List<? extends CareParticipant> peds = source.peds();
        final CareParticipant careParticipant = peds.get(0);
        assertThat(careParticipant.getX(), closeTo(90.0, 1e-3));
        assertThat(careParticipant.getY(), closeTo(90.0, 1e-3));
    }

}