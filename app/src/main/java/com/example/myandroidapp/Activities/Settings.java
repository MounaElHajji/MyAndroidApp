package com.example.myandroidapp.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent intent = new Intent(Settings.this, EditProfile.class);
        startActivity(intent);
    }
    public void onLogOutClick(View view){
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("userlogin", false);
        editor.commit();


        Intent intent = new Intent(Settings.this,
                LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(Settings.this, "you 're logout successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
        finish();
    }


    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
        finish();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void onProfilClick(View view) {
        Intent intent = new Intent(this, CurrentProfile.class);
        startActivity(intent);
        finish();
    }

    // -------- Footer icons listeners /

}