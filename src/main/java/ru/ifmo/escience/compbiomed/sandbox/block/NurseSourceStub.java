package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class NurseSourceStub extends AbstractPedSource<Nurse> {

    public NurseSourceStub(final Simulation simulation) {
        super(simulation);
    }

    private static void moveNurse(final Simulation simulation, final Nurse nurse) {
        final double estimatedTime = nurse.getDisplacement() / nurse.getSpeed();
        final long numberOfSteps = (long) Math.ceil(estimatedTime / 1e-3);
        for (int i = 0; i < numberOfSteps; ++i) {
            if (!nurse.isArrived()) {
                simulation.addEvent(new AbstractEvent(i * 1e-3) {
                    @Override
                    public void execute() {
                        nurse.move(1e-3);
                    }
                });
            } else {
                break;
            }
        }
    }

    @Override
    public void inject(int num) {
        final Simulation simulation = getSimulation();
        final List<Nurse> nurses = peds();
        simulation.addInitEvent(() -> {
            for (int i = 0; i < num; ++i) {
                final Nurse nurse = new Nurse(
                        Location.byCoordinates(20.0, 20.0),
                        Location.byCoordinates(90.0, 90.0),
                        i
                );
                nurse.onCreate();
                nurses.add(nurse);
                simulation.updatePeds();
                moveNurse(simulation, nurse);
            }
        });
    }

}
