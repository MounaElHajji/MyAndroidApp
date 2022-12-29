package com.example.myandroidapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Listeners.ListMessagesListener;
import com.example.myandroidapp.Models.Conversation;
import com.example.myandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    List<Conversation> listConversations;
    Context context;
    private ListMessagesListener listener;

    public ConversationAdapter(List<Conversation> listConversations, Context context, ListMessagesListener listener) {
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
        Conversation conversation = listConversations.get(position);

        holder.textName.setText(conversation.getFirstName() + " " + conversation.getLastName());
        holder.textService.setText(conversation.getTypeProfil());

        if(listConversations.get(position).getImageP() != null){
            Picasso.get().load(listConversations.get(position).getImageP()).into(holder.image);
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listConversations.get(holder.getAdapterPosition()));
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
