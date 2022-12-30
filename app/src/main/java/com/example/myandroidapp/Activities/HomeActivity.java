package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

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
        sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Boolean islogin = sharedPref.getBoolean("userlogin", false);
        if(islogin){
            Intent i= new Intent(this, EmployeelistActivity.class);
            startActivity(i);
        }else {
            setContentView(R.layout.activity_home);
            ButterKnife.bind(this);
        }
    }
}