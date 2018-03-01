package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.SimplePedestrian;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PedSource<T extends SimplePedestrian> {

    private Class<T> classInstance;
    private List<T> peds = new ArrayList<>();

    public PedSource(Class<T> classInstance) {
        this.classInstance = classInstance;
    }

    public List<T> peds() {
        return peds;
    }

    public void inject(final int num) {
        try {
            for (int i = 0; i < num; ++i) {
                final T ped = classInstance.getConstructor(long.class).newInstance(i);
                ped.onCreate();
                peds.add(ped);
            }
        } catch (final ReflectiveOperationException e) {
            System.err.println("Inject failed");
        }

    }
}
