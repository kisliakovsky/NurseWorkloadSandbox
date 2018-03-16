package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class Attractor {

    private Location location;

    public Attractor(double x, double y) {
        location = Location.byCoordinates(x, y);
    }

    public double getX() {
        return location.getPoint().getX();
    }

    public double getY() {
        return location.getPoint().getY();
    }

}
