package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class DestinedPedestrian extends StaticPedestrian {

    private Location destination;

    public DestinedPedestrian(final Location location, final Location destination) {
        super(location);
        this.destination = destination;
    }

}
