package ru.ifmo.escience.compbiomed.sandbox.util.space;

public class Location {

    private Point point;

    public Location(final Point point) {
        this.point = point;
    }

    public Location(final double x, final double y) {
        this(new Point(x, y));
        if (x < 0.0 || x > 10.0 || y < 0.0 || y > 10.0)
            throw new IllegalArgumentException();
    }

    public Point getPoint() {
        return point;
    }

    public double distanceTo(final Location other) {
        return Space.calculateEuclideanDistance(this.point, other.point);
    }

}
