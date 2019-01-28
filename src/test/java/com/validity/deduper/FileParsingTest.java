package com.validity.deduper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;


class FileParsingTest {

    @Test
    void canParseSimpleSampleFileIntoBeans() {
        Stream<UserRecord> beans = ResourceStreamLoader.parseResource("samples/normal.csv");
        Assertions.assertTrue(beans.count() > 0, "Could not read records from sample file");
    }

    @Test
    void canParseAdvancedSampleFileIntoBeans() {
        Stream<UserRecord> beans = ResourceStreamLoader.parseResource("samples/advanced.csv");
        Assertions.assertTrue(beans.count() > 0, "Could not read records from sample file");
    }
}


