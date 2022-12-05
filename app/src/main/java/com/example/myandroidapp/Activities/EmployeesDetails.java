package com.example.myandroidapp.Activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myandroidapp.R;

public class EmployeesDetails extends AppCompatActivity {


    TextView villeTxt, nomTxt, adressTxt, emploiTxt, descTxt, telTxt;
    String nom, ville, description, image, telephone, emploie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_details);

        villeTxt = findViewById(R.id.textView4);
        nomTxt = findViewById(R.id.textView);
        adressTxt = findViewById(R.id.textView8);
        emploiTxt = findViewById(R.id.textView10);
        descTxt = findViewById(R.id.textView12);
        telTxt = findViewById(R.id.textView6);

        Intent intent = getIntent();

        nom = intent.getStringExtra("firstName");
        ville = intent.getStringExtra("city");
        description = intent.getStringExtra("description");
        image = intent.getStringExtra("imageP");
        telephone = intent.getStringExtra("tel");
        emploie = intent.getStringExtra("typeProfil");

        nomTxt.setText(nom);
        villeTxt.setText(ville);
        descTxt.setText(description);
        emploiTxt.setText(emploie);
        telTxt.setText(telephone);


    }
}