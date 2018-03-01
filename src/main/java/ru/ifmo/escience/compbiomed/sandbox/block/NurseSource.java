package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

public class NurseSource extends PedSource<Nurse> {

    public NurseSource(final Simulation simulation) {
        super(Nurse.class, simulation);
    }

}
