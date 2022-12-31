package com.example.myandroidapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentProfile extends AppCompatActivity {
    Employee emp = new Employee();
    ApiInterface apiInterface;
    TextView villeTxt, nomTxt, cinTxt, emploiTxt, descTxt, telTxt;
    ImageView imageP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageP= findViewById(R.id.imageView);

        setContentView(R.layout.activity_current_profile);
        ButterKnife.bind(this);
        getVardFromLayout();
        getUserDetails();
        OnclickButtonListener();
    }
    private void getVardFromLayout() {
        villeTxt = findViewById(R.id.textView4);
        nomTxt = findViewById(R.id.textView);
        cinTxt = findViewById(R.id.textView8);
        emploiTxt = findViewById(R.id.textView10);
        descTxt = findViewById(R.id.textView12);
        telTxt = findViewById(R.id.textView6);
    }

    private void getUserDetails() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        int id= sh.getInt("id",0);
        Call<Employee> call = apiInterface.getProfilePersonne(id);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {


                //get the data from the response
                Employee reponseEmp = response.body();
                String empVille = reponseEmp.getCity();
                String empTel = response.body().getTel();
                String empNom = reponseEmp.getFirst_name();
                String empPrenom = reponseEmp.getLast_name();
                String empCin = reponseEmp.getCin();
                String empDesc = reponseEmp.getDescription();
                String empEmploie = reponseEmp.getType_profil();
                String imagep= reponseEmp.getImageP();



                //set the data in the layout to the dat coming from the backend
                villeTxt.setText(empVille);
                telTxt.setText(empTel);
                nomTxt.setText(empNom +" "+empPrenom);
                emploiTxt.setText(empEmploie);
                cinTxt.setText(empCin);
                descTxt.setText(empDesc);

                Picasso.get()
                        .load(Uri.parse(imagep))
                        .centerCrop()
                        .resize(150,150)
                        .into(imageP);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });

    }


    //heart icon listener
    public void OnclickButtonListener() {

        ImageView imgview = findViewById(R.id.imageView6);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentProfile.this,FavorisActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(CurrentProfile.this, listeServices.class);
        startActivity(intent);
        finish();
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
        finish();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, EmployeelistActivity.class);
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