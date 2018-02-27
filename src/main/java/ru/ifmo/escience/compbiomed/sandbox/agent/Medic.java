package ru.ifmo.escience.compbiomed.sandbox.agent;

public class Medic extends Pedestrian {

    private String id;

    public Medic(final double x, final double y, final long index) {
        super(x, y);
//        this.id =  this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
