package com.example.myandroidapp.Activities;

import android.content.Intent;
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
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }
}