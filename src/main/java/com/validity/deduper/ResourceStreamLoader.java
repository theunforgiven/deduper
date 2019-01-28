package com.validity.deduper;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class ResourceStreamLoader {
    private ResourceStreamLoader() {}

    public static Stream<UserRecord> parseResource(String resourcePath) {
        InputStream normalCsvSampleAsStream = UserRecord.class.getResourceAsStream(resourcePath);
        Iterator<UserRecord> iterator = new CsvToBeanBuilder<UserRecord>(new InputStreamReader(normalCsvSampleAsStream, StandardCharsets.UTF_8)).withType(UserRecord.class).build().iterator();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false).onClose(() -> {
            try {
                normalCsvSampleAsStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error closing stream", e);
            }
        });
    }
}
