package com.dosmono.datadb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/22.
 */

public class RestaurantHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="lunchlist.db";
    private static final int SCHEMA_VERSION=2;

    public RestaurantHelper(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_VERSION);//to use for creating cursor objects, or null for the default
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                " address TEXT, type TEXT, notes TEXT, phone TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion ==1 && newVersion ==2){
            db.execSQL("ALTER TABLE restaurants ADD phone TEXT;");
        }
    }

    public Cursor getAll(String where, String orderBy){
        StringBuilder buf = new StringBuilder(
                "SELECT _id, name, address, type, notes, phone FROM restaurants");
        if(where != null){
            buf.append(" WHERE ");
            buf.append(where);
        }
        if(orderBy != null){
            buf.append(" ORDER BY ");
            buf.append(orderBy);
        }
        return(getReadableDatabase().rawQuery(buf.toString(),null));
    }

    public Cursor getById(String id){
        String[] args={id};
        return (getReadableDatabase().rawQuery("SELECT _id, name, address, type, notes, phone" +
                " FROM restaurants WHERE _id=? ",args));
    }

    public void insert(String name, String address, String type, String notes, String phone){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("address",address);
        contentValues.put("type", type);
        contentValues.put("notes", notes);
        contentValues.put("phone",phone);

        getWritableDatabase().insert("restaurants", "name", contentValues);
    }

    public void update(String id, String name, String address, String type, String notes, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address",address);
        contentValues.put("type", type);
        contentValues.put("notes", notes);
        contentValues.put("phone",phone);

        String[] args = {id};

        getWritableDatabase().update("restaurants",contentValues,"_id=?",args);
    }


}
