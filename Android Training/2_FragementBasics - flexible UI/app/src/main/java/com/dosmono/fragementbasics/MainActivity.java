package com.dosmono.fragementbasics;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState != null){
                return;
            }

            ArticleFragment articleFragment = new ArticleFragment();

            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container,articleFragment);
            mFragmentTransaction.addToBackStack(null);

            mFragmentTransaction.commit();
        }
    }
}
