package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/currentProfile.java

public class currentProfile extends AppCompatActivity {
=======
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
>>>>>>> Page_acceuil:app/src/main/java/com/example/myandroidapp/MainActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/currentProfile.java
        setContentView(R.layout.activity_current_profile);
=======
        setContentView(R.layout.activity_main);
>>>>>>> Page_acceuil:app/src/main/java/com/example/myandroidapp/MainActivity.java
        ButterKnife.bind(this);
    }
    public void onBackClick(View view) {
        finish();
    }
}