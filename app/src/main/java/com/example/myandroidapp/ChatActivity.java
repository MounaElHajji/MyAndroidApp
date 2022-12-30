package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.MessageAdapter;
import com.example.myandroidapp.Models.Message;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<Message> listMessages;
    LinearLayoutManager layoutManager;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);    // to show the recyclerView list from the bottom
        recyclerView.setLayoutManager(layoutManager);

        listMessages = getListMessages();

        /*
        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            listMessages.add( new Message(
                    Message.LAYOUT_ONE, bundle.getString("newMsg"), "20/12/2022"
            ));
        }
         */

        adapter = new MessageAdapter(listMessages,ChatActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    ///////// Every 2 miliseconds this method will be run (update the recyclerView) :

    @Override
    protected void onResume() {

        listMessages = getListMessages(); // this line shld go inside the run() method

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                adapter = new MessageAdapter(listMessages,ChatActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                // recyclerView.scrollToPosition(6);

                // System.out.println("agaiiiiiiin");

            }
        }, delay);
        super.onResume();
    }

    /////////

    public void onBackClick(View view) {
        finish();
    }


    public void onSendClick(View view) {

        EditText newMessageText = (EditText) findViewById(R.id.inputMessage);
        String newMessage = newMessageText.getText().toString();

        listMessages = getListMessages();

        /// --- this is optional, just for the frontend now, shld be replaced by the msg saving method of backend
        listMessages.add( 0,new Message(
                Message.LAYOUT_ONE, newMessage, "20/12/2022"
        ));

        adapter = new MessageAdapter(listMessages,ChatActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /// ---

        newMessageText.setText("");

        /*
        Intent i = new Intent(ChatActivity.this, ChatActivity.class);
        i.putExtra("newMsg", newMessage );
        startActivity(i);

         */

        /*
        int insertIndex = 1;
        listMessages.add( insertIndex, new Message(
                Message.LAYOUT_ONE, newMessage, "20/12/2022"
        ));

        adapter.notifyDataSetChanged();
//        adapter.notifyItemInserted(insertIndex);

         */

        /*
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

         */
    }

    // here, we should select the date from database

    public ArrayList<Message> getListMessages() {

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

        return (ArrayList<Message>) listMessages;
    }

}