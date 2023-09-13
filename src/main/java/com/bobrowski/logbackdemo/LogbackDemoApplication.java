package com.bobrowski.logbackdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogbackDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogbackDemoApplication.class, args);
    }

}