package ru.ifmo.escience.compbiomed.sandbox.agent;

public class CareParticipant extends SimplePedestrian {

    private String id;

    public CareParticipant(final double x, final double y, final long index) {
        super(x, y);
        id = this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    public CareParticipant(final long index) {
        this(0.0, 0.0, index);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public void onCreate() {
        System.out.println("Hi! I'm " + toString());
    }

    @Override
    public void onStartup() {
        System.out.println("I (" + toString() + ") am going to start!");
    }

}
