package ru.ifmo.escience.compbiomed.sandbox.util.io;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.ifmo.escience.compbiomed.sandbox.data.Event;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class CsvReader implements Reader<Event> {

    private static final String EXTENSION = ".csv";

    public Optional<List<Event>> read(final String filePath) {
        List<Event> schedule = null;
        try {
            final URL fileUrl = getClass().getClassLoader().getResource(filePath + EXTENSION);
            if (fileUrl == null) {
                throw new IOException();
            }
            try(final FileReader reader = new FileReader(new File(fileUrl.getFile()))) {
                final CsvToBean<Event> scheduleBuilder = new CsvToBeanBuilder<Event>(reader)
                        .withType(Event.class).build();
                schedule = scheduleBuilder.parse();
            }
        } catch (final IOException e) {
            System.err.println("Cannot read the file " + filePath);
        }

        return Optional.ofNullable(schedule);
    }

}
