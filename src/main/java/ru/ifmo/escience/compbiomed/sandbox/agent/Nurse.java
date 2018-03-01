package ru.ifmo.escience.compbiomed.sandbox.agent;

public class Nurse extends CareParticipant {

    public Nurse(final double x, final double y, final long index) {
        super(x, y, index);
    }

    public Nurse(final long index) {
        this(0.0, 0.0, index);
    }

    @Override
    public void onCreate() {
        System.out.println("Hi! I'm " + toString());
    }
}
