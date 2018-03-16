package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class NurseSourceStub extends AbstractPedSource<Nurse> {

    public NurseSourceStub(final Simulation simulation) {
        super(simulation);
    }

    @Override
    public void inject(int num) {
        final Simulation simulation = getSimulation();
        final List<Nurse> nurses = peds();
        simulation.addFirst(() -> {
            for (int i = 0; i < num; ++i) {
                final Nurse nurse = new Nurse(
                        Location.byCoordinates(20.0, 20.0),
                        Location.byCoordinates(90.0, 90.0),
                        i
                );
                nurse.onCreate();
                nurses.add(nurse);
            }
        });
    }

}
