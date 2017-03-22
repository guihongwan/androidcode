package com.dosmono.datafile;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(getFilesDir(),"testfile");
        try {
            if (!file.exists()) file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }


        /**
         * one way to write file
          */
//        try {
//            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write("test");
//            bufferedWriter.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        /**
         * second way to write file
         */
//        FileOutputStream fileOutputStream;
//        try{
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write("haha".getBytes());
//            fileOutputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        FileInputStream fileInputStream;
//        try{
//            fileInputStream = new FileInputStream(file);
//            byte[] data= new byte[50];
//            int ret = fileInputStream.read(data);
//            String string = new String(data);
//            Log.d(TAG,"DATA:"+string);
//            fileInputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        /**
         * write external storage
         * don't forget add user permision
         */
        getAlbumStorageDir("test");
        getAlbumStorageDirP(this,"testp");
    }


    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }
    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        Log.d(TAG, "public file:"+file.getAbsolutePath());
        return file;
    }
    public File getAlbumStorageDirP(Context context,String albumName){
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),albumName);
        if(!file.mkdirs()){
            Log.e(TAG,"Direcotory not created");
        }
        Log.d(TAG, "private file:"+file.getAbsolutePath());
        return file;
    }
}
