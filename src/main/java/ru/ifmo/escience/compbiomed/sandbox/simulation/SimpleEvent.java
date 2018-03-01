package ru.ifmo.escience.compbiomed.sandbox.simulation;

public class SimpleEvent extends AbstractEvent {

    public SimpleEvent(final double time) {
        super(time);
    }

    @Override
    public void execute() {
        System.out.println("Execute the event " + toString() + " at the moment " + getTimeSec());
    }

}
