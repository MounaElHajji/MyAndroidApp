package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.myandroidapp.Adapters.ServiceAdapter;
import com.example.myandroidapp.Models.Service;
import butterknife.BindView;
import butterknife.ButterKnife;

public class listeServices extends AppCompatActivity {
    @BindView(R.id.lisSer)
    ListView listServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services);
        ButterKnife.bind(this);
        ArrayList<Service> services= new ArrayList<Service>();
        services.add(new Service("Plomberie",R.drawable.pl));
        services.add(new Service("Electricité",R.drawable.ele));
        services.add(new Service("Peinture",R.drawable.pei));
        services.add(new Service("Climatisation",R.drawable.cli));
        services.add(new Service("Bricolage",R.drawable.bri));
        services.add(new Service("Femme de ménage",R.drawable.fe));

        ServiceAdapter serviceAdapter= new ServiceAdapter(getApplicationContext(), R.layout.activity_liste_services, services);
        listServ.setAdapter(serviceAdapter);
    }
}