package com.ghw.jsondemo;

import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/4/18.
 */

public class User {
    public String name;
    public int age;
    public String email;
    public User(){
    }
    public User(String name,int age, @Nullable  String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
