package ru.ifmo.escience.compbiomed.sandbox.util.space;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static java.lang.Math.PI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;


@RunWith(Parameterized.class)
public class DirectionFromDegreesTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0.0, 0.0}, {10.0, PI / 18}, {180.0, PI},
                {181.0, -PI + (PI / 180)}, {240.0, -2 * PI / 3}, {360.0, 0},
                {450.0, PI / 2}, {-20.0, -PI / 9}, {-370.0, -PI / 18}, {-570.0, 5 * PI / 6},
                {-180.0, PI}
        });
    }

    @Parameterized.Parameter(value = 0)
    public double degrees;

    @Parameterized.Parameter(value = 1)
    public double value;

    @Test
    public void checkValue() {
        assertThat(Direction.fromDegrees(degrees).getValue(), closeTo(value, 1.0E-4));
    }
}