package com.example.myandroidapp.Models;

public class Message {
    public static final int LAYOUT_ONE = 1; // my msg
    public static final int LAYOUT_TWO = 2; // the other's msg

    private final int viewType;
    private Person personFrom;
    private Person personTo;
    private String message;
    private String date;

    public Message(int viewType, Person personFrom, Person personTo, String message, String date) {
        this.viewType = viewType;
        this.personFrom = personFrom;
        this.personTo = personTo;
        this.message = message;
        this.date = date;
    }

    public Message(int viewType, String message, String date) {
        this.viewType = viewType;
        this.message = message;
        this.date = date;
    }

    public int getViewType() {
        return viewType;
    }

    public Person getPersonFrom() {
        return personFrom;
    }

    public void setPersonFrom(Person personFrom) {
        this.personFrom = personFrom;
    }

    public Person getPersonTo() {
        return personTo;
    }

    public void setPersonTo(Person personTo) {
        this.personTo = personTo;
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
