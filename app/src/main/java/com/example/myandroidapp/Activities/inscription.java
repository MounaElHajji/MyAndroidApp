package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myandroidapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class inscription extends AppCompatActivity {
    @BindView(R.id.fonction)
    Spinner fonction;

    @BindView(R.id.ville)
    Spinner ville;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        ButterKnife.bind(this);
        List fcts= new ArrayList();
        List LisVille= new ArrayList();
        LisVille.add("Ville");
        LisVille.add("Kénitra");
        LisVille.add("Rabat");
        fcts.add("Fonction");
        fcts.add("Employé");
        fcts.add("Consommateur");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                fcts
        );
        ArrayAdapter adapter1 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                LisVille
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        fonction.setAdapter(adapter);
        ville.setAdapter(adapter1);




    }

      /*  mytextview = findViewById(R.id.authentification);

        mytextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inscription.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        inscription = findViewById(R.id.btnRegister);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inscription.this,listeServices.class);
                startActivity(intent);
            }
        });
        //Create Array Adapter
       /* ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, profile_type);
        //Find TextView control
        AutoCompleteTextView acTextView = findViewById(R.id.inputUsername);
        //Set the number of characters the user must type before the drop down list is shown

        //Set the adapter
        acTextView.setAdapter(adapter);

        acTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View arg0) {
                acTextView.setMaxLines(5);
                acTextView.showDropDown();

            }
        });*/



}