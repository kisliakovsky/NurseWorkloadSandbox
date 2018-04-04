package ru.ifmo.escience.compbiomed.sandbox;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Experiment {

    // These variables correspond to Main variables in the AnyLogic project
    public static final List<Attractor> PATIENT_ROOMS_FLOOR_7 = new ArrayList<>();

    static {
        for (int i = 0; i < 17; ++i) {
            PATIENT_ROOMS_FLOOR_7.add(new Attractor(i, i));
        }
    }

    public static void main(String[] args) {

        final Simulation simulation = new Simulation(25.0);
        final List<Function<Simulation, PedSource<? extends CareParticipant>>> sourceFactories = new ArrayList<>();
        sourceFactories.add(PedSource::createNurseSource);
        sourceFactories.stream().map((sourceFactory) -> {
            final PedSource<? extends CareParticipant> source = sourceFactory.apply(simulation);
            simulation.addSource(source);
            return source;
        }).forEach((source) -> source.inject(1));
        simulation.run();
    }

}
