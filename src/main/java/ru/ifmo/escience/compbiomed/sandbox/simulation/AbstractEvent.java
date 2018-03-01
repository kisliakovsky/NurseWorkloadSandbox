package ru.ifmo.escience.compbiomed.sandbox.simulation;

public abstract class AbstractEvent implements Event {

    private double timeSec;
    private double timeNano;

    public AbstractEvent(final double timeSec) {
        this.timeSec = timeSec;
        timeNano = timeSec * 1e9;
    }

    @Override
    public double getTimeSec() {
        return timeSec;
    }

    @Override
    public double getTimeNano() {
        return timeNano;
    }

}
