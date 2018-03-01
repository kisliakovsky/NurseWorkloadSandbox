package ru.ifmo.escience.compbiomed.sandbox.agent;

public interface Agent {

    void onCreate();
    void onStartup();
    double getX();
    double getY();
    double distanceTo(final double x, final double y);
    double distanceTo(final Agent other);

}
