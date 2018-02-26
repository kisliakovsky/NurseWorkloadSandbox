package ru.ifmo.escience.compbiomed.sandbox.agent;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import ru.ifmo.escience.compbiomed.sandbox.sensor.SensorStub;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SensorTest {

    private Attractor attractor;
    private List<Medic> medics;

    @Before
    public void setUp() {
        attractor = new Attractor(5.0, 8.0); // attractorPatientRoom06Floor7 in AnyLogic
        medics = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            medics.add(new Nurse(4.0, 5.0, i));
        }
    }

    @Test
    public void checkDetectionBySensor() {
        for (final Medic medic: medics) {
            final SensorStub sensor = new SensorStub(attractor, medic, 5.0);
            assertThat(sensor.check(), is(true));
        }
    }

    @Test
    public void checkLossBySensor() {
        for (final Medic medic: medics) {
            final SensorStub sensor = new SensorStub(attractor, medic, 3.0);
            assertThat(sensor.check(), is(false));
        }
    }

}