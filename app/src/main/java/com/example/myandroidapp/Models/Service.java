package com.example.myandroidapp.Models;

public class Service {
    private int service_id;
    private String service_title;
    private String image;

    public Service(String service_title, String image) {
        this.service_title = service_title;
        this.image = image;
    }

    public int getId() {
        return service_id;
    }

    public String getLabel() {
        return service_title;
    }

    public void setLabel(String label) {
        this.service_title = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
