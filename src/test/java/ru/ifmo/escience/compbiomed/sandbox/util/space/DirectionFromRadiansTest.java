package ru.ifmo.escience.compbiomed.sandbox.util.space;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static java.lang.Math.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;


@RunWith(Parameterized.class)
public class DirectionFromRadiansTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0.0, 0.0}, {PI / 18, PI / 18}, {PI, PI},
                {PI + (PI / 180), -PI + (PI / 180)}, {4 * PI / 3, -2 * PI / 3}, {2 * PI, 0},
                {5 * PI / 2, PI / 2}, {-PI / 9, -PI / 9}, {-37 * PI / 18, -PI / 18}, {-19 * PI / 6, 5 * PI / 6},
                {-PI, PI}
        });
    }

    @Parameterized.Parameter(value = 0)
    public double degrees;

    @Parameterized.Parameter(value = 1)
    public double value;

    @Test
    public void checkValue() {
        assertThat(Direction.fromRadians(degrees).getValue(), closeTo(value, 1.0E-4));
    }
}