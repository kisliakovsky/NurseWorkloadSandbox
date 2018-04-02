package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.assimilation.Particle;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public class ParticleSourceStub extends AbstractPedSource<Particle> {

    public ParticleSourceStub(final Simulation simulation) {
        super(simulation);
    }

    private static void moveParticle(final Simulation simulation, final Particle particle) {
       // TODO: It must be implemented.
       // TODO: Probably, the particles must be related with a nurse (an agent of Nurse type) and a sensor.
    }

    @Override
    public void inject(int num) {
        final Simulation simulation = getSimulation();
        final List<Particle> particles = peds();
        simulation.addInitEvent(() -> {
            for (int i = 0; i < num; ++i) {
                // TODO: The locations must be replaced with real ones.
                final Particle particle = new Particle(
                        Location.defaultLocation(),
                        Location.defaultLocation(),
                        i
                );
                particle.onCreate();
                particles.add(particle);
                simulation.updatePeds();
                moveParticle(simulation, particle);
            }
        });
    }

}
