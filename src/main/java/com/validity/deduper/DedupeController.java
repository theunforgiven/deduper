package com.validity.deduper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class DedupeController {
    private final DedupeService dedupeService;

    @Autowired
    public DedupeController(DedupeService dedupeService) {
        this.dedupeService = dedupeService;
    }

    @RequestMapping("/dedupe")
    public String dedupe() {
        DedupedJsonPresentationBean result = dedupeService.runDeduplicationJob();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("Potential Duplicates\n");
        sb.append("<ol>\n");
        result.getPotentialDuplicates().forEach(s -> sb.append("<li>" + s + "</li>\n"));
        sb.append("</ol>\n");
        sb.append("None Duplicates\n");
        sb.append("<ol>\n");
        result.getNonDuplicates().forEach(s -> sb.append("<li>" + s + "</li>\n"));
        sb.append("</ol>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

    @RequestMapping("/dedupe.json")
    public DedupedJsonPresentationBean dedupeJson() {
        return dedupeService.runDeduplicationJob();
    }
    
}

