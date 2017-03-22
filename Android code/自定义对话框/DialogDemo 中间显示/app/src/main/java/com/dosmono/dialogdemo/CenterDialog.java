package com.dosmono.dialogdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * A customized Dialog
 * @author wanguihong on 2017/3/22.
 */

public class CenterDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private int layoutResID;
    private int[] listenedItems;//要监听的控件ID
    private onCenterItemClickListener listener;

    public interface onCenterItemClickListener{
        void OnCenterItemClick(CenterDialog dialog, View view);
    }

    /**
     * Constructor
     */
    public CenterDialog( Context context) {
        super(context);
    }
    public CenterDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_menu_animation);
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = display.getWidth()*4/5;
        getWindow().setAttributes(layoutParams);

        setCanceledOnTouchOutside(true);

        for(int id:listenedItems){
            findViewById(id).setOnClickListener(this);
        }
    }

    public void setOnCenterItemClickListener(onCenterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        listener.OnCenterItemClick(this, view);
    }
}
