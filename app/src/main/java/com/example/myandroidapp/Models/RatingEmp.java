package com.example.myandroidapp.Models;



public class RatingEmp {

    private int id_rating;

    private Float label;

    public int getId_rating() {
        return id_rating;
    }

    public RatingEmp(Float label) {
        this.label = label;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public Float getLabel() {
        return label;
    }

    public void setLabel(Float label) {
        this.label = label;
    }
}