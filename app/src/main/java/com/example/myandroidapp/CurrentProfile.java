package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

public class CurrentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_profile);
        ButterKnife.bind(this);
    }

    public void onBackClick(View view) {
        finish();
    }
}