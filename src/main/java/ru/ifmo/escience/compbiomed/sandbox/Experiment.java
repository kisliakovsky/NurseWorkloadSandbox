package ru.ifmo.escience.compbiomed.sandbox;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.block.PedSource;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Experiment {

    public static void main(String[] args) {
        final Simulation simulation = new Simulation(25.0);
        final List<Function<Simulation, PedSource<? extends CareParticipant>>> sourceFactories = new ArrayList<>();
        sourceFactories.add(PedSource::createNurseSource);
        sourceFactories.add(PedSource::createPatientSource);
        sourceFactories.forEach((sourceFactory) -> {
            final PedSource<? extends CareParticipant> source = sourceFactory.apply(simulation);
            simulation.addSource(source);
        });
        simulation.run();
    }

}
