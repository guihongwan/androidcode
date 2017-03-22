package com.dosmono.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE="com.dosmono.MyApplication.message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SendMessage(View view){
        Intent mIntent=new Intent(this,DisplayMessageActivity.class);
        EditText mEditText = (EditText)this.findViewById(R.id.edit_message);
        String message = mEditText.getText().toString();
        mIntent.putExtra(EXTRA_MESSAGE,message);
        this.startActivity(mIntent);
    }

}
