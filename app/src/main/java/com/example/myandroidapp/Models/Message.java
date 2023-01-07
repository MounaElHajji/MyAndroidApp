package com.example.myandroidapp.Models;

//import java.time.String;

public class Message {
    //public static final int LAYOUT_ONE = 1; // my msg
    //public static final int LAYOUT_TWO = 2; // the other's msg

    private int viewType;
    private int messageId;
    private Person messageFrom;
    private Person messageTo;
    private String messageText;
    private String date;

    public Message(int viewType, Person messageFrom, Person messageTo, String message, String date) {
        this.viewType = viewType;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.messageText = message;
        this.date = date;
    }

    public Message(int viewType, String message, String date) {
        this.viewType = viewType;
        this.messageText = message;
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getViewType() {
        return viewType;
    }

    public Person getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(Person messageFrom) {
        this.messageFrom = messageFrom;
    }

    public Person getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(Person messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "viewType=" + viewType +
                ", messageId=" + messageId +
                ", messageFrom=" + messageFrom +
                ", messageTo=" + messageTo +
                ", messageText='" + messageText + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Message() {
    }
}
