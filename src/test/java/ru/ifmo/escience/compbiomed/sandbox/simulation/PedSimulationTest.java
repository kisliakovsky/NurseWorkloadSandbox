package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseSourceStub;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PedSimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation(25.0);
    }

    @Test
    public void checkPedestrianEvents() {
        final List<Function<Simulation, PedSource<? extends CareParticipant>>> sourceFactories = new ArrayList<>();
        sourceFactories.add(NurseSourceStub::new);
        sourceFactories.stream().map((sourceFactory) -> {
            final PedSource<? extends CareParticipant> source = sourceFactory.apply(simulation);
            simulation.addSource(source);
            return source;
        }).forEach(source -> source.inject(1));
        simulation.run();
    }

}