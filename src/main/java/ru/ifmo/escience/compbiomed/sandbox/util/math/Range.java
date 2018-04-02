package ru.ifmo.escience.compbiomed.sandbox.util.math;

import java.util.Random;

public class Range {

    public static int getIntRange(final Random random, final int a, final int b) {
        return random.nextInt((b - a) + 1) + a;
    }

}
