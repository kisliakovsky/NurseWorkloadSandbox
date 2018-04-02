package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.TargetedPedestrian;

import java.util.List;
import java.util.StringJoiner;

public class SensorVector {

    private final List<List<TargetedPedestrian>> vector;
    private final String vectorString;

    public SensorVector(final List<List<TargetedPedestrian>> vector) {
        this.vector = vector;
        final StringJoiner vectorStringJoiner = new StringJoiner(",", "[", "]");
        for (final List<TargetedPedestrian> sensorData: vector) {
            final StringJoiner sensorStringJoiner = new StringJoiner(",", "[", "]");
            for (final TargetedPedestrian ped: sensorData) {
                sensorStringJoiner.add(ped.toString());
            }
            vectorStringJoiner.add(sensorStringJoiner.toString());
        }
        this.vectorString = vectorStringJoiner.toString();
    }

    @Override
    public String toString() {
        return this.vectorString;
    }
}
