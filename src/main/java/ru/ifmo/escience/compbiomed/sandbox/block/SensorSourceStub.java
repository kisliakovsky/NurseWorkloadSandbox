package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Estimate;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Measurement;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Particle;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Resampling;
import ru.ifmo.escience.compbiomed.sandbox.sensor.AdaptedSensor;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.*;
import java.util.stream.Collectors;

public class SensorSourceStub extends AbstractPedSource<AdaptedSensor> {

    private static final double POLL_STEP = 1.0;
    private final List<Location> locations;

    public SensorSourceStub(final Simulation simulation,
                            final List<Location> locations) {
        super(simulation);
        this.locations = locations;
    }

    private static void calculateWeights(List<? super RealCareParticipant> agents,
            final Map<? super RealCareParticipant, List<Particle>> particlesByAgents,
            final List<? super AdaptedSensor> sensors) {
        agents.forEach(agent -> {
            final List<Boolean> agentVector = sensors.stream()
                    .map(sensor -> ((AdaptedSensor) sensor).check((Pedestrian) agent))
                    .collect(Collectors.toCollection(ArrayList::new));
            final List<Particle> particles = particlesByAgents.get((RealCareParticipant) agent);
            if (Objects.nonNull(particles)) {
                particles.forEach(particle -> {
                    if (agent.equals(particle.getObject())) {
                        final List<Boolean> particleVector = sensors.stream()
                                .map(sensor -> ((AdaptedSensor) sensor).check(particle))
                                .collect(Collectors.toCollection(ArrayList::new));
                        particle.updateWeight((oldWeight) -> {
                            final double newWeight;
                            if (particle.isCorrectlyLocated()) {
                                newWeight = Measurement.calculateWeight(agentVector, particleVector);
                            } else {
                                newWeight = 0.0;
                            }
                            return newWeight;
                        });
                    }
                });
            }
        });
    }

    private static void normalizeWeight(final List<Particle> particles) {
        final double sum = particles.stream().mapToDouble(Particle::getWeight).sum();
        particles.forEach(pseudoObservable -> pseudoObservable.updateWeight((oldWeight) -> oldWeight / sum));
    }

    private static void makePoll(final Simulation simulation, final double time) {
        simulation.addEvent(new AbstractEvent(time) {
            @Override
            public void execute() {
                final List<? super RealCareParticipant> observables = simulation.getObservables();
                final Map<? super RealCareParticipant, List<Particle>> pseudoObservablesByObservables = simulation.getPseudoObservablesByObservables();
                final List<? super AdaptedSensor> observers = simulation.getObservers();
                calculateWeights(observables, pseudoObservablesByObservables, observers);
                observables.forEach(observable -> {
                    final List<Particle> pseudoObservables = pseudoObservablesByObservables.get((RealCareParticipant) observable);
                    if (Objects.nonNull(pseudoObservables)) {
                        normalizeWeight(pseudoObservables);
                        final List<Particle> newPseudoObservables = Resampling.apply(pseudoObservables);
                        final Location estimatedLocation = Estimate.calculate(newPseudoObservables);
                        final Location actualLocation = Location.byCoordinates(((RealCareParticipant) observable).getX(), ((RealCareParticipant) observable).getY());
                        pseudoObservablesByObservables.put((RealCareParticipant) observable, newPseudoObservables);
                        newPseudoObservables.forEach(newPseudoObservable -> newPseudoObservable.move(POLL_STEP));
                    }
                });
                makePoll(simulation, time + POLL_STEP);

            }});
    }

    @Override
    public void inject(final int num) {
        final Simulation simulation = getSimulation();
        final List<AdaptedSensor> sensors = peds();
        final List<? super AdaptedSensor> observers = simulation.getObservers();
        simulation.addInitEvent(() -> {
            for (int i = 0; i < num; ++i) {
                if (i < locations.size()) {
                    final Location location = locations.get(i);
                    final AdaptedSensor sensor = new AdaptedSensor(
                            location, i
                    );
                    sensor.onCreate();
                    sensors.add(sensor);
                    observers.add(sensor);
                } else {
                    throw new IllegalArgumentException(
                            "There are not enough locations"
                    );
                }
            }
            makePoll(simulation, simulation.getTime());
        });
    }

}
