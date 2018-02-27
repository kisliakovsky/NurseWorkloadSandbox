package ru.ifmo.escience.compbiomed.sandbox.util.io;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class CsvReader implements Reader<String[]> {

    private static final String EXTENSION = ".csv";

    public Optional<List<String[]>> read(final String filePath) {
        List<String[]> rows = null;

        try {
            final URL fileUrl = getClass().getClassLoader().getResource(filePath + EXTENSION);
            if (fileUrl == null) {
                throw new IOException();
            }
            try(final CSVReader reader = new CSVReader(new FileReader(new File(fileUrl.getFile())))) {
                rows = reader.readAll();
            }
        } catch (final IOException e) {
            System.err.println("Cannot read the file " + filePath);
        }

        return Optional.ofNullable(rows);
    }

}
