package ru.ifmo.escience.compbiomed.sandbox.agent;

public class CareParticipant extends SimplePedestrian {

    private String id;

    public CareParticipant(final double x, final double y, final long index) {
        super(x, y);
        id = this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    @Override
    public String toString() {
        return id;
    }
}
