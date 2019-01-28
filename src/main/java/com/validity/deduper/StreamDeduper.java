package com.validity.deduper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDeduper<T> {
    private final List<DedupeColumn<T>> dedupeColumns;
    private final int thresholdForFuzzyMatch = 6;

    public StreamDeduper(List<DedupeColumn<T>> dedupeColumns) {
        this.dedupeColumns = dedupeColumns;
    }

    public DedupeResults<T> dedupe(Stream<T> userRecordStream) {
        LinkedList<T> totalSet = new LinkedList<>();
        DedupeColumnMap<T>[] invertedIndexes = dedupeColumns.stream().map(DedupeColumnMap<T>::new).toArray(DedupeColumnMap[]::new);
        userRecordStream.forEach(t -> {
            totalSet.add(t);
            for (DedupeColumnMap<T> index : invertedIndexes) {
                index.index(t);
            }
        });


        HashSet<T> identifiedDupes = new HashSet<>();
        HashMap<T, Set<T>> deduped = new HashMap<>();
        Set<T> unique = new HashSet<>();

        for (T item : totalSet) {
            if(identifiedDupes.contains(item)){
                continue;
            }

            Set<T> dupes = findDuplicates(item, invertedIndexes);
            if (dupes.size() > 0) {
                identifiedDupes.addAll(dupes);
                deduped.put(item, dupes);
            } else {
                unique.add(item);
            }

        }
        return new DedupeResults<>(deduped, unique);
    }

    private Set<T> findDuplicates(T item, DedupeColumnMap<T>[] invertedIndexes) {
        List<Set<T>> collect = Arrays.stream(invertedIndexes).map(tDedupeColumnMap -> tDedupeColumnMap.duplicatedRows(item, thresholdForFuzzyMatch)).collect(Collectors.toList());
        return collect.stream().reduce((ts, ts2) -> ts.stream().filter(ts2::contains).collect(Collectors.toSet())).orElse(new HashSet<>());
    }
}
