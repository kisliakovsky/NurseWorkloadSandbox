package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class RealCareParticipant extends CareParticipant {

    public RealCareParticipant(final Location location, final Location target, final long index) {
        super(location, target, index);
    }

    public RealCareParticipant(final Location location, final long index) {
        super(location, index);
    }

    public RealCareParticipant(final long index) {
        super(index);
    }

}
