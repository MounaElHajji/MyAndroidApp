package com.example.myandroidapp.Models;

public class Person {

    private Long id;
    private String cin;
    private String firstName;
    private String lastName;
    private String city;
    private String tel;
    private String imageP;
    private String typeProfil;
    private String description;
    private Service service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCin() {
        return cin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getTel() {
        return tel;
    }

    public String getImage() {
        return imageP;
    }

    public String getTypeProfil() {
        return typeProfil;
    }

    public Service getService() {
        return service;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setImage(String image) {
        this.imageP = image;
    }

    public void setTypeProfil(String typeProfil) {
        this.typeProfil = typeProfil;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
