package ru.ifmo.escience.compbiomed.sandbox.util.space;

public class Location {

    private Point point;

    private Location(final Point point) {
        this.point = point;
    }

    public static Location byPoint(final Point point) {
        final double x = point.getX();
        final double y = point.getY();
        return new Location(point);
    }

    public static Location byCoordinates(final double x, final double y) {
        return byPoint(new Point(x, y));
    }

    public static Location defaultLocation() { return byCoordinates(0.0, 0.0); }

    public Point getPoint() {
        return point;
    }

    public double getX() {
        return point.getX();
    }

    public double getY() {
        return point.getY();
    }

    public double distanceTo(final Location other) {
        return Space.calculateEuclideanDistance(this.point, other.point);
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
