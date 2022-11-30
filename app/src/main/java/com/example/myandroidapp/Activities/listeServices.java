package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.myandroidapp.Adapters.ServiceAdapter;

import com.example.myandroidapp.Models.Service1;
import com.example.myandroidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class listeServices extends AppCompatActivity {
    @BindView(R.id.lisSer)
    ListView listServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services);
        ButterKnife.bind(this);
        ArrayList<Service1> services= new ArrayList<Service1>();
        services.add(new Service1("Plomberie",R.drawable.pl));
        services.add(new Service1("Electricité",R.drawable.ele));
        services.add(new Service1("Peinture",R.drawable.pei));
        services.add(new Service1("Climatisation",R.drawable.cli));
        services.add(new Service1("Bricolage",R.drawable.bri));
        services.add(new Service1("Femme de ménage",R.drawable.fe));

        ServiceAdapter serviceAdapter= new ServiceAdapter(getApplicationContext(), R.layout.activity_liste_services, services);
        listServ.setAdapter(serviceAdapter);


    }

    @OnItemClick(R.id.lisSer)
    public void onListTransactionClicked(int position){
        Intent i = new Intent(this, EmployeelistActivity.class);
        startActivity(i);
    }
}