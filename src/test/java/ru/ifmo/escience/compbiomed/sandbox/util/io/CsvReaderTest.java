package ru.ifmo.escience.compbiomed.sandbox.util.io;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void checkRead() {
        final Reader<String[]> scheduleReader = new CsvReader();
        final Optional<List<String[]>> scheduleOpt = scheduleReader.read("acs_schedule");
        if (scheduleOpt.isPresent()) {
            assertThat(scheduleOpt.get().size(), is(8));
        } else {
            fail();
        }
    }
}