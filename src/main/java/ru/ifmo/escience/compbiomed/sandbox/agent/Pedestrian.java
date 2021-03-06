package ru.ifmo.escience.compbiomed.sandbox.agent;


public interface Pedestrian extends Agent {

    double getX();
    double getY();
    double distanceTo(final double x, final double y);
    double distanceTo(final Pedestrian other);

}
