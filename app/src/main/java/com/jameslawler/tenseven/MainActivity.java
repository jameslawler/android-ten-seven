package com.jameslawler.tenseven;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {

    Calendar currentTime;
    Countdown countdown;
    CountdownState previousCountdownState;
    TextView countdownTextBox;
    TextView countdownLabel;
    LinearLayout countdownContainer;
    Handler countdownHandler;
    Runnable countdownHandlerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCountdown();
    }

    private void startCountdown() {
        currentTime = Calendar.getInstance();
        countdown = new Countdown();
        previousCountdownState = CountdownState.None;
        countdownTextBox = (TextView)findViewById(R.id.countdown);
        countdownLabel = (TextView)findViewById(R.id.countdownLabel);
        countdownContainer = (LinearLayout)findViewById(R.id.countdownContainer);
        countdownHandler = new Handler();
        countdownHandlerTask = new Runnable()
        {
            @Override
            public void run() {
                currentTime.setTimeInMillis(System.currentTimeMillis());
                countdown.Update(currentTime);

                countdownTextBox.setText(countdown.TimeLeft);

                if (countdown.State != previousCountdownState) {
                    previousCountdownState = countdown.State;

                    switch (countdown.State) {
                        case Walk:
                            countdownContainer.setBackgroundResource(R.color.walk);
                            countdownLabel.setText(R.string.countdown_label_walk);
                            break;
                        case Run:
                            countdownContainer.setBackgroundResource(R.color.run);
                            countdownLabel.setText(R.string.countdown_label_run);
                            break;
                        default:
                            countdownContainer.setBackgroundResource(R.color.wait);
                            countdownLabel.setText(R.string.countdown_label_wait);
                    }
                }

                countdownHandler.postDelayed(countdownHandlerTask, 1000);
            }
        };

        countdownHandlerTask.run();
    }
}
