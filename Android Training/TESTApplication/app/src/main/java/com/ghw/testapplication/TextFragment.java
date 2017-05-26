package com.ghw.testapplication;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dosmono.vacation.R;

import java.util.ArrayList;
import java.util.List;

import adapter.recycle.CommonAdapter;
import adapter.recycle.ViewHolder;
import base.BaseFragment;
import model.ReportReq;

public class TextFragment extends BaseFragment {

    private RecyclerView recycler;
    private List<ReportReq> datas;
    private CommonAdapter<ReportReq> commonAdapter;

    @Override
    protected void initHeader(ImageView back, TextView title, ImageView add) {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
    }

    @Override
    protected void initView() {
        recycler=findView(R.id.recycler);
        datas=new ArrayList<>();
//        反馈类型 1：普通反馈帮助 2:骚扰 3：欺诈 4：色情 5：侵权 6：违法犯罪 7：其他
        datas.add(new ReportReq("2", "总是发送骚扰信息"));
        datas.add(new ReportReq("3", "欺诈"));
        datas.add(new ReportReq("4", "发送色情信息"));
        datas.add(new ReportReq("5", "侵权"));
        datas.add(new ReportReq("6", "违法犯罪"));
        datas.add(new ReportReq("7", "其他"));
        datas.add(new ReportReq("2", "总是发送骚扰信息"));
        datas.add(new ReportReq("3", "欺诈"));
        datas.add(new ReportReq("4", "发送色情信息"));
        datas.add(new ReportReq("5", "侵权"));
        datas.add(new ReportReq("6", "违法犯罪"));
        datas.add(new ReportReq("7", "其他"));
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        commonAdapter=new CommonAdapter<ReportReq>(getContext(),R.layout.report_item,datas) {
            @Override
            protected void convert(ViewHolder holder, ReportReq reportReq, int position) {
                holder.setText(R.id.type_tv,reportReq.getDesc());
            }
        };
        recycler.setAdapter(commonAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_frag;
    }
}
