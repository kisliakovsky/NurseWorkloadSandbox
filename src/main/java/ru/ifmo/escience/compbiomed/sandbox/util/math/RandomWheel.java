package ru.ifmo.escience.compbiomed.sandbox.util.math;

import java.util.Arrays;
import java.util.Random;

public class RandomWheel {

    private final Random indexRandom = new Random();
    private final Random betaRandom = new Random();
    private final double[] probabilities;

    public RandomWheel(final double[] probabilities) {
        this.probabilities = probabilities;
    }

    public int[] getSamples() {
        final int range = probabilities.length;
        final int[] samples = new int[range];
        final double max = Arrays.stream(probabilities).max().getAsDouble();
        int index = Range.getIntInRange(indexRandom, 0, range);
        double beta = 0.0;
        for (int i = 0; i < range; ++i) {
            beta += Range.getDoubleInRange(betaRandom, 0.0, 2.0 * max);
            while (beta > probabilities[index]) {
                beta -= probabilities[index];
                index = (index + 1) % range;
            }
            samples[i] = index;
        }
        return samples;
    }
}
