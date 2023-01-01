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
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Message;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<Message> listMessages;
    LinearLayoutManager layoutManager;

    List<Message> listMessagesToDisplay;

    // retrofit
    RetrofitS retrofit= new RetrofitS();
    ApiInterface api =retrofit.getRetrofit().create(ApiInterface.class);

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);    // to show the recyclerView list from the bottom
        recyclerView.setLayoutManager(layoutManager);

        int i = 0;
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

        for ( Message msg : listMessages ) {
            if (msg.getPersonFrom().equals(4)) {
                listMessagesToDisplay.add( i,new Message(
                        Message.LAYOUT_ONE, msg.getMessage(), msg.getDate()
                ));
                i++;
            }
            else if (msg.getPersonTo().equals(4)) {

                listMessagesToDisplay.add( i,new Message(
                        Message.LAYOUT_TWO, msg.getMessage(), msg.getDate()
                ));
                i++;
            }
        }

        adapter = new MessageAdapter(listMessagesToDisplay,ChatActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    ///////// Every 5 seconds this method will be run (update the recyclerView) :

    @Override
    protected void onResume() {

        listMessages = getListMessages(); // this line shld go inside the run() method

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);

                int itemsOldCount = recyclerView.getAdapter().getItemCount();

                // get new data :

                LinearLayoutManager layoutManager = ((LinearLayoutManager)recyclerView.getLayoutManager());
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();

                // get new data :
                // listMessages = getListMessages();
                int itemsNewCount = recyclerView.getAdapter().getItemCount();
                int itemsDecalage = itemsNewCount - itemsOldCount;

                adapter = new MessageAdapter(listMessages,ChatActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                recyclerView.getLayoutManager().scrollToPosition(firstVisiblePosition + itemsDecalage);

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

        /*
        /// --- this is optional, just for the frontend now, shld be replaced by the msg-saving method of backend
        listMessages.add( 0,new Message(
                Message.LAYOUT_ONE, newMessage, "20/12/2022 18:02"
        ));

         */

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

    public List<Message> getListMessages() {

        listMessages = new ArrayList<>();

        //////// msgs must be selected from newest to oldest

        /*

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
                Message.LAYOUT_TWO, "it's me", "12/12/2022 "
        ));
        listMessages.add( new Message(
                Message.LAYOUT_ONE, "hi, who is this ?", "12/12/2022 22:00"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "come on answer", "12/12/2022 20:02"
        ));
        listMessages.add( new Message(
                Message.LAYOUT_TWO, "hello Kawtar", "12/12/2022 18:02"
        ));

         */

        api.getChatMsgs(4,1).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                // display response as string
                listMessages = response.body();
                System.out.println("got it");
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                // Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });

        return listMessages;
    }

}