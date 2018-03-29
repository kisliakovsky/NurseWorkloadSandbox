package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class StaticPedestrian implements Pedestrian {

    private Location location;

    public StaticPedestrian(final Location location) {
        this.location = location;
    }

    public StaticPedestrian() { this.location = Location.byCoordinates(0.0, 0.0); }

    @Override
    public double getX() {
        return location.getPoint().getX();
    }

    @Override
    public double getY() {
        return location.getPoint().getY();
    }

    @Override
    public double distanceTo(final double x, final double y) {
        return location.distanceTo(Location.byCoordinates(x, y));
    }

    @Override
    public double distanceTo(final Pedestrian other) {
        return location.distanceTo(Location.byCoordinates(other.getX(), other.getY()));
    }

}
