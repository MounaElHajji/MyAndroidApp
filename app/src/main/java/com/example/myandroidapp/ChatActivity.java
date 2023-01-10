package com.example.myandroidapp;

import static com.example.myandroidapp.Adapters.MessageAdapter.LAYOUT_ONE;
import static com.example.myandroidapp.Adapters.MessageAdapter.LAYOUT_TWO;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageAdapter adapter;
    List<Message> listMessages = new ArrayList<>();
    LinearLayoutManager layoutManager;

    List<Message> listMessagesToDisplay = new ArrayList<>();

    //
    String theirName, theirTypeProfil, theirImage, theirStringId;
    int theirId;
    TextView nameTextView;

    // retrofit
    RetrofitS retrofit= new RetrofitS();
    ApiInterface api = retrofit.getRetrofitInstance().create(ApiInterface.class);

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get the session's id :
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int myId = sh.getInt("myId",0);

        nameTextView = findViewById(R.id.textName);

        //

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);    // to show the recyclerView list from the bottom
        recyclerView.setLayoutManager(layoutManager);

        ///////////// get Extras values :

        Bundle extras = getIntent().getExtras();
        theirId = extras.getInt("theirId",0);
        //theirId = Integer.valueOf(theirStringId);

        theirName = extras.getString("theirFirstName") + " " + extras.getString("theirLastName");
        theirTypeProfil = extras.getString("theirTypeProfil");
        theirImage = extras.getString("theirImage");

        /////////////

        nameTextView.setText(theirName);

        // get the data :
        listMessages = getListMessages();
    }

    ///////// Every 5 seconds this method will be run (update the recyclerView) :

    @Override
    protected void onResume() {


       listMessages = getListMessages(); // this line shld go inside the run() method

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //listMessages = getListMessages();
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

        // Get the session's id :
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int myId = sh.getInt("myId",0);

        /// get their id :
        Bundle extras = getIntent().getExtras();
        theirId = extras.getInt("theirId",0);

        // we set the message object :
       // Message msgToSend = new Message(0,null,null,newMessage,"test date");
        Message msgToSend = new Message();

        msgToSend.setDate("2023-01-07 22:51:01");
        msgToSend.setMessageText(newMessage);

        // we should get the two persons objects before saving the msg :

        api.getProfilePersonne(myId).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                //get the data from the response
                final Person person1 = response.body();
                msgToSend.setMessageFrom(person1);
                System.out.println("PERSON 1 : " + person1.toString());

                // get person 2 :

                api.getProfilePersonne(theirId).enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        //get the data from the response
                        final Person person2 = response.body();
                        msgToSend.setMessageTo(person2);
                        System.out.println("PERSON 2 : " + person2.toString());

                        // now we save the msg :

                        api.saveSentMsg(msgToSend).enqueue(new Callback<Message>() {
                            @Override
                            public void onResponse(Call<Message> call, Response<Message> response) {
                                Toast.makeText(ChatActivity.this, "Message sent !", Toast.LENGTH_SHORT).show();
                                System.out.println("messaaaaaage : " + msgToSend.toString());

                                listMessages = getListMessages();  // renew the data

                                adapter = new MessageAdapter(listMessages,ChatActivity.this);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                                /// ---

                                newMessageText.setText("");
                            }
                            @Override
                            public void onFailure(Call<Message> call, Throwable t) {
                                Toast.makeText(ChatActivity.this, "Failed to send the message", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        System.out.println("PERSON 2 NADA");
                    }
                });
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                System.out.println("PERSON 1 NADA");
            }
        });

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

        // Get the session's id :
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int myId = sh.getInt("myId",0);

        // get their id :
        Bundle extras = getIntent().getExtras();
        theirId = extras.getInt("theirId",0);

        listMessages = new ArrayList<>();

        //////// msgs must be selected from newest to oldest


        api.getChatMsgs(myId,theirId).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(ChatActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                // display response as string
                listMessages = response.body();
                System.out.println("got it");
                System.out.println(response.body());

                adapter = new MessageAdapter(listMessages,ChatActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                // Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                System.out.println("Faiiiiiled");
                System.out.println("test" + call);

            }
        });

        System.out.println("w haadiii : " + listMessages.toString());

        return listMessages;
    }

}