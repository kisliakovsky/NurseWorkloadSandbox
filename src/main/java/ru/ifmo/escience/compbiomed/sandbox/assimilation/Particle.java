package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;

import java.util.Random;

public class Particle extends CareParticipant {

    private static final Random X_RANDOM = new Random();
    private static final Random Y_RANDOM = new Random();
    private static final Random DIRECTION_RANDOM = new Random();

    public Particle(long index) {
        super(index);
    }

}
