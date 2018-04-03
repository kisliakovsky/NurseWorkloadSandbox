package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.RealCareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.util.math.Range;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Direction;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Particle extends CareParticipant {

    private static final double DEFAULT_DISPLACEMENT = 50.0;

    private RealCareParticipant object;
    private double weight;

    private Particle(final Location location, final Location target, final long index,
                     final double weight, final RealCareParticipant object) {
        super(location, target, index);
        this.weight = weight;
        this.object = object;
    }

    public double getWeight() {
        return weight;
    }

    public void updateWeight(final Function<Double, Double> updater) {
        this.weight = updater.apply(this.weight);
    }

    public static List<Particle> createParticles(final RealCareParticipant object, final int num) {
        final Random xRandom = new Random();
        final Random yRandom = new Random();
        final Random alphaRandom = new Random();
        final List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < num; ++i) {
            final int x = Range.getIntInRange(xRandom, 0, 101);
            final int y = Range.getIntInRange(yRandom, 0, 101);
            final double alpha = Direction.fromDegrees(Range.getIntInRange(alphaRandom, -179, 181)).getValue();
            final double weight = 1.0 / num;
            final double target_x = x + DEFAULT_DISPLACEMENT * Math.cos(alpha);
            final double target_y = y + DEFAULT_DISPLACEMENT * Math.cos(alpha);
            final Location location = Location.byCoordinates(x, y);
            final Location target = Location.byCoordinates(target_x, target_y);
            final Particle particle = new Particle(location, target, i, weight, object);
            particle.onCreate();
            particles.add(particle);
        }
        return particles;
    }

    public RealCareParticipant getObject() {
        return object;
    }
}
