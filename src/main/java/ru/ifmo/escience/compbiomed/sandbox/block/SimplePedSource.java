package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

class SimplePedSource<T extends Pedestrian> implements PedSource<T> {

    private Class<T> classInstance;
    private Simulation simulation;
    private List<T> peds = new ArrayList<>();

    SimplePedSource(final Class<T> classInstance, final Simulation simulation) {
        this.classInstance = classInstance;
        this.simulation = simulation;
    }

    @Override
    public List<T> peds() {
        return peds;
    }

    @Override
    public void inject(final int num) {
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
