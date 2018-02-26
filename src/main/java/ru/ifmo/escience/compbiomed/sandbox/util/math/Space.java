package ru.ifmo.escience.compbiomed.sandbox.util.math;

public class Space {

    public static double calculateEuclideanDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(sqr(x2 - x1) + sqr(y2 - y1));
    }

    public static double calculateEuclideanDistance(final Point point1, final Point point2) {
        final double x1 = point1.getX();
        final double x2 = point2.getX();
        final double y1 = point1.getY();
        final double y2 = point2.getY();
        return calculateEuclideanDistance(x1, y1, x2, y2);
    }

    private static double sqr(double a) {
        return Math.pow(a, 2.0);
    }
}
