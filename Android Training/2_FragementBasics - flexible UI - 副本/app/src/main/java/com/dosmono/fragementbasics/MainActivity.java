package com.dosmono.fragementbasics;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSeletedListener{
public class MainActivity extends AppCompatActivity implements HeadlinesFragmentself.OnHeadlineSeletedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState != null){
                return;
            }
//            HeadlinesFragment mHeadlineFragment = new HeadlinesFragment();
            HeadlinesFragmentself mHeadlineFragment = new HeadlinesFragmentself();
            mHeadlineFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,mHeadlineFragment).commit();
        }
    }

    public void onArticleSelected(int position){
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ArticleFragment.ARG_POSITION, 2);
        articleFragment.setArguments(args);

        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container,articleFragment);
        mFragmentTransaction.addToBackStack(null);

        mFragmentTransaction.commit();
    }
}
