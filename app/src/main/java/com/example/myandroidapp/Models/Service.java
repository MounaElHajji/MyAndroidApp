package com.example.myandroidapp.Models;

import java.util.Set;


public class Service{

    private Long service_id;
    private Set<Person> personnes;
    private String service_title;

    public Set<Person> getPersonnes() {
        return personnes;
    }

    public void setService_id(long service_id) {
        this.service_id = service_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setPersonnes(Set<Person> personnes) {
        this.personnes = personnes;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }
}
