package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.math.Point;
import ru.ifmo.escience.compbiomed.sandbox.util.math.Space;

public class Agent {

    private double x;
    private double y;

    public Agent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(final double x, final double y) {
        return Space.calculateEuclideanDistance(new Point(this.x, this.y), new Point(x, y));
    }

    public double distanceTo(final Agent other) {
        return Space.calculateEuclideanDistance(
                new Point(x, y),
                new Point(other.getX(), other.getY())
        );
    }

}
