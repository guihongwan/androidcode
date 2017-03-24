package com.ghw.chatpagedemo;

import android.util.Log;

public class MessageBean {
	private final String TAG = "MessageBean";
	private final boolean DEBUG = true;

	private String myTime;
	private String myMessage;
	private String serviceTime;
	private String serviceMessage;
	private boolean isMy = true;
	
	public boolean isMy() {
		return isMy;
	}
	public void setMy(boolean isMy) {
		this.isMy = isMy;
	}
	public String getMyTime() {
		return myTime;
	}
	public void setMyTime(String myTime) {
		this.myTime = myTime;
	}
	public String getMyMessage() {
		return myMessage;
	}
	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getServiceMessage() {
		return serviceMessage;
	}
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}
	public void print(){
		Log.d(TAG, "myMessage:"+myMessage);
		Log.d(TAG, "serviceMessage:"+serviceMessage);
	}
	
}
