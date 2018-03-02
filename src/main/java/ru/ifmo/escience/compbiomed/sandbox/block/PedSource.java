package ru.ifmo.escience.compbiomed.sandbox.block;

import ru.ifmo.escience.compbiomed.sandbox.agent.Nurse;
import ru.ifmo.escience.compbiomed.sandbox.agent.Patient;
import ru.ifmo.escience.compbiomed.sandbox.agent.Pedestrian;
import ru.ifmo.escience.compbiomed.sandbox.simulation.Simulation;

import java.util.List;

public interface PedSource<T extends Pedestrian> extends PedBlock<T> {

    static PedSource<Nurse> createNurseSource(final Simulation simulation) {
        return new SimplePedSource<>(Nurse.class, simulation);
    }

    static PedSource<Patient> createPatientSource(final Simulation simulation) {
        return new SimplePedSource<>(Patient.class, simulation);
    }

    List<T> peds();

    void inject(final int num);

}
