package com.ghw.chatpagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
            implements View.OnClickListener {
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private EditText message_edit;
    private Button btn_message_send;
    private List<MessageBean> list = new ArrayList<MessageBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first message by server
        MessageBean bean = new MessageBean();
        bean.setMy(false);
        bean.setServiceTime(MyUtils.getTimeStr());
        bean.setServiceMessage("If you have any question, don't hesitate to contact us.");

        list.add(0,bean);

        //edit and send
        message_edit = (EditText)findViewById(R.id.feed_back_edit);
        btn_message_send = (Button)findViewById(R.id.feed_back_send);
        btn_message_send.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.page_chating_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setReverseLayout(true);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        LinearLayoutManager.setReverseLayout(true);
//        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new MessageAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(message_edit.getText() == null) return;
        Collections.reverse(list);//recover to the original order

        MessageBean bean = new MessageBean();
        bean.setMy(true);
        bean.setMyTime(MyUtils.getTimeStr());
        bean.setMyMessage(message_edit.getText().toString());
        list.add(list.size(),bean);

        Collections.reverse(list);//reverse the original order

        message_edit.setText(null);
        adapter.notifyDataSetChanged();
    }
}
