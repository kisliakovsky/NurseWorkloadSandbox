package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ProtoSensorVector {

    private final Map<? super CareParticipant, List<AdaptedSensor>> vector;
    private final String vectorString;

    public ProtoSensorVector(final Map<? super CareParticipant, List<AdaptedSensor>> vector) {
        this.vector = vector;
        final StringJoiner vectorStringJoiner = new StringJoiner(",", "[", "]");
        for (final Map.Entry<? super CareParticipant, List<AdaptedSensor>> pedData: vector.entrySet()) {
            final StringJoiner pedStringJoiner = new StringJoiner(",", "[", "]");
            for (final AdaptedSensor sensor: pedData.getValue()) {
                if (sensor == null) {
                    pedStringJoiner.add(Integer.toBinaryString(0));
                } else {
                    pedStringJoiner.add(Integer.toBinaryString(1));
                }
            }
            vectorStringJoiner.add(pedData.getKey() + ": " + pedStringJoiner.toString());
        }
        this.vectorString = vectorStringJoiner.toString();
    }

    @Override
    public String toString() {
        return this.vectorString;
    }
}
