package com.jameslawler.tenseven;

import junit.framework.TestCase;

import java.util.Calendar;

/**
 * Created by james on 07/09/2015.
 */
public class CountdownTest extends TestCase {

    public void testWhenUpdateCalledWithWalkTimeShouldShowWalk() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 30);
        time.set(Calendar.SECOND, 15);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Walk, countdown.State);
        assertEquals("06:45", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithRunTimeShouldShowRun() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 32);
        time.set(Calendar.SECOND, 15);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Run, countdown.State);
        assertEquals("04:45", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithWaitTimeShouldShowWait() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 34);
        time.set(Calendar.SECOND, 15);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Wait, countdown.State);
        assertEquals("02:45", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithExactlyBeforeSevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 36);
        time.set(Calendar.SECOND, 59);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Wait, countdown.State);
        assertEquals("00:01", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithExactlySevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 37);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Walk, countdown.State);
        assertEquals("10:00", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithStartOfNewCountdown() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 37);
        time.set(Calendar.SECOND, 1);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Walk, countdown.State);
        assertEquals("09:59", countdown.TimeLeft);
    }

    public void testWhenUpdateCalledWithTimeAfterSevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 39);
        time.set(Calendar.SECOND, 35);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown countdown = new Countdown(time);

        // Assert
        assertEquals(CountdownState.Walk, countdown.State);
        assertEquals("07:25", countdown.TimeLeft);
    }
}