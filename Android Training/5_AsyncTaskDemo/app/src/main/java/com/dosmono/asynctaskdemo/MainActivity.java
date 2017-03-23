package com.dosmono.asynctaskdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);

        ProgressBarAsyncTask task = new ProgressBarAsyncTask(textView, progressBar);
        task.execute(1000);
    }
}
