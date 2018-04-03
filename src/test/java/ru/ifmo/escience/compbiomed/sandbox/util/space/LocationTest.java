package ru.ifmo.escience.compbiomed.sandbox.util.space;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static java.lang.Math.PI;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LocationTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {-1.0, 0.0},
                {-2.0, -3.0},
                {0.0, -5.0},
                {110.0, 105.0},
                {101.0, 5.0},
                {0.0, 150.0},
                {-200.0, 150.0},
        });
    }

    @Parameterized.Parameter(value = 0)
    public double x;

    @Parameterized.Parameter(value = 1)
    public double y;

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectInitialization() {
        Location.byCoordinates(x, y);
    }
}