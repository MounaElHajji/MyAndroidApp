package com.example.myandroidapp.Models;

public class Service {
    private int service_id;
    private String service_title;
    private String image;

    public Service() {

    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getService_title() {
        return service_title;
    }

    public Service(String service_title, String image) {
        this.service_title = service_title;
        this.image = image;
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
