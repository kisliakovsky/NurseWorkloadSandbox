package ru.ifmo.escience.compbiomed.sandbox.agent;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import ru.ifmo.escience.compbiomed.sandbox.sensor.SimpliestSensorStub;
import ru.ifmo.escience.compbiomed.sandbox.util.space.Location;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SensorTest {

    private Attractor attractor;
    private List<CareParticipant> careParticipants;

    @Before
    public void setUp() {
        attractor = new Attractor(5.0, 8.0); // attractorPatientRoom06Floor7 in AnyLogic
        careParticipants = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            careParticipants.add(new Nurse(
                    Location.byCoordinates(4.0, 5.0),
                    Location.byCoordinates(4.0, 5.0),
                    i
            ));
        }
    }

    @Test
    public void checkDetection() {
        for (final CareParticipant careParticipant: careParticipants) {
            final SimpliestSensorStub sensor = new SimpliestSensorStub(attractor, careParticipant, 5.0);
            assertThat(sensor.check(), is(true));
        }
    }

    @Test
    public void checkLoss() {
        for (final CareParticipant careParticipant: careParticipants) {
            final SimpliestSensorStub sensor = new SimpliestSensorStub(attractor, careParticipant, 3.0);
            assertThat(sensor.check(), is(false));
        }
    }

}