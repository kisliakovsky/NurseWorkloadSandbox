package ru.ifmo.escience.compbiomed.sandbox.util.io;

import org.junit.Test;
import ru.ifmo.escience.compbiomed.sandbox.data.Event;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CsvReaderTest {

    @Test
    public void checkRead() {
        final Reader<Event> scheduleReader = Reader.getCsvReader();
        final Optional<List<Event>> scheduleOpt = Optional.ofNullable(scheduleReader.read("acs_schedule"));
        if (scheduleOpt.isPresent()) {
            final List<Event> schedule = scheduleOpt.get();
            assertThat(schedule.size(), is(7));
            assertThat(schedule.get(0).getName(), is("nurse_0"));
        } else {
            fail();
        }
    }
}