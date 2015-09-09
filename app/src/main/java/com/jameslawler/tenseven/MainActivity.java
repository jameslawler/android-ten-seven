package com.jameslawler.tenseven;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Bind(R.id.countdown) TextView countdownTextBox;
    @Bind(R.id.countdownLabel) TextView countdownLabel;
    @Bind(R.id.countdownContainer) LinearLayout countdownContainer;

    @BindString(R.string.countdown_label_walk) String countdownLabelWalk;
    @BindString(R.string.countdown_label_run) String countdownLabelRun;
    @BindString(R.string.countdown_label_wait) String countdownLabelWait;

    Calendar currentTime;
    Countdown countdown;
    CountdownState previousCountdownState;
    Handler countdownHandler;
    Runnable countdownHandlerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        startCountdown();
    }

    private void startCountdown() {
        currentTime = Calendar.getInstance();
        countdown = new Countdown();
        previousCountdownState = CountdownState.None;
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
                            countdownLabel.setText(countdownLabelWalk);
                            break;
                        case Run:
                            countdownContainer.setBackgroundResource(R.color.run);
                            countdownLabel.setText(countdownLabelRun);
                            break;
                        default:
                            countdownContainer.setBackgroundResource(R.color.wait);
                            countdownLabel.setText(countdownLabelWait);
                    }
                }

                countdownHandler.postDelayed(countdownHandlerTask, 1000);
            }
        };

        countdownHandlerTask.run();
    }
}
