package com.example.myandroidapp.Models;

public class Employee {
    private String city, lastName, tel, imageP, typeProfil, cin, firstName, description;
    private Long service_id;

    public String getType_profil() {
        return typeProfil;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String lastName) {
        this.lastName = lastName;
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


    public void setType_profile(String type_profile) {
        this.typeProfil = type_profile;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public Long getId() {
        return service_id;
    }

    public void setId(Long id) {
        this.service_id = id;
    }
}
