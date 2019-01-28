package com.validity.deduper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DedupeResults<T> {
    private final HashMap<T, Set<T>> deduped;
    private final Set<T> unique;

    public DedupeResults(HashMap<T, Set<T>> deduped, Set<T> unique) {
        this.deduped = deduped;
        this.unique = unique;
    }

    public HashMap<T, Set<T>> getDuplicates() {
        return deduped;
    }

    public Set<T> getUnique() {
        return unique;
    }
}
