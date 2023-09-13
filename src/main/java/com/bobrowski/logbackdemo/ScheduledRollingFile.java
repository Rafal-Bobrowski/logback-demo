package com.bobrowski.logbackdemo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.rolling.*;
import com.bobrowski.logbackdemo.appender.MySizeAndTimeBasedFNATP;
import com.bobrowski.logbackdemo.controller.LogController;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledRollingFile {

    @Scheduled(cron = "0 * * * * *")
    public void forceRolloverOfFile() {

        Logger logger = (Logger) LoggerFactory.getLogger(LogController.class);
        Appender<?> appender = logger.getAppender("LOGBACK_TEST");

        if (appender instanceof RollingFileAppender<?> rollingFileAppender) {
            try {
                TimeBasedRollingPolicy<?> triggeringPolicy = (TimeBasedRollingPolicy<?>)rollingFileAppender.getTriggeringPolicy();
                MySizeAndTimeBasedFNATP<?> timeBasedFileNamingAndTriggeringPolicy = (MySizeAndTimeBasedFNATP<?>) triggeringPolicy.getTimeBasedFileNamingAndTriggeringPolicy();
                timeBasedFileNamingAndTriggeringPolicy.prepareForForcedRollover();
                rollingFileAppender.rollover();
            } catch (Exception e) {
                System.out.println("Unable due to exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}