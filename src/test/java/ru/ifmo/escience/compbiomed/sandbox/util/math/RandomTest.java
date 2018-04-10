package ru.ifmo.escience.compbiomed.sandbox.util.math;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class RandomTest {

    private MultinomialRandom multinomialRandom;
    private RandomWheel randomWheel;

    @Before
    public void setUp() {
        final double[] weights = new double[] {0.8, 0.05, 0.05, 0.1};
        multinomialRandom = new MultinomialRandom(weights);
        randomWheel = new RandomWheel(weights);
    }

    @Test
    public void checkSample() {
        final int[] multinomialSamples = multinomialRandom.getSamples();
        final int[] wheelSamples = randomWheel.getSamples();
        System.out.println("Multinomial: " + Arrays.toString(multinomialSamples));
        System.out.println("Random wheel: " + Arrays.toString(wheelSamples));
    }

}
