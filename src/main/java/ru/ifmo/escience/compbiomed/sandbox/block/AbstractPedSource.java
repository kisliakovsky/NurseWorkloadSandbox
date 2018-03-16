package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractPedSource<T extends Pedestrian> implements PedSource<T> {

    private Simulation simulation;
    private List<T> peds = new ArrayList<>();

    public AbstractPedSource(final Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public List<T> peds() {
        return peds;
    }

    @Override
    public Simulation getSimulation() {
        return simulation;
    }
}
