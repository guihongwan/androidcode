package com.ghw.jsondemo;

import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

/*
  fromJson()反序列化；toJson()序列化。
Gson.toJson(Object);
Gson.fromJson(Reader,Class);
Gson.fromJson(String,Class);
Gson.fromJson(Reader,Type);
Gson.fromJson(String,Type);
  reference：http://www.jianshu.com/p/c88260adaf5e
 */

public class MainActivity extends AppCompatActivity {
    /**
     * name : 王五
     * gender : man
     * age : 15
     * height : 140
     */

    String jsonarray = "[{ \"UserName\":\"Bill Gates\", \"gender\":\"male\",\"age\":55,\"height\":175 }," +
                    "{ \"UserName\":\"Guihong Wan\", \"gender\":\"femal\",\"age\":20,\"height\":160 }," +
                    "]" ;
    String json = "{ \"UserName\":\"Bill Gates\", \"gender\":\"male\",\"age\":55,\"height\":175 }" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();

        Bean bean = gson.fromJson(json,Bean.class);

        int i = gson.fromJson("100",int.class);
        double d = gson.fromJson("\"99.99\"",double.class);
        boolean b = gson.fromJson("true", boolean.class);

        String jsonstring = gson.toJson(bean);
        Bean bean1 = gson.fromJson(jsonstring,Bean.class);

        Bean[] strings = gson.fromJson(jsonarray,Bean[].class);

        List<Bean> beanList = gson.fromJson(jsonarray, new TypeToken<List<Bean>>(){}.getType());


        //泛型
        String jsonT = "{\"code\":1,\"message\":\"this is a test\",\"data\":{ \"UserName\":\"Bill Gates\", \"gender\":\"male\",\"age\":55,\"height\":175 }}" ;
        Type userType = new TypeToken<Result<Bean>>(){}.getType();
        Result<Bean> userResult = gson.fromJson(jsonT,userType);

        jsonReaderDemo();
        jsonWriterDemo();
        gsonBuilderDemo();

        String jsonboolean = gson.toJson(false);


    }
    private void jsonReaderDemo(){
        String json = "{\"name\":\"ghwan\",\"age\":\"24\"}";
        User user = new User();
        JsonReader reader = new JsonReader(new StringReader(json));
        try {
            reader.beginObject();
            while(reader.hasNext()){
                String s = reader.nextName();
                switch (s){
                    case "name":
                        user.name = reader.nextString();
                        break;
                    case "age":
                        user.age = reader.nextInt();
                        break;
                    case "email":
                        user.email = reader.nextString();
                        break;
                }
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(true){
            Log.d("TAG",""+user.name);
            Log.d("TAG",""+user.age);
            Log.d("TAG",""+user.email);
        }
    }

    private void jsonWriterDemo(){
        // one of several different ways
        Gson gson = new Gson();
        User user = new User("ghw",20,"ghw@icould.com");
        try{
            Appendable appendable = new Appendable() {
                @Override
                public Appendable append(CharSequence csq) throws IOException {
                    Log.d("TAG 1",""+csq.length());
                    return null;
                }

                @Override
                public Appendable append(CharSequence csq, int start, int end) throws IOException {
                    Log.d("TAG 2",""+csq);
                    for(int i = 0; i< csq.length();i++){
                        Log.d("TAG", ""+csq.charAt(i));
                    }
                    return null;
                }

                @Override
                public Appendable append(char c) throws IOException {
                    Log.d("TAG c",""+c);
                    return null;
                }
            };
            gson.toJson(user, (Appendable) appendable);
        }catch (Exception e){
            e.printStackTrace();
        }

        //second way
        try {
            File f = new File("/mnt/sdcard/test.txt");
            if(!f.exists()) f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOutputStream));
            writer.beginObject()
                    .name("name").value("lemen")
                    .name("age").value(2)
                    .name("email").nullValue()
                    .endObject();
            writer.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gsonBuilderDemo(){
        //gson 默认是不会导出null的键值
        Gson gson = new Gson();
        User user = new User("WX",30,null);
        Log.d("TAG",gson.toJson(user));//{"name":"WX","age":30}

        //导出完整的json，当没有值时用null
        Gson gson1 = new GsonBuilder().serializeNulls().create();
        User user1 = new User("WXX",30,null);
        Log.d("TAG",gson.toJson(user1));//{"name":"WXX","age":30, "email":null}???


    }

}
