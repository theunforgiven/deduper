package com.validity.deduper;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DedupeService {
    public DedupedJsonPresentationBean runDeduplicationJob() {
        Stream<UserRecord> userRecordStream = ResourceStreamLoader.parseResource("samples/normal.csv");
        List<DedupeColumn<UserRecord>> dedupeFields = Arrays.asList(
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getFirst_name),
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getLast_name),
                DedupeColumn.forClassField(UserRecord.class, UserRecord::getEmail)
        );
        StreamDeduper<UserRecord> deduper = new StreamDeduper<>(dedupeFields);
        DedupeResults<UserRecord> results = deduper.dedupe(userRecordStream);

        List<String> uniqueUserRecordCsvRows = results.getUnique().stream().map(DedupeService::userRecordToString).collect(Collectors.toList());
        List<String> duplicateUserRecordCsvRows = results.getDuplicates().entrySet().stream().flatMap(userRecordSetEntry -> {
            return Stream.concat(Stream.of(userRecordToString(userRecordSetEntry.getKey())),userRecordSetEntry.getValue().stream().map(DedupeService::userRecordToString));
        }).collect(Collectors.toList());
        return new DedupedJsonPresentationBean(uniqueUserRecordCsvRows, duplicateUserRecordCsvRows);
    }

    private static String userRecordToString(UserRecord userRecord) {
        return userRecord.toString();
    }
}
