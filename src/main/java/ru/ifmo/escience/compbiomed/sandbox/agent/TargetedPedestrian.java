package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Direction;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Space;

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
        return Double.compare(getDisplacement(), 0.0) == 0;
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
                    direction = Direction.fromRadians(alpha);
                } else {
                    direction = Direction.fromRadians(alpha + Math.PI);
                }
            }
        }
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDisplacement() {
        return Space.calculateEuclideanDistance(getX(), getY(), getTargetX(), getTargetY());
    }

    public final void updateTarget(final Location target) {
        this.target = target;
    }

    public void move(final double time) {
        final Direction direction = calculateDirection();
        if (direction != null) {
            final double alpha = direction.getValue();
            final double delta_x = speed * Math.cos(alpha) * time;
            final double delta_y = speed * Math.sin(alpha) * time;
            updateLocation(Location.byCoordinates(getX() + delta_x, getY() + delta_y));
        }
    }
}
