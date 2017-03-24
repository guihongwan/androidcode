package com.ghw.chatpagedemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends Adapter {
	List<MessageBean> lists = new ArrayList<MessageBean>();
	Context context;
	private static final int TYPE_SERVICE = 1 << 2;
	private static final int TYPE_MY = 1 << 3;
	private int padding = 0;

	public MessageAdapter(List<MessageBean> lists) {
		this.lists = lists;
	}

	public MessageAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup mViewGroup, int type) {
		// TODO Auto-generated method stub
		this.context = mViewGroup.getContext();
		int width = ScreenUtil.getScreenWidth((Activity)context) - padding;
		ViewHolder holder = null;

		switch (type) {
		case TYPE_MY:
			View myView = LayoutInflater.from(mViewGroup.getContext()).inflate(
					R.layout.msg_rcy_item1, null);

			holder = new MyViewHolder(myView);

			ViewGroup.LayoutParams params1 = ((MyViewHolder) holder).item1.getLayoutParams();
			params1.width = width;
			((MyViewHolder) holder).item1.setLayoutParams(params1);
			break;
		case TYPE_SERVICE:
			View serviceView = LayoutInflater.from(context).inflate(
					R.layout.msg_rcy_item2, null);
			holder = new ServiceViewHolder(serviceView);
			ViewGroup.LayoutParams params = ((ServiceViewHolder) holder).item2.getLayoutParams();
			params.width = width;
			((ServiceViewHolder) holder).item2.setLayoutParams(params);
			break;
		default:
			break;
		}

		return holder;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// TODO Auto-generated method stub
		
		if (holder.getItemViewType() == TYPE_MY) {
			((MyViewHolder) holder).feedback_rcy_item1_my.setText(lists.get(
					position).getMyMessage());
			((MyViewHolder) holder).feedback_rcy_item1_time.setText(lists.get(
					position).getMyTime());
			int MyWidth = ScreenUtil
					.measureWidth(((MyViewHolder) holder).feedback_rcy_item1_my);
			int MyHeight = ScreenUtil
					.measureHeight(((MyViewHolder) holder).feedback_rcy_item1_my);

			ViewGroup.LayoutParams params = ((MyViewHolder) holder).my_bg
					.getLayoutParams();
			params.height = MyHeight+padding;
			params.width = MyWidth+padding;
			((MyViewHolder) holder).my_bg.setLayoutParams(params);
		}else if (holder.getItemViewType() == TYPE_SERVICE) {
//			ServiceViewHolder

			((ServiceViewHolder) holder).feedback_rcy_item2_service.setText(lists.get(
					position).getServiceMessage());
			if (position == 0) {
				((ServiceViewHolder) holder).feedback_rcy_item2_time.setVisibility(View.GONE);
			}else{
				((ServiceViewHolder) holder).feedback_rcy_item2_time.setText(lists.get(
						position).getServiceTime());
			}
			
			int MyWidth = ScreenUtil
					.measureWidth(((ServiceViewHolder) holder).feedback_rcy_item2_service);
			int MyHeight = ScreenUtil
					.measureHeight(((ServiceViewHolder) holder).feedback_rcy_item2_service);

			ViewGroup.LayoutParams params = ((ServiceViewHolder) holder).service_bg
					.getLayoutParams();
			params.height = MyHeight+padding;
			params.width = MyWidth+padding;
			((ServiceViewHolder) holder).service_bg.setLayoutParams(params);
		}

	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		if (lists.get(position).isMy()) {
			return TYPE_MY;
		} else {
			return TYPE_SERVICE;
		}
	}

	class MyViewHolder extends ViewHolder {
		TextView feedback_rcy_item1_time;
		TextView my_bg;
		TextView feedback_rcy_item1_my;
		ImageView my;
		LinearLayout item1;

		public MyViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			feedback_rcy_item1_time = (TextView) itemView
					.findViewById(R.id.feedback_rcy_item1_time);
			my_bg = (TextView) itemView.findViewById(R.id.my_bg);
			feedback_rcy_item1_my = (TextView) itemView
					.findViewById(R.id.feedback_rcy_item1_my);
			my = (ImageView) itemView.findViewById(R.id.my);
			item1 = (LinearLayout) itemView.findViewById(R.id.item1);
		}
	}

	class ServiceViewHolder extends ViewHolder {
		TextView feedback_rcy_item2_time;
		TextView service_bg;
		TextView feedback_rcy_item2_service;
		ImageView service;
		LinearLayout item2;

		public ServiceViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			feedback_rcy_item2_time = (TextView) itemView
					.findViewById(R.id.feedback_rcy_item2_time);
			service_bg = (TextView) itemView.findViewById(R.id.service_bg);
			feedback_rcy_item2_service = (TextView) itemView
					.findViewById(R.id.feedback_rcy_item2_service);
			service = (ImageView) itemView.findViewById(R.id.service);
			item2 = (LinearLayout) itemView.findViewById(R.id.item2);
		}

	}

}
