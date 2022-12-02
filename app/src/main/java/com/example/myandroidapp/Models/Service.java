package com.example.myandroidapp.Models;

public class Service {
    private int id;
    private String label;
    private int image;

    public Service(String label, int image) {
        this.label = label;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
