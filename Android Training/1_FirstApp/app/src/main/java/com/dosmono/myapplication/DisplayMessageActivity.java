package com.dosmono.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent mIntent=this.getIntent();
        TextView mTextView = (TextView)this.findViewById(R.id.TV);
        mTextView.setText(mIntent.getStringExtra(MainActivity.EXTRA_MESSAGE));
    }
}
