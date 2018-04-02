package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class Patient extends RealCareParticipant {

    public Patient(final Location location, final Location destination, final long index) {
        super(location, destination, index);
    }

    public Patient(final Location location, final long index) {
        super(location, index);
    }

    public Patient(final long index) {
        super(index);
    }

}
