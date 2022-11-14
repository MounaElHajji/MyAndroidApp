package com.example.myandroidapp;

<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/MainActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myandroidapp.models.Settings;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
=======
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> footer:app/src/main/java/com/example/myandroidapp/OtherActivity.java

public class OtherActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/MainActivity.java
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button)
    protected void getProfile(){
        Intent i= new Intent(this, currentProfile.class);
        startActivity(i);
=======
<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/OtherActivity.java
        setContentView(R.layout.otheractivity);






>>>>>>> footer:app/src/main/java/com/example/myandroidapp/OtherActivity.java
=======

>>>>>>> settings
>>>>>>> 7b7a1e1e4d4743f41041ad2f2e0a5f5eed76e092:app/src/main/java/com/example/myandroidapp/MainActivity.java
    }

}
