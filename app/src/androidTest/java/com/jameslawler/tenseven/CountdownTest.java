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
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Walk, Countdown.getInstance().State);
        assertEquals("06:45", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithRunTimeShouldShowRun() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 32);
        time.set(Calendar.SECOND, 15);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Run, Countdown.getInstance().State);
        assertEquals("04:45", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithWaitTimeShouldShowWait() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 34);
        time.set(Calendar.SECOND, 15);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Wait, Countdown.getInstance().State);
        assertEquals("02:45", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithExactlyBeforeSevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 36);
        time.set(Calendar.SECOND, 59);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Wait, Countdown.getInstance().State);
        assertEquals("00:01", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithExactlySevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 37);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Walk, Countdown.getInstance().State);
        assertEquals("10:00", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithStartOfNewCountdown() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 37);
        time.set(Calendar.SECOND, 1);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Walk, Countdown.getInstance().State);
        assertEquals("09:59", Countdown.getInstance().TimeLeft);
    }

    public void testWhenUpdateCalledWithTimeAfterSevenPast() {
        // Arrange
        Calendar time = Calendar.getInstance();

        time.set(Calendar.HOUR_OF_DAY, 17);
        time.set(Calendar.MINUTE, 39);
        time.set(Calendar.SECOND, 35);
        time.set(Calendar.MILLISECOND, 0);

        // Act
        Countdown.getInstance().Update(time);

        // Assert
        assertEquals(CountdownState.Walk, Countdown.getInstance().State);
        assertEquals("07:25", Countdown.getInstance().TimeLeft);
    }
}