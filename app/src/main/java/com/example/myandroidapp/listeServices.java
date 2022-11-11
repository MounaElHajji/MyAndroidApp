package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.ServiceAdapter;
import Models.Service;
import butterknife.BindView;
import butterknife.ButterKnife;

public class listeServices extends AppCompatActivity {
    @BindView(R.id.lisSer)
    ListView listServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services);
        ButterKnife.bind(this);
        ArrayList<Service> services= new ArrayList<Service>();
        services.add(new Service("Plomberie",R.drawable.pl));
        ServiceAdapter serviceAdapter= new ServiceAdapter(getApplicationContext(), R.layout.activity_liste_services, services);
        listServ.setAdapter(serviceAdapter);
    }
}