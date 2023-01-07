package com.example.myandroidapp.Models;

public class Person {

    private int person_id;
    private String cin;
    private String firstName;
    private String lastName;
    private String city;
    private String tel;
    private String imageP;
    private String typeProfil;
    private String description;
    private Service service;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", cin='" + cin + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", tel='" + tel + '\'' +
                ", imageP='" + imageP + '\'' +
                ", typeProfil='" + typeProfil + '\'' +
                ", description='" + description + '\'' +
                ", service=" + service +
                '}';
    }
}
