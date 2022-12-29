package com.example.myandroidapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.MessageAdapter;
import com.example.myandroidapp.Adapters.MessageAdapter;
import com.example.myandroidapp.Models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<Message> listMessages;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);    // to show the recyclerView list from the bottom
        recyclerView.setLayoutManager(layoutManager);


        listMessages = new ArrayList<>();

        //////// msgs must be selected from newest to oldest

        listMessages.add( new Message(
                Message.LAYOUT_TWO, "what's up !!!!!!!!!!!!!", "18/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "test test", "17/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "16/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_ONE, "???", "15/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "it's me", "12/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_ONE, "hi, who is this ?", "12/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "come on answer", "12/12/2022"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "hello Kawtar", "12/12/2022"
        ));


        adapter = new MessageAdapter(listMessages,ChatActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onBackClick(View view) {
        finish();
    }

}