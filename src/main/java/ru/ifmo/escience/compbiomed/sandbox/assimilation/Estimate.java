package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;

public interface Estimate {

    static Location calculate(final List<Particle> particles) {
        double x = 0.0;
        double y = 0.0;
        for (final Particle particle: particles) {
            final double weight = particle.getWeight();
            x += particle.getX() * weight;
            y += particle.getY() * weight;
        }
        return Location.byCoordinates(x, y);
    }

}
