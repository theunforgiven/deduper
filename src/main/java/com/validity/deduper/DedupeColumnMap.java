package com.validity.deduper;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DedupeColumnMap<T> {
    private final DedupeColumn<T> column;
    private final HashMap<String, Set<T>> columnIndex = new HashMap<>();
    private final Metaphone metaphone = new Metaphone();

    public DedupeColumnMap(DedupeColumn<T> column) {
        this.column = column;
    }

    public void index(T t) {
        String columnValue = metaphone.metaphone(column.get(t));
        if (columnIndex.containsKey(columnValue)) {
            columnIndex.get(columnValue).add(t);
        } else {
            Set<T> collectionForKey = new HashSet<>();
            collectionForKey.add(t);
            columnIndex.put(columnValue, collectionForKey);
        }
    }

    public Set<T> duplicatedRows(T item, int thresholdForFuzzyMatch) {
        String columnRawValue = column.get(item);
        String columnMetaphoneValue = metaphone.metaphone(columnRawValue);
        Set<T> roughMatches = columnIndex.get(columnMetaphoneValue);
        return roughMatches.stream().filter(roughMatch -> {
            // Early escape so we don't match on the current row we are working with
            if (roughMatch.equals(item)) {
                return false;
            }

            String roughMatchColumnValue = column.get(roughMatch);
            Integer distance = LevenshteinDistance.getDefaultInstance().apply(columnRawValue, roughMatchColumnValue);
            return distance < thresholdForFuzzyMatch;
        }).collect(Collectors.toSet());
    }
}
