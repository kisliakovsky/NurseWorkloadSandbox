package ru.ifmo.escience.compbiomed.sandbox;

import ru.ifmo.escience.compbiomed.sandbox.block.NurseSource;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

public class Experiment {

    public static void main(String[] args) {
        final Simulation simulation = new Simulation(25.0);
        final NurseSource source = new NurseSource(simulation);
        simulation.addEvent(new AbstractEvent(1.1) {
            @Override
            public void execute() {
                source.inject(1);
            }
        });
        simulation.run();
    }

}
