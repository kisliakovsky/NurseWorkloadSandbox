package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.Random;

public class Particle extends CareParticipant {

    private static final Random X_RANDOM = new Random();
    private static final Random Y_RANDOM = new Random();
    private static final Random DIRECTION_RANDOM = new Random();

    private double weight;

    public Particle(final Location location, final Location destination, final long index, final double weight) {
        super(location, destination, index);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void updateWeight(final double weight) {
        this.weight = weight;
    }

}
