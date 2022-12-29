package com.example.myandroidapp.Models;

public class Conversation {

    // private Long id;
    private String firstName;
    private String lastName;
    private String imageP;
    private String typeProfil;

    public Conversation(String firstName, String lastName, String imageP, String typeProfil) {
        // this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageP = imageP;
        this.typeProfil = typeProfil;
    }

    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
