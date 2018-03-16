package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class CareParticipant extends DestinedPedestrian {

    private String id;

    public CareParticipant(final Location location, final Location destination, final long index) {
        super(location, destination);
        id = this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    public CareParticipant(final long index) {
        this(Location.byCoordinates(0.0, 0.0), Location.byCoordinates(10.0, 10.0), index);
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
