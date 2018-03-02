package ru.ifmo.escience.compbiomed.sandbox.util.io;

import ru.ifmo.escience.compbiomed.sandbox.data.Event;

import java.util.List;
import java.util.Optional;

public interface Reader<T> {

    static Reader<Event> getCsvReader() {
        return CsvReader.getInstance();
    }

    List<T> read(final String filePath);

}
