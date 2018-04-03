package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Particle;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;
import java.util.Map;

class ParticleSourceStub extends AbstractPedSource<Particle> {

    private final RealCareParticipant careParticipant;

    private static void moveParticles(final Simulation simulation, final List<Particle> particles) {
        for (final Particle particle: particles) {
            simulation.addEvent(new AbstractEvent(1e-3) {
                @Override
                public void execute() {
                    particle.move(1e-3);
                }
            });
        }
    }

    public ParticleSourceStub(final Simulation simulation, final RealCareParticipant careParticipant) {
        super(simulation);
        this.careParticipant = careParticipant;
    }

    @Override
    public void inject(final int num) {
        final Simulation simulation = getSimulation();
        final Map<? super RealCareParticipant, List<Particle>> pseudoObservablesByObservables = simulation.getPseudoObservables();
        final List<Particle> newParticles = Particle.createParticles(careParticipant, num);
        pseudoObservables.addAll(newParticles);
        simulation.updatePeds();
        moveParticles(simulation, particles);
    }
}

public class NurseParticleSourceStub extends AbstractPedSource<Nurse> {

    public NurseParticleSourceStub(final Simulation simulation) {
        super(simulation);
    }

    @Override
    public void inject(final int num) {
        final Simulation simulation = getSimulation();
        final List<Nurse> nurses = peds();
        final List<? super RealCareParticipant> observables = simulation.getObservables();
        simulation.addInitEvent(() -> {
           for (int i = 0; i < num; ++i) {
               final Nurse nurse = new Nurse(
                       Location.byCoordinates(20.0, 20.0),
                       Location.byCoordinates(90.0, 90.0),
                       i
               );
               nurse.onCreate();
               nurses.add(nurse);
               observables.add(nurse);
               simulation.updatePeds();
               final PedSource<? extends Particle> particleSource = new ParticleSourceStub(simulation, nurse);
               simulation.addSource(particleSource);
               particleSource.inject(50);
           }
        });
    }

}
