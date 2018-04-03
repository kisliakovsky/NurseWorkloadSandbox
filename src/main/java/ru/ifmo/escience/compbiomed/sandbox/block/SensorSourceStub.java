package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Measurement;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Particle;
import ru.ifmo.escience.compbiomed.sandbox.assimilation.Resampling;
import ru.ifmo.escience.compbiomed.sandbox.sensor.AdaptedSensor;
import ru.ifmo.escience.compbiomed.sandbox.sensor.ProtoSensorVector;
import ru.ifmo.escience.compbiomed.sandbox.simulation.AbstractEvent;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.*;

public class SensorSourceStub extends AbstractPedSource<AdaptedSensor> {

    private final List<Location> locations;

    public SensorSourceStub(final Simulation simulation,
                            final List<Location> locations) {
        super(simulation);
        this.locations = locations;
    }

    private static void makePoll(final Simulation simulation, final double time) {
        simulation.addEvent(new AbstractEvent(time) {
            @Override
            public void execute() {
                final List<? super RealCareParticipant> observables = simulation.getObservables();
                final List<Particle> pseudoObservables = simulation.getPseudoObservables();
                final List<? super AdaptedSensor> observers = simulation.getObservers();
                for (final Object observable: observables) {
                    final List<Boolean> observableData = new LinkedList<>();
                    for (final Object observer: observers) {
                        if (((AdaptedSensor) observer).check((Pedestrian) observable)) {
                            observableData.add(true);
                        } else {
                            observableData.add(false);
                        }
                    }
                    for (final Particle pseudoObservable: pseudoObservables) {
                        if (observable.equals(pseudoObservable.getObject())) {
                            final List<Boolean> pseudoObservableData = new LinkedList<>();
                            for (final Object observer: observers) {
                                if (((AdaptedSensor) observer).check(pseudoObservable)) {
                                    pseudoObservableData.add(true);
                                } else {
                                    pseudoObservableData.add(false);
                                }
                            }
                            final double newWeight = Measurement.calculateWeight(observableData, pseudoObservableData);
                            pseudoObservable.updateWeight((oldWeight) -> newWeight);
                        }
                    }
                }
                for (final Object observable: observables) {
                    final double sum = pseudoObservables.stream()
                            .filter(pseudoObservable -> observable.equals(pseudoObservable.getObject()))
                            .mapToDouble(Particle::getWeight)
                            .sum();
                    pseudoObservables.stream()
                            .filter(pseudoObservable -> observable.equals(pseudoObservable.getObject()))
                            .forEach(pseudoObservable -> pseudoObservable
                                    .updateWeight((oldWeight) -> oldWeight / sum));
                }
                final List<Particle> newPseudoObservables = Resampling.make(pseudoObservables);
                // TODO: Replace particles with new ones.
                makePoll(simulation, time + 1e-3);
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
                    simulation.updatePeds();
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
