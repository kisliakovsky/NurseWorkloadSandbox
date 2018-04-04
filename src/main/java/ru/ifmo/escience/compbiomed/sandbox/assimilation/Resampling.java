package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import ru.ifmo.escience.compbiomed.sandbox.util.math.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public interface Resampling {

    static List<Particle> apply(final List<Particle> oldParticles) {
        final Random indexRandom = new Random();
        final Random betaRandom = new Random();
        final int numberOfParticles = oldParticles.size();
        final List<Double> oldWeights = oldParticles.stream()
                .map(Particle::getWeight)
                .collect(Collectors.toCollection(ArrayList::new));
        final List<Particle> newParticles = new ArrayList<>();
        int index = Range.getIntInRange(indexRandom, 0, numberOfParticles);
        final double max = oldWeights.stream().mapToDouble(x -> x).max().getAsDouble();
        double beta = 0.0;
        for (int i = 0; i < numberOfParticles; ++i) {
            beta += Range.getDoubleInRange(betaRandom, 0.0, 2.0 * max);
            while (beta > oldWeights.get(index)) {
                beta -= oldWeights.get(index);
                index = (index + 1) % numberOfParticles;
            }
            newParticles.add(new Particle(oldParticles.get(index)));
        }
        final double newWeightsSum = newParticles.stream().mapToDouble(Particle::getWeight).sum();
        newParticles.forEach(particle -> particle.updateWeight(weight -> weight / newWeightsSum));
        return newParticles;
    }

}
