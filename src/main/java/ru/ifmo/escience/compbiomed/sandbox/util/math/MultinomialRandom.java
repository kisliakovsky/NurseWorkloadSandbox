package ru.ifmo.escience.compbiomed.sandbox.util.math;

import java.util.Random;

public class MultinomialRandom {

    private final Random random = new Random();
    private final int range;
    private final double[] distribution;

    public MultinomialRandom(final double[] probabilities) {
        this.range = probabilities.length;
        this.distribution = new double[range];
        double position = 0;
        for (int i = 0; i < range; ++i) {
            position += probabilities[i];
            distribution[i] = position;
        }
        distribution[range - 1] = 1.0;
    }

    public int getSample() {
        final double uniform = random.nextDouble();
        for (int i = 0; i < range; ++i) {
            if (uniform < distribution[i]) {
                return i;
            }
        }
        return range - 1;
    }

    public int[] getSamples(final int num) {
        final int[] samples = new int[num];
        for (int i = 0; i < num; ++i) {
            samples[i] = getSample();
        }
        return samples;
    }

    public int[] getSamples() {
        return getSamples(range);
    }

}
