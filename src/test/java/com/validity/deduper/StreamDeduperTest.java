package com.validity.deduper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamDeduperTest {
    @Test
    void streamDeduperTest() {
        List<DedupeColumn<UserRecord>> dedupeFields = Arrays.asList(
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getFirst_name),
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getLast_name),
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getEmail)
        );
        StreamDeduper<UserRecord> deduper = new StreamDeduper<>(dedupeFields);
        UserRecord userRecord1 = new UserRecord();
        userRecord1.setId(1);
        userRecord1.setFirst_name("Nicholas");
        userRecord1.setLast_name("Goodwin");
        userRecord1.setEmail("nick@goodwin.com");
        UserRecord userRecord2 = new UserRecord();
        userRecord2.setId(2);
        userRecord2.setFirst_name("Nicholsa");
        userRecord2.setLast_name("Goodwin");
        userRecord2.setEmail("nick@goodwin.com");
        UserRecord userRecord3 = new UserRecord();
        userRecord3.setId(3);
        userRecord3.setFirst_name("Bill");
        userRecord3.setLast_name("Goodwin");
        userRecord3.setEmail("nick@goodwin.com");
        DedupeResults<UserRecord> dupes = deduper.dedupe(Stream.of(userRecord1, userRecord2, userRecord3));
        Assertions.assertEquals(dupes.getDuplicates().size(), 1, "we should have one duplicate");
        Assertions.assertEquals(dupes.getUnique().size(), 1, "we should have one unique");
    }
}
