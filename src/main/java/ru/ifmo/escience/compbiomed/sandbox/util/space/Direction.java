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
        } else if (Math.abs(remainder + PI) < 1.0E-4) {
            this.value = PI;
        } else {
            this.value = remainder;
        }
    }

    public static double fromDegreesToRadians(final double degrees) {
        return (degrees / FULL_CIRCLE_IN_DEGREES) * FULL_CIRCLE_IN_RADIANS;
    }

    public static double fromRadiansToDegrees(final double radians) {
        return radians * (FULL_CIRCLE_IN_DEGREES / FULL_CIRCLE_IN_RADIANS);
    }

    public static Direction fromDegrees(final double degrees) {
        return new Direction(fromDegreesToRadians(degrees));
    }

    public static Direction fromRadians(final double radians) {
        return new Direction(radians);
    }

    public final Direction subtractDegrees(final double degrees) {
        return subtractRadians(fromDegreesToRadians(degrees));
    }

    public final Direction subtractRadians(final double radians) {
        return addRadians(-radians);
    }

    public final Direction addDegrees(final double degrees) {
        return addRadians(fromDegreesToRadians(degrees));
    }

    public final Direction addRadians(final double radians) {
        return Direction.fromRadians(this.value + radians);
    }

    public final double getValue() {
        return value;
    }

    public final double getValueInDegrees() {
        return fromRadiansToDegrees(value);
    }
}
