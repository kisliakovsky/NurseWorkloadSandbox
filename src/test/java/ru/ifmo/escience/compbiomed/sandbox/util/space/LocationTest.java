package ru.ifmo.escience.compbiomed.sandbox.util.space;

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
                {11.0, 15.0},
                {100.0, 5.0},
                {0.0, 15.0},
        });
    }

    @Parameterized.Parameter(value = 0)
    public double x;

    @Parameterized.Parameter(value = 1)
    public double y;

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectInitialization() {
        Location.byCoordinates(x, y);
    }
}