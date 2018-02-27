package ru.ifmo.escience.compbiomed.sandbox.util.io;

import java.util.List;
import java.util.Optional;

public interface Reader<T> {

    public Optional<List<T>> read(final String filePath);

}
