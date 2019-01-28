package com.validity.deduper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = DeduperMain.class)
public class DeduperMain {
    public static void main(String[] args) {
        SpringApplication.run(DeduperMain.class, args);
    }
}
