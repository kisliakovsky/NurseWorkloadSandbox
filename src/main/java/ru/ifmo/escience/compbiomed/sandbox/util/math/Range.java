package ru.ifmo.escience.compbiomed.sandbox.util.math;

import java.util.Random;

public class Range {

    public static int getIntInRange(final Random random, final int a, final int b) {
        return random.nextInt(b - a) + a;
    }

    public static double getDoubleInRange(final Random random, final double a, final double b) {
        return (random.nextDouble() * (b - a)) + a ;
    }

}
