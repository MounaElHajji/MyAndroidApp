package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.R;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onProfilClick(View view) {
        Intent intent = new Intent(this, CurrentProfile.class);
        startActivity(intent);
    }

    // -------- Footer icons listeners /
}