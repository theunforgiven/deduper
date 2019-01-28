package com.validity.deduper;

import java.util.List;

public class DedupedJsonPresentationBean {
    private final List<String> nonDuplicates;
    private final List<String> potentialDuplicates;

    public DedupedJsonPresentationBean(List<String> nonDuplicates, List<String> potentialDuplicates) {
        this.nonDuplicates = nonDuplicates;
        this.potentialDuplicates = potentialDuplicates;
    }

    public List<String> getNonDuplicates() {
        return nonDuplicates;
    }

    public List<String> getPotentialDuplicates() {
        return potentialDuplicates;
    }
}
