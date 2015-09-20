package com.jameslawler.tenseven;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trello.rxlifecycle.components.RxActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity  extends RxActivity {

    @Bind(R.id.countdown) TextView countdownTextBox;
    @Bind(R.id.countdownLabel) TextView countdownLabel;
    @Bind(R.id.countdownContainer) LinearLayout countdownContainer;

    @BindString(R.string.countdown_label_walk) String countdownLabelWalk;
    @BindString(R.string.countdown_label_run) String countdownLabelRun;
    @BindString(R.string.countdown_label_wait) String countdownLabelWait;

    Countdown previousCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .map(l -> Countdown.getInstance().Update(Calendar.getInstance()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(c -> displayCountdown(c));
    }

    private void displayCountdown(Countdown countdown) {
        countdownTextBox.setText(countdown.TimeLeft);

        if (previousCountdown == null || countdown.State != previousCountdown.State) {
            previousCountdown = countdown;

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
    }
}
