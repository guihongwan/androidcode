package com.dosmono.asynctaskdemo;

/**
 * 用于模拟网络环境
 * Created by guihong wan on 2017/3/23.
 */

public class NetOperator {
    public void NetOperator(){ }
    public void operate(){
        try{
            Thread.sleep(1000); //1s
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
