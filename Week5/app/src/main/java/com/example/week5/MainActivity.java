package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    CalendarView simpleCalendarView;
    int progress = 0;
    ProgressBar simpleProgressBar;
    ProgressBar simpleProgressBar2;
    Button b1, b2;
    ProgressDialog progressDialog;
    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setFocusedMonthDateColor(Color.RED);
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE);
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED);
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN);

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month +
                        "/" + year, Toast.LENGTH_LONG).show();
            }
        });

        simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        simpleProgressBar2 = (ProgressBar) findViewById(R.id.progressBarH);
        simpleProgressBar.setVisibility(View.INVISIBLE);

        Button startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleProgressBar.setVisibility(View.VISIBLE);
                setProgressValue(progress);
            }
        });

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            Handler handle = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(2);
                }};

            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax())
                            {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog.getMax())
                                {
                                    progressDialog.dismiss();
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        addListenerOnButton();
    }

    private void setProgressValue(final int progress) {
        simpleProgressBar2.setProgress(progress);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();
    }

    public void addListenerOnButton() {
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 :").append(toggleButton2.getText());
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }});
    }
}