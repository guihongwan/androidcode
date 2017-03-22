package com.dosmono.datakv;
;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
        return inflater.inflate(R.layout.fragment_article,container,false);
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Context context = getActivity();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(
//                getString(R.string.preference_file_key),Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.exam_key),452);
        editor.commit();
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int defaultVaule = getResources().getInteger(R.integer.default_score);
        long score = sharedPreferences.getInt(getString(R.string.exam_key),defaultVaule);
        Log.d(TAG,"score:"+score);
    }

    }
