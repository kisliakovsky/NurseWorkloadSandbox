package ru.ifmo.escience.compbiomed.sandbox.util.space;

import static java.lang.Math.PI;

public class Direction {

    private static final double FULL_CIRCLE_IN_RADIANS = 2 * PI;
    private static final double FULL_CIRCLE_IN_DEGREES = 360;

    private double value;

    private Direction(final double value) {
        double remainder = value % FULL_CIRCLE_IN_RADIANS;
        if (Math.abs(remainder) > PI) {
            this.value = remainder - Math.signum(remainder) * FULL_CIRCLE_IN_RADIANS;
        } else if (remainder == -PI) {
            this.value = PI;
        } else {
            this.value = remainder;
        }
    }

    public static Direction fromDegrees(final double degrees) {
        return  new Direction((degrees / FULL_CIRCLE_IN_DEGREES) * FULL_CIRCLE_IN_RADIANS);
    }

    public static Direction fromRadians(final double radians) {
        return  new Direction(radians);
    }

    public double getValue() {
        return value;
    }
}
