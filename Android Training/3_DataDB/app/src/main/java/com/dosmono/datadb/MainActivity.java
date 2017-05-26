package com.dosmono.datadb;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    Cursor model = null;
    RestaurantHelper restaurantHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantHelper = new RestaurantHelper(this);
        initData();
        initList();
    }
    //you'd better use those methods in background threads.
    private void initData(){
        restaurantHelper.insert("NAME1","LA","","","");
        restaurantHelper.insert("NAME2","NY","","","");
    }
    private void initList(){
        if(model != null){
            model.close();;
        }

        String where = null;
        where="name LIKE \"%"+"科技园"+"%\"";

        model = restaurantHelper.getAll(null,null);
        while (model.moveToNext()){
            Log.d("TAG",model.getString(1)+" "+model.getString(2));
        }
        model.close();
    }
}
