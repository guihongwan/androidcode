package com.ghw.chatpagedemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
	public static String getTimeStr(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = format.format(curDate);
		return str;
	}
	
}
