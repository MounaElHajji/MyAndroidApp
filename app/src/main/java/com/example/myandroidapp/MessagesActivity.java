package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.ConversationAdapter;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;
import com.example.myandroidapp.Adapters.ConversationAdapter;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;
import com.example.myandroidapp.Models.Message;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity implements ListMessagesListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Conversation> listConversations;

    // retrofit
    RetrofitS retrofit= new RetrofitS();
    ApiInterface api =retrofit.getRetrofitInstance().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        recyclerView = findViewById(R.id.conversationsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listConversations = getListConversations();

        adapter = new ConversationAdapter(listConversations,this,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClicked(Conversation conversation) {
        Intent i = new Intent(this,ChatActivity.class);
        startActivity(i);
    }

    public List<Conversation> getListConversations() {

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


        /*
        api.getConversations(4).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                // display response as string
                //listConversations = response.body();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
               // Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });

         */

        return listConversations;
    }

}