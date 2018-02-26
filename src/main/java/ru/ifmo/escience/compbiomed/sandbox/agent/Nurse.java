package ru.ifmo.escience.compbiomed.sandbox.agent;

public class Nurse extends Medic {

    private String id;

    public Nurse(final double x, final double y, final long index) {
        super(x, y, index);
    }

    public Nurse(final long index) {
        this(4.0, 5.0, index);
    }

}
