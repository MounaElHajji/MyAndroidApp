package com.example.myandroidapp.Activities;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings extends AppCompatActivity {
    Employee emp = new Employee();
    ApiInterface apiInterface;

    String type_profil;
    @BindView(R.id.editButton)
    TextView edit;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        type_profil = sharedPref.getString("type_profil", "");
        if(type_profil.equals("employe")){
            setContentView(R.layout.activity_settings_empl);
            prepareDialog();
        }else{
            setContentView(R.layout.activity_settings);
            prepareDialog();
        }

    }

    //Delete  Button  listener
//    public void OnclickButtonListener() {
//
//        Button bouton = findViewById(R.id.deleteButton);
//        bouton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DeleteuserAccount();
//                Intent intent = new Intent(Settings.this,HomeActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
    private void DeleteuserAccount() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        int id= sh.getInt("id",0);
        Call<Void> call = apiInterface.DeleteAccount(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Settings.this, "Success account deleted", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Settings.this, "failed to delete account ", Toast.LENGTH_LONG).show();
            }
        });
        SharedPreferences.Editor editor = sh.edit();
        editor.putBoolean("userlogin", false);
        editor.commit();
        Intent intent = new Intent(Settings.this,HomeActivity.class);
        startActivity(intent);
    }
    public void prepareDialog(){
        //Create the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button Ok = dialog.findViewById(R.id.btn_okay);
        Button Annuler = dialog.findViewById(R.id.btn_cancel);
        Ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteuserAccount();
                        dialog.dismiss();
                    }
                }
        );
        Annuler.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Settings.this, "la suppression du compte est annulé", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
        );
    }


    public void popUpMessage(View view){
        dialog.show(); // Showing the dialog here
    }

    public void onEditClick(View view) {
        Intent intent = new Intent(Settings.this, EditProfile.class);
        startActivity(intent);
    }
    public void onLogOutClick(View view){
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("userlogin", false);
        editor.clear();
        editor.commit();
        Intent intent = new Intent(Settings.this,
                LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(Settings.this, "you 're logout successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void SwitchProfil(View view){
        System.out.println("im here");
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type_profil", "client");
        editor.commit();
        Intent i = new Intent(this, listeServices.class);
        startActivity(i);
        finish();
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, MessagesActivity.class);
        startActivity(intent);
        finish();
    }


    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
        finish();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
        finish();
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void onMessageEmplClick(View view){
        Intent intent = new Intent(this, EmployeelistActivity.class);

        startActivity(intent);
        finish();
    }

    public void onProfilClick(View view) {
        Intent intent = new Intent(this, CurrentProfile.class);
        startActivity(intent);
        finish();
    }

    public void onHomeEmployeClick(View view){
        Intent intent = new Intent(this, EmployeelistActivity.class);
        startActivity(intent);
        finish();
    }



    // -------- Footer icons listeners /

}