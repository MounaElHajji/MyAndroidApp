package com.example.myandroidapp.Models;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Activities.EditProfile;
import com.example.myandroidapp.Activities.EmployeelistActivity;
import com.example.myandroidapp.Activities.HomeActivity;
import com.example.myandroidapp.R;

import butterknife.BindView;

public class Settings extends AppCompatActivity {

    @BindView(R.id.editButton)
    TextView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    public void onEditClick(View view) {
        Intent intent = new Intent(Settings.this, EditProfile.class);
        startActivity(intent);
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, EmployeelistActivity.class);
        startActivity(intent);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onProfilClick(View view) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    // -------- Footer icons listeners /

}