package com.github.fdehghan4013.reporting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReportingApplication {

    public static void main(String[] args) {
        log.info("Start APP");
        SpringApplication.run(ReportingApplication.class, args);
        log.info("Stop APP");
    }

}
