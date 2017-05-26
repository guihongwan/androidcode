package com.dosmono.fragementbasics;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadlinesFragmentself extends ListFragment {
    private static final String TAG="HeadlinesFragmentself";

    OnHeadlineSeletedListener mCallbak;

    public interface OnHeadlineSeletedListener{
        public void onArticleSelected(int position);
    }
    @Override
    public void onAttach(Context mContext){
        super.onAttach(mContext);
        try{
            mCallbak = (OnHeadlineSeletedListener)mContext;
        }catch (ClassCastException e){
            throw new ClassCastException(mContext.toString()+" must implement OnHeadlineSeletedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_headlineself,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        final String[] from = new String[]{"title","info"};
        final int[] to = new int[]{R.id.text1,R.id.text2};

        super.onCreate(savedInstanceState);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),getSimpleData(),R.layout.two_textview,from,to);
        setListAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getSimpleData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("title","title1");
        map.put("info","info1");
        list.add(map);

        map.put("title","title2");
        map.put("info","info2");
        list.add(map);
        return list;
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        Log.d(TAG,"onListItemClick"+position);
        mCallbak.onArticleSelected(position);
    }
}
