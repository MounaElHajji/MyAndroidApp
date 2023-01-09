package com.example.myandroidapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Activities.ChatActivity;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Message;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    List<Message> listConversations;
    Context context;
    private ListMessagesListener listener;

    public ConversationAdapter(List<Message> listConversations, Context context, ListMessagesListener listener) {
        this.listConversations = listConversations;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_conversation, null);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message conversation = listConversations.get(position);

        // Get the session's id :
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int myId = sh.getInt("id",0);

        // specify the person i am talking to :
        Person personWith = new Person();
        if (conversation.getMessageFrom().getId() == myId) {
            personWith = conversation.getMessageTo();
            System.out.println("his ID :  " + conversation.getMessageTo().getId());
        }
        else { personWith = conversation.getMessageFrom();
            System.out.println("his ID :  " + conversation.getMessageTo().getId());
        }

        holder.textName.setText(personWith.getFirstName() + " " + personWith.getLastName());
        holder.textService.setText(conversation.getMessageText());

        /*
        if(listConversations.get(position).getImageP() != null){
            Picasso.get().load(listConversations.get(position).getImageP()).into(holder.image);
        }
         */

        if(personWith.getImage() != null){
            Picasso.get().load(personWith.getImage()).into(holder.image);
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int thisPosition = holder.getAdapterPosition();
                listener.onItemClicked(listConversations.get(thisPosition));

                // specify the person i am talking to :
                Person personWith = new Person();
                if (listConversations.get(thisPosition).getMessageFrom().getId() == myId) {
                    personWith = listConversations.get(thisPosition).getMessageTo();
                }
                else { personWith = listConversations.get(thisPosition).getMessageFrom(); }

                // putExtras of the other person's attributs
                Intent i = new Intent(context, ChatActivity.class);
                i.putExtra("theirId", personWith.getId());
                i.putExtra("theirFirstName", personWith.getFirstName());
                i.putExtra("theirLastName", personWith.getLastName());
                i.putExtra("theirTypeProfil", personWith.getTypeProfil());
                i.putExtra("theirImage", personWith.getImage());

                System.out.println("thats their id ------- " + personWith.getId());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listConversations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textName, textService;
        public ImageView image;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textService = itemView.findViewById(R.id.textService);
            image = itemView.findViewById(R.id.image);
            constraintLayout = itemView.findViewById(R.id.itemConversation);
        }
    }
}
