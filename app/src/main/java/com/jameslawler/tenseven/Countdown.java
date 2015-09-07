package com.jameslawler.tenseven;

import java.util.Calendar;

/**
 * Created by james on 07/09/2015.
 */
public class Countdown {
    public CountdownState State;
    public String TimeLeft;

    private final int WalkUntilSecondsLeft = 5 * 60;
    private final int RunUntilSecondsLeft = 4 * 60;

    public void Update(Calendar currentTime) {
        int currentMinute = currentTime.get(Calendar.MINUTE);
        int currentSecond = currentTime.get(Calendar.SECOND);
        int currentMinuteMod = currentMinute % 10;
        Integer minutesLeft;

        if (currentMinuteMod >= 7) {
            minutesLeft = 17 - currentMinuteMod;
        } else {
            minutesLeft = 7 - currentMinuteMod;
        }

        int totalSecondsLeft = (minutesLeft * 60) - currentSecond;

        if (totalSecondsLeft > this.WalkUntilSecondsLeft) {
            this.State = CountdownState.Walk;
        } else if (totalSecondsLeft > this.RunUntilSecondsLeft) {
            this.State = CountdownState.Run;
        } else {
            this.State = CountdownState.Wait;
        }

        Integer minutes = totalSecondsLeft / 60;
        Integer seconds = totalSecondsLeft % 60;

        this.TimeLeft = String.format("%02d:%02d", minutes, seconds);
    }
}
