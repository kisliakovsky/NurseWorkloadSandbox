package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Nurse extends CareParticipant {

    private int currentFloor;
    private int nextFloor;
    private Attractor nextPatientRoom = new Attractor(0.0, 0.0);
    private int indexOfElevator;
    private List<Integer> floors = new LinkedList<>();
    private List<Integer> doors = new LinkedList<>();
    private List<Attractor> patientRooms = new ArrayList<>();

    public Nurse(final long index) {
        super(index);
    }

    public Nurse(final double x, final double y, final long index) {
        super(x, y, index);
    }
}
