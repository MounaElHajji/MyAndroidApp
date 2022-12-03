package com.example.myandroidapp.Models;

public class Employee {
    private String city, last_name, tel, imageP, type_profil, cin, first_name;
    private Long service_id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImage() {
        return imageP;
    }

    public void setImage(String image) {
        this.imageP = image;
    }

    public String getType_profile() {
        return type_profil;
    }

    public void setType_profile(String type_profile) {
        this.type_profil = type_profile;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Long getId() {
        return service_id;
    }

    public void setId(Long id) {
        this.service_id = id;
    }
}
