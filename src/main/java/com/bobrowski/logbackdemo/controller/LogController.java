package com.bobrowski.logbackdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class LogController {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @PostMapping("/log")
    public ResponseEntity<String> getRecipe(@RequestParam Long logsNumber) {

        for (int i = 0; i < logsNumber; i++) {
            LOGGER.info("Logger line: {}/n", i);
        }
        return ResponseEntity.ok().body("Logs created.");
    }
}