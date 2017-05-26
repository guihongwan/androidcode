package com.ghw.myapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Chronometer text_time;
    public static int hour = 0;
    private int startTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_time = (Chronometer) findViewById(R.id.recording_time1);
        text_time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60/60);
                Log.d("~~~~","hour:"+hour);
                if(hour == 0){
                    chronometer.setFormat("00:"+"%s");
                }else if(hour < 10){
                    chronometer.setFormat("0"+"%s");
                }else {
                    chronometer.setFormat("%s");
                }
            }
        });
        text_time.setBase(SystemClock.elapsedRealtime());
        text_time.start();
    }
}
