package com.ghw.jsondemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/4/18.
 */

public class Bean {
    /**
     * name : 王五
     * gender : man
     * age : 15
     * height : 140
     */
//    @SerializedName("Name")
    @SerializedName(value = "name", alternate = {"Name","UserName"})
    private String name;
    private String gender;
    private int age;
    private int height;

    public Bean() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
