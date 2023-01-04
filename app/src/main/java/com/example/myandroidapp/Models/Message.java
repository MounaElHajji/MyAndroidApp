package com.example.myandroidapp.Models;

import java.time.LocalDateTime;

public class Message {
    public static final int LAYOUT_ONE = 1; // my msg
    public static final int LAYOUT_TWO = 2; // the other's msg

    private final int viewType;
    private int messageId;
    private Person messageFrom;
    private Person messageTo;
    private String messageText;
    private LocalDateTime createdDate;

    public Message(int viewType, int messageId, Person messageFrom, Person messageTo, String messageText, LocalDateTime createdDate) {
        this.viewType = viewType;
        this.messageId = messageId;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.messageText = messageText;
        this.createdDate = createdDate;
    }

    public Message(int viewType, String messageText, LocalDateTime createdDate) {
        this.viewType = viewType;
        this.messageText = messageText;
        this.createdDate = createdDate;
    }

    public int getViewType() {
        return viewType;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
