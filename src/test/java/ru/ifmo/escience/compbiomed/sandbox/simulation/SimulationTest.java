package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.block.NurseSource;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;

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
        final NurseSource source = new NurseSource(simulation);
        simulation.addBlock(source);
        source.inject(1);
        simulation.run();
    }

}