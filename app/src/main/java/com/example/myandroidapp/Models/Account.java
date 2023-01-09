package com.example.myandroidapp.Models;

public class Account {
    private  Long id;
    private String username;
    private String password;

    public Account(String password, String username) {
        this.username = username;
        this.password=password;
    }

    public Account() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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