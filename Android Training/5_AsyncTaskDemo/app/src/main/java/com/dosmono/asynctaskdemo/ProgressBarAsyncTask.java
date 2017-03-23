package com.dosmono.asynctaskdemo;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * usage: 生成该类的对象，并调用execute
 * 首先执行onPreExecute
 * 再执行doInBackground
 *
 * Created by guihong wan on 2017/3/23.
 */

public class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {
    private TextView textView;
    private ProgressBar progressBar;

    public ProgressBarAsyncTask(TextView textView, ProgressBar progressBar){
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();

        int progress;
        for( progress = 10; progress <= 100; progress += 10 ){
            netOperator.operate();
            publishProgress(progress);
        }
        return ""+progress+" "+params[0].intValue();
    }

    @Override
    protected void onPreExecute(){
        textView.setText("start to operate...");
    }

    @Override
    protected void onPostExecute(String ret){
        textView.setText("operation is end.  "+ret);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int vlaue = values[0];
        progressBar.setProgress(vlaue);
    }
}
