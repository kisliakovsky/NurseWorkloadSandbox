package ru.ifmo.escience.compbiomed.sandbox.simulation;

public class SimpleEvent implements Event {

    private double eTime;

    public SimpleEvent(final double eTime) {
        this.eTime = eTime;
    }

    @Override
    public void execute() {
        System.out.println("Execute the event " + toString() + " at the moment " + eTime);
    }

    @Override
    public double eTime() {
        return eTime;
    }
}
