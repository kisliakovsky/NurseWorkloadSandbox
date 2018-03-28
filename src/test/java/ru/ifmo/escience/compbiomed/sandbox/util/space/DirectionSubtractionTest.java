package ru.ifmo.escience.compbiomed.sandbox.util.space;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

@RunWith(Parameterized.class)
public class DirectionSubtractionTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0.0, 0.0, 0.0},
                {30.0, 20.0, 10.0},
                {210.0, 20.0, -170.0},
                {180.0, 0.0, 180.0},
                {-180.0, 10.0, 170.0},
                {-170.0, 10.0, 180.0},
                {-10.0, 30.0, -40.0},

        });
    }

    @Parameterized.Parameter(value = 0)
    public double minuend;

    @Parameterized.Parameter(value = 1)
    public double subtrahend;

    @Parameterized.Parameter(value = 2)
    public double expectedValue;

    @Test
    public void checkValue() {
        final double actualValue = Direction.fromDegrees(minuend).subtractDegrees(subtrahend).getValueInDegrees();
        assertThat(actualValue, closeTo(expectedValue, 1.0E-4));
    }
}