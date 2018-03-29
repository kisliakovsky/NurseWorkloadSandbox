package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation(25.0);
    }

    @Test
    public void checkRun() {
        simulation.addEvent(new SimpleEvent(1.1));
        simulation.addEvent(new SimpleEvent(4.1));
        simulation.addEvent(new SimpleEvent(8.3));
        simulation.addEvent(new SimpleEvent(5.0));
        simulation.addEvent(new SimpleEvent(11.2));
        simulation.run();
    }

    @Test
    public void checkSource() {
        final List<Function<Simulation, PedSource<? extends CareParticipant>>> sourceFactories = new ArrayList<>();
        sourceFactories.add(PedSource::createNurseSource);
        sourceFactories.add(PedSource::createPatientSource);
        sourceFactories.stream().map((sourceFactory) -> {
            final PedSource<? extends CareParticipant> source = sourceFactory.apply(simulation);
            simulation.addSource(source);
            return source;
        }).forEach(source -> source.inject(1));
        simulation.run();
    }

}