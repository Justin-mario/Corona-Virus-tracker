package com.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoronavirusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run ( CoronavirusTrackerApplication.class, args );
    }

}
