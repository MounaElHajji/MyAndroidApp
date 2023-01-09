package com.example.myandroidapp.Models;

public class Account {
    private  int id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(String password, String email) {
        this.username=email;
        this.password=password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private Person person;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Person getPerson() {
        return person;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}