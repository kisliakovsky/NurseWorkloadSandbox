package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class CareParticipant extends TargetedPedestrian {

    private String id;

    public CareParticipant(final Location location, final Location target, final long index) {
        super(location, target);
        id = this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    public CareParticipant(final Location location, final long index) {
        this(location, location, index);
    }

    public CareParticipant(final long index) {
        this(Location.defaultLocation(), index);
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

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof CareParticipant && this.toString().equals(obj.toString());
    }

}
