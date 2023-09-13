package com.bobrowski.logbackdemo.appender;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;

import java.lang.reflect.Field;

@NoAutoStart
public class MySizeAndTimeBasedFNATP<E> extends SizeAndTimeBasedFNATP<E> {
    public void prepareForForcedRollover() throws NoSuchFieldException, IllegalAccessException {
        prepareNameForRollingFile();
        setNextCheckTimeAndUpdateCurrentPeriodTime();
        resetSizePeriodsCounter();
    }

    private void prepareNameForRollingFile() {
        super.elapsedPeriodsFileName = super.getCurrentPeriodsFileNameWithoutCompressionSuffix();
    }

    private void resetSizePeriodsCounter() throws NoSuchFieldException, IllegalAccessException {
        Field currentPeriodsCounter = getClass().getSuperclass().getDeclaredField("currentPeriodsCounter");
        currentPeriodsCounter.setAccessible(true);
        currentPeriodsCounter.setInt(this, 0);
    }

    private void setNextCheckTimeAndUpdateCurrentPeriodTime() {
        long currentTime = getCurrentTime();
        long nextCheckCandidate = computeNextCheck(currentTime);
        atomicNextCheck.set(nextCheckCandidate);
        setDateInCurrentPeriod(currentTime);
    }
}