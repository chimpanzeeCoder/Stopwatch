package com.example.timers;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView counterView;
    CountDownTimer timer;
    Button button;

    ListView listView;
    ArrayList<String> laps;
    ArrayAdapter arrayAdapter;

    boolean counterActive = true;
    int deciSeconds, lapCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = findViewById(R.id.counterView);
        listView = findViewById(R.id.listView);
        laps = new ArrayList<>();
        button = findViewById(R.id.startPauseButton);

        timer = new CountDownTimer(0, 0 ) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }

    void updateTimer(int deciSecs) {
        int min = deciSecs / 600;
        int sec = deciSecs/10 - min * 60;
        int dSec = deciSecs - (min*600 + sec*10);

        String m = min < 10 ? "0"+min : ""+min;
        String s = sec < 10 ? "0"+sec : ""+sec;

        counterView.setText(m +":"+ s+":"+dSec);
    }

    public void startPause(View view) {
        if(counterActive) {
            button.setText("STOP");
            counterActive = false;

            timer = new CountDownTimer(Long.MAX_VALUE, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    deciSeconds++;
                    updateTimer(deciSeconds);
                }

                @Override
                public void onFinish() {

                }
            }.start();
        } else {
            button.setText("START");
            counterActive = true;

            timer.cancel();
        }
    }

    public void resetTimer(View view) {
        counterView.setText("00:00:0");
        deciSeconds = 0;
        button.setText("START");
        counterActive = true;
        timer.cancel();

        laps.clear();
        listView.setAdapter(null);
        lapCounter = 0;
    }

    public void lap(View view) {
        lapCounter++;
        laps.add("LAP "+lapCounter+": "+counterView.getText());

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, laps);
        listView.setAdapter(arrayAdapter);
    }
}
