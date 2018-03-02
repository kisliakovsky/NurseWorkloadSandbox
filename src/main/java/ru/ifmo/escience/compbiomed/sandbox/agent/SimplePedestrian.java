package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class SimplePedestrian implements Pedestrian {

    private Location location;

    public SimplePedestrian(double x, double y) {
        location = new Location(x, y);
    }

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
        return location.distanceTo(new Location(x, y));
    }

    @Override
    public double distanceTo(final Pedestrian other) {
        return location.distanceTo(new Location(other.getX(), other.getY()));
    }

}
