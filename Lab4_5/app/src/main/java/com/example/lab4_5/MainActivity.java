package com.example.lab4_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DigitalClock;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DigitalClock simpleAnalogClock;
    Chronometer simpleChronometer;
    Button start, stop, restart;
    private long pauseOffset;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleAnalogClock = (DigitalClock)
                findViewById(R.id.digitalClock);

        simpleAnalogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Digital Clock",
                        Toast.LENGTH_SHORT).show();
            }
        });

        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        restart = (Button) findViewById(R.id.btnRestart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPaused) {
                    simpleChronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    simpleChronometer.start();
                } else {
                    simpleChronometer.start();
                }
                isPaused = false;
            }});

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPaused) {
                    simpleChronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    isPaused = true;
                }
            }});

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleChronometer.stop();
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                isPaused = false;
            }});
    }
}