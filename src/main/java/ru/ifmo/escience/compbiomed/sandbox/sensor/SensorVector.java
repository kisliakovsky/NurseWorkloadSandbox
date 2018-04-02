package ru.ifmo.escience.compbiomed.sandbox.sensor;

import ru.ifmo.escience.compbiomed.sandbox.agent.CareParticipant;
import ru.ifmo.escience.compbiomed.sandbox.agent.TargetedPedestrian;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class SensorVector {

    private final Map<CareParticipant, List<BasicSensorStub>> vector;
    private final String vectorString;

    public SensorVector(final Map<CareParticipant, List<BasicSensorStub>> vector) {
        this.vector = vector;
        final StringJoiner vectorStringJoiner = new StringJoiner(",", "[", "]");
        for (final Map.Entry<CareParticipant, List<BasicSensorStub>> pedData: vector.entrySet()) {
            final StringJoiner pedStringJoiner = new StringJoiner(",", "[", "]");
            for (final BasicSensorStub sensor: pedData.getValue()) {
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
