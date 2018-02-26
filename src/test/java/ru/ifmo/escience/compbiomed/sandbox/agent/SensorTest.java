package ru.ifmo.escience.compbiomed.sandbox.agent;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.sensor.Attractor;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class SensorTest {

    private Attractor sensor;
    private Pedestrian pedestrian;

    @Before
    public void setUp() {
        sensor = new Attractor();
        pedestrian = new Nurse();
    }

    @Test
    public void checkDetectionBySensor() {
        final double distance = this.pedestrian.distanceTo(this.sensor.getX(), this.sensor.getY());
        assertThat(distance, closeTo(3.1, 0.1));
    }
}