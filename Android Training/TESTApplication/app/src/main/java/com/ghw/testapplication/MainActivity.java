package com.dosmono.vacation.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dosmono.vacation.R;

import java.util.ArrayList;

import adapter.ContactViewPagerAdapter;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.TextFragment;

public class TextActivity extends BaseActivity{

    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private ContactViewPagerAdapter mAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new TextFragment());
        fragments.add(new TextFragment());
        mAdapter = new ContactViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(mAdapter);
        tab.setupWithViewPager(viewpager);

//        tab.addTab(tab.newTab().setText("Tab1"));//添加一个Tab
//        tab.setTabMode(TabLayout.MODE_SCROLLABLE);//设置Tab可以滚动
//        tab.setTabMode(TabLayout.MODE_FIXED);//设置Tab布满TabLayout(默认)
//        tab.setSelectedTabIndicatorColor(0xfff);//指示器颜色
//        tab.setSelectedTabIndicatorHeight(10);//指示器高度
//        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));//让TabLayout成为ViewPager的指示器

    }


}
