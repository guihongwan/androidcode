package com.dosmono.fragementbasics;
;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author guihongwan on 2017/3/17.
 */

public class ArticleFragment extends Fragment {
    private static final String TAG = "ArticleFragment";

    public static String ARG_POSITION = "arg_position";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content,container,false);
    }
}
