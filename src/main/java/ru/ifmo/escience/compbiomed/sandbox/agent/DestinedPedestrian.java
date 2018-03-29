package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class DestinedPedestrian extends StaticPedestrian {

    private Location destination;
    private double speed;

    public DestinedPedestrian(final Location location, final Location destination, final double speed) {
        super(location);
        this.destination = destination;
        this.speed = speed;
    }

    public DestinedPedestrian(final Location location, final Location destination) {
        this(location, destination, 4.0);
    }

    public final void updateDestination(final Location destination) {
        this.destination = destination;
    }

}
