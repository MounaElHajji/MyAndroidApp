package com.example.myandroidapp;

<<<<<<< HEAD:app/src/main/java/com/example/myandroidapp/MainActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button)
    protected void getProfile(){
        Intent i= new Intent(this, currentProfile.class);
        startActivity(i);
=======
        setContentView(R.layout.otheractivity);






>>>>>>> footer:app/src/main/java/com/example/myandroidapp/OtherActivity.java
    }

}
