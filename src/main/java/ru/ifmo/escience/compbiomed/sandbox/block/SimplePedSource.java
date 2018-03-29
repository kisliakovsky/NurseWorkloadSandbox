package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;
import java.util.List;

class SimplePedSource<T extends Pedestrian> extends AbstractPedSource<T> {

    private Class<T> classInstance;

    SimplePedSource(final Class<T> classInstance, final Simulation simulation) {
        super(simulation);
        this.classInstance = classInstance;
    }

    @Override
    public void inject(final int num) {
        final Simulation simulation = getSimulation();
        final List<T> peds = peds();
        simulation.addFirst(() -> {
            try {
                for (int i = 0; i < num; ++i) {
                    final T ped = classInstance.getConstructor(long.class).newInstance(i);
                    ped.onCreate();
                    peds.add(ped);
                }
            } catch (final ReflectiveOperationException e) {
                System.err.println("Inject failed");
            }
        });
    }

}
