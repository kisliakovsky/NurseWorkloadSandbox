package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Direction;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

public class TargetedPedestrian extends StaticPedestrian {

    private Location target;
    private double speed;

    public TargetedPedestrian(final Location location, final Location target, final double speed) {
        super(location);
        this.target = target;
        this.speed = speed;
    }

    public TargetedPedestrian(final Location location, final Location target) {
        this(location, target, 4.0);
    }

    public double getTargetX() {
        return target.getX();
    }

    public double getTargetY() {
        return target.getY();
    }

    public final boolean isArrived() {
        final double deltaX = getTargetX() - getX();
        final double deltaY = getTargetY() - getY();
        final int res1 = Double.compare(deltaX, 0.0);
        final int res2 = Double.compare(deltaY, 0.0);
        return (res1 + res2) == 0;
    }

    private Direction calculateDirection() {
        final double deltaX = getTargetX() - getX();
        final double deltaY = getTargetY() - getY();
        Direction direction = null;
        if (!isArrived()) {
            if (Double.compare(deltaX, 0.0) == 0) {
                if (Double.compare(deltaY, 0.0) > 0) {
                    direction = Direction.fromDegrees(90);
                } else {
                    direction = Direction.fromDegrees(-90);
                }
            } else if (Double.compare(deltaY, 0.0) == 0) {
                if (Double.compare(deltaX, 0.0) > 0) {
                    direction = Direction.fromDegrees(0);
                } else {
                    direction = Direction.fromDegrees(180);
                }
            } else {
                final double alpha = Math.atan(deltaX / deltaY);
                if (Double.compare(deltaX, 0.0) > 0) {
                    direction = Direction.fromDegrees(alpha);
                } else {
                    direction = Direction.fromDegrees(alpha + 180);
                }
            }
        }
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public final void updateTarget(final Location target) {
        this.target = target;
    }

    public void updateLocation(final double time) {
        final Direction direction = calculateDirection();
        if (direction != null) {
            final double alpha = direction.getValue();
            final double delta_x = speed * Math.cos(alpha);
            final double delta_y = speed * Math.sin(alpha);
            // TODO: Make the location update
        }
    }

}
