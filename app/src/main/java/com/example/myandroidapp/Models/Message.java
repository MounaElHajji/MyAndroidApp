package com.example.myandroidapp.Models;

public class Message {
    public static final int LAYOUT_ONE = 1; // my msg
    public static final int LAYOUT_TWO = 2; // the other's msg

    private final int viewType;
    private String message;
    private String date;

    public Message(int viewType, String message, String date) {
        this.viewType = viewType;
        this.message = message;
        this.date = date;
    }

    public int getViewType() {
        return viewType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
