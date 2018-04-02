package ru.ifmo.escience.compbiomed.sandbox.assimilation;

import java.util.List;

public interface Measurement {

    double A = 2;

    static double calculateWeight(final List<Boolean> realVector,
                                  final List<Boolean> particleVector) {
        double accumulator = 1;
        for (int i = 0; i < realVector.size(); ++i) {
            final boolean realComponent = realVector.get(i);
            final boolean particleComponent = particleVector.get(i);
            final double d;
            if (realComponent && particleComponent) {
                d = 0;
            } else if (!(realComponent || particleComponent)) {
                d = 1 / A;
            } else {
                d = A;
            }
            accumulator *= Math.exp(-(d * d) / 2) / Math.sqrt(2 * Math.PI);
        }
        return accumulator;
    }

}
