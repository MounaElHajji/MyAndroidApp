package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {



    @OnClick(R.id.button)
    protected void loginClick(){
        Intent inten = new Intent(this, LoginActivity
                .class);
        startActivity(inten);
    }

    @OnClick(R.id.button2)
    protected void sighUpClick(){
        Intent inten = new Intent(this, inscription.class);
        startActivity(inten);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Boolean islogin = sharedPref.getBoolean("userlogin", false);
        String type_profil = sharedPref.getString("type_profil", "");
        if(islogin && type_profil.equals("employe")){
            Intent i= new Intent(this, EmployeelistActivity.class);
            startActivity(i);
        }
        else if(islogin && type_profil.equals("client")){
            Intent i= new Intent(this, listeServices.class);
            startActivity(i);
        }
        else {
            setContentView(R.layout.activity_home);
            ButterKnife.bind(this);
        }
    }
}