package ru.ifmo.escience.compbiomed.sandbox.simulation;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.util.collection.Queue;

import static org.junit.Assert.*;

public class SimulationTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        simulation = new Simulation();
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
}