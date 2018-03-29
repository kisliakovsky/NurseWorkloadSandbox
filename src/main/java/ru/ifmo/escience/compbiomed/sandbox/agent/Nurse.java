package ru.ifmo.escience.compbiomed.sandbox.agent;

import ru.ifmo.escience.compbiomed.sandbox.Experiment;
import ru.ifmo.escience.compbiomed.sandbox.data.Event;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import ru.ifmo.escience.compbiomed.sandbox.util.io.Reader;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.List;
import java.util.stream.Collectors;

public class Nurse extends CareParticipant {

    private int currentFloor;
    private int nextFloor;
    private Attractor nextPatientRoom;
    private int indexOfElevator;
    private List<Integer> floors;
    private List<Integer> doors;
    private List<Attractor> patientRooms;

    public Nurse(final Location location, final Location destination, final long index) {
        super(location, destination, index);
    }

    public Nurse(final Location location, final long index) {
        super(location, index);
    }

    public Nurse(final long index) {
        super(index);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Reader<Event> scheduleReader = Reader.getCsvReader();
        final List<Event> schedule = scheduleReader.read("acs_schedule");
        final List<Event> filteredSchedule = schedule.stream()
                .filter(event -> toString().equals(event.getName()))
                .collect(Collectors.toList());
        floors = filteredSchedule.stream().map(Event::getFloor).collect(Collectors.toList());
        doors = filteredSchedule.stream().map(Event::getDoor).collect(Collectors.toList());
        patientRooms = Experiment.PATIENT_ROOMS_FLOOR_7;
    }
}
