package com.example.myandroidapp.Models;


public class Rating {

    private float id_rating;

    private String label;

    public float getId_rating() {
        return id_rating;
    }

    public Rating(String label) {
        this.label = label;
    }

    public void setId_rating(float id_rating) {
        this.id_rating = id_rating;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}