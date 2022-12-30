package com.example.myandroidapp.Models;

import android.media.Rating;

public class Employee {
    private String city, lastName, tel, imageP, typeProfil, cin, firstName, description;
    private String id;
    private Rating rating;
    private Long service_id;


    public Employee(Rating rating) {
        this.rating = rating;
    }

    public Employee() {
    }

    public Employee(RatingEmp rating) {
    }


    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

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

    public void setId(String id) {
        this.id = id;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public String getTypeProfil() {
        return typeProfil;
    }

    public void setTypeProfil(String typeProfil) {
        this.typeProfil = typeProfil;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }



    public String getId() {
        return id;
    }
}