package com.example.myandroidapp.models;

import com.example.myandroidapp.models.Footer;
import com.example.myandroidapp.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onProfilClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // -------- Footer icons listeners /

}