package com.dosmono.fragementbasics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeadlinesFragment extends ListFragment {
    private static final String TAG="HeadlinesFragment";

    String[] cities = {
      "shenzhen",
       "Dallas",
       "Los Angels",
       "Irvine",
    };

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
        return inflater.inflate(R.layout.fragment_headline,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, cities));
    }//use android.R.layout.simple_list_item_1 to deploy the ListView.

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        Log.d(TAG,"onListItemClick"+position);
        mCallbak.onArticleSelected(position);
    }
}
