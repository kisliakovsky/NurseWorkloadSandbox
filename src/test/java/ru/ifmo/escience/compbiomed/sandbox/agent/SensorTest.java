package ru.ifmo.escience.compbiomed.sandbox.agent;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class SensorTest {

    private Attractor sensor;
    private List<Medic> medics;

    @Before
    public void setUp() {
        sensor = new Attractor(); // attractorPatientRoom06Floor7 in AnyLogic
        medics = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            medics.add(new Nurse(i));
        }
    }

    @Test
    public void checkDetectionBySensor() {
        for (final Medic medic: medics) {
            final double distance = medic.distanceTo(this.sensor.getX(), this.sensor.getY());
            assertThat(distance, closeTo(3.1, 0.1));
            if (distance < 5) {
                System.out.println("Nurse " + medic.getId() + " is in the zone!");
            }
        }

    }
}