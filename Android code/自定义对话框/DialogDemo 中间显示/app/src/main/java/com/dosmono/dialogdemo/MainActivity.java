package com.dosmono.dialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                implements View.OnClickListener, CenterDialog.onCenterItemClickListener {
    private final String TAG = "MainActivity";

    private Button button;
    private CenterDialog centerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(this);
        centerDialog = new CenterDialog(this,R.layout.dialog_layout,new int[]{R.id.dialog_sure});

        centerDialog.setOnCenterItemClickListener(this);
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        Log.d(TAG,"OnCenterItemClick");
    }

    @Override
    public void onClick(View v) {
        centerDialog.show();
        ((TextView)centerDialog.findViewById(R.id.dialog_sure)).setText("设置蓝牙");
        centerDialog.findViewById(R.id.dialog_cancel).setVisibility(View.GONE);
    }
}
