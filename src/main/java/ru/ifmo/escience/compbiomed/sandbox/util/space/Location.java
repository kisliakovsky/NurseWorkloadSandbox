package ru.ifmo.escience.compbiomed.sandbox.util.space;

import java.util.function.Function;
import java.util.function.Supplier;

public class Location {

    private static boolean isCoordinateCorrect(final double coordinate) {
        return Double.compare(coordinate, 0.0) >= 0 && Double.compare(coordinate, 100.0) <= 0;
    }

    private Point point;

    private Location(final Point point) {
        this.point = point;
    }

    public static Location byPoint(final Point point) {
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

    public boolean isCorrect() {
        final double x = point.getX();
        final double y = point.getY();
        return Double.compare(x, 0.0) >= 0 && Double.compare(x, 100.0) <= 0 && Double.compare(y, 0.0) >= 0 && Double.compare(y, 100.0) <= 0;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
