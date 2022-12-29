package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.ConversationAdapter;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;
import com.example.myandroidapp.Adapters.ConversationAdapter;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity implements ListMessagesListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Conversation> listConversations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        recyclerView = findViewById(R.id.conversationsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listConversations = new ArrayList<>();

        for(int i=0 ; i<10 ; i++) {
            Conversation conversation = new Conversation(
                    "First name " + i,
                    "Last name " + i,
                    "https://i.pinimg.com/736x/44/9e/5e/449e5e93c14f34fb7b90559e4f9908ab.jpg",
                    "Service " + i
            );

            listConversations.add(conversation);
        }

        adapter = new ConversationAdapter(listConversations,this,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClicked(Conversation conversation) {
        Intent i = new Intent(this,ChatActivity.class);
        startActivity(i);
    }

}