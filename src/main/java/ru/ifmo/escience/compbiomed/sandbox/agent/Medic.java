package ru.ifmo.escience.compbiomed.sandbox.agent;

public class Medic extends Pedestrian {

    private String id;

    public Medic(final double x, final double y, final long index) {
        super(x, y);
        this.id =  this.getClass().getSimpleName().toLowerCase() + "_" + index;
    }

    public String getId() {
        return id;
    }

}
