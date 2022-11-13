package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public void openActivity()
    {
        Intent inten = new Intent(this,MainActivity2.class);
        startActivity(inten);
    }
    @OnClick(R.id.button)
    protected void buttonClick(){

        openActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}