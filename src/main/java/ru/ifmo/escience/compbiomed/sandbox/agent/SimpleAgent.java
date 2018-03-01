package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.math.Point;
import ru.ifmo.escience.compbiomed.sandbox.util.math.Space;

public class SimpleAgent implements Agent {

    private double x;
    private double y;

    public SimpleAgent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStartup() {
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double distanceTo(final double x, final double y) {
        return Space.calculateEuclideanDistance(new Point(this.x, this.y), new Point(x, y));
    }

    @Override
    public double distanceTo(final Agent other) {
        return Space.calculateEuclideanDistance(
                new Point(x, y),
                new Point(other.getX(), other.getY())
        );
    }

}
