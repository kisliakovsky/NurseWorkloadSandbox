package ru.ifmo.escience.compbiomed.sandbox.simulation;

public interface Event {

    void execute();

    double getTimeSec();

    double getTimeNano();

}
