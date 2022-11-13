package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class inscription extends AppCompatActivity {

    String[] profile_type = { "client", "employé"};
    TextView mytextview;
    Button inscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        mytextview = findViewById(R.id.authentification);

        mytextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inscription.this,OtherActivity.class);
                startActivity(intent);
            }
        });

        inscription = findViewById(R.id.btnRegister);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inscription.this,OtherActivity.class);
                startActivity(intent);
            }
        });
        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, profile_type);
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
        });



    }
}