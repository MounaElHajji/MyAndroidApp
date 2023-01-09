package com.example.myandroidapp.Adapters;

//import static com.example.myandroidapp.Models.Message.LAYOUT_ONE;
//import static com.example.myandroidapp.Models.Message.LAYOUT_TWO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Models.Message;
import com.example.myandroidapp.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    public static final int LAYOUT_ONE = 1; // my msg
    public static final int LAYOUT_TWO = 2; // the other's msg

    List<Message> listMessages;
    Context context;

    public MessageAdapter(@NonNull List<Message> listMessages, Context context) {
        this.listMessages = listMessages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch(listMessages.get(position).getViewType())
        {
            case 1 :
                return LAYOUT_ONE;

            case 2 :
                return LAYOUT_TWO;

            default :
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType)
        {
            case LAYOUT_ONE :
                View layoutOne = LayoutInflater.from(context)
                        .inflate(R.layout.item_container_sent_message,parent,false);
                return new SenderMessageViewHolder(layoutOne);

            case LAYOUT_TWO :
                View layoutTwo = LayoutInflater.from(context)
                        .inflate(R.layout.item_container_received_message,parent,false);
                return new ReceiverMessageViewHolder(layoutTwo);

            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Format LocalDateTime to String
       // String msgDate = listMessages.get(position).getCreatedDate().format(dateTimeFormatter);

        /*
        switch(listMessages.get(position).getViewType())
        {

            case LAYOUT_ONE :
                String sentMessage = listMessages.get(position).getMessageText();
                //String msgDate = listMessages.get(position).getCreatedDate();
                ((SenderMessageViewHolder) holder).setView(sentMessage, msgDate);
                break;

            case LAYOUT_TWO :
                String receivedMessage = listMessages.get(position).getMessageText();
               // String msgDate1 = listMessages.get(position).getCreatedDate();
                ((ReceiverMessageViewHolder) holder).setView(receivedMessage, msgDate);
                break;
        }

         */

        switch(listMessages.get(position).getViewType())
        {

            case LAYOUT_ONE :
                String sentMessage = listMessages.get(position).getMessageText();
                String msgDate = listMessages.get(position).getDate();
                ((SenderMessageViewHolder) holder).setView(sentMessage, msgDate);
                break;

            case LAYOUT_TWO :
                String receivedMessage = listMessages.get(position).getMessageText();
                String msgDate1 = listMessages.get(position).getDate();
                //String image = listMessages.get(position).getMessageTo().getImage();
                //Picasso.get().load(image).into(holder.imageView);

                ((ReceiverMessageViewHolder) holder).setView(receivedMessage, msgDate1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }

    class SenderMessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView textMessageSent, messageDate;

        public SenderMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessageSent = itemView.findViewById(R.id.textMessage);
            messageDate = itemView.findViewById(R.id.textDateTime);
        }

        /*
        private void setView(String textMessage, String textDate)
        {
            textMessageSent.setText(textMessage);
            messageDate.setText(textDate);
        }

         */

        private void setView(String textMessage, String textDate)
        {
            textMessageSent.setText(textMessage);
            messageDate.setText(textDate);
        }
    }

    class ReceiverMessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView textMessageReceived, messageDate;
        //public ImageView imageView;

        public ReceiverMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessageReceived = itemView.findViewById(R.id.textMessage);
            messageDate = itemView.findViewById(R.id.textDateTime);
            //imageView = itemView.findViewById(R.id.image);
        }

        /*
        private void setView(String textMessage, String textDate)
        {
            textMessageReceived.setText(textMessage);
            messageDate.setText(textDate);
        }
        */
         private void setView(String textMessage, String textDate)
        {
            textMessageReceived.setText(textMessage);
            messageDate.setText(textDate);

        }
    }
}
