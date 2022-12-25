package com.example.myandroidapp.Models;

public class ListFavoris {

    private int id;

    private Employee person;

    private Employee emp;

    public Employee getPerson() {
        return person;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setPerson(Employee person) {
        this.person = person;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public ListFavoris(Employee person, Employee emp) {
        this.person = person;
        this.emp = emp;
    }
}
