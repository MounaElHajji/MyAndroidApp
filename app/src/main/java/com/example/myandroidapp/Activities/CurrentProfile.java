package com.example.myandroidapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    TextView villeTxt, nomTxt, cinTxt, emploiTxt, descTxt, telTxt, ratingClient;
    ImageView imageP;
    String type_profil;
    SharedPreferences sh;
    int id_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        type_profil = sharedPref.getString("type_profil", "");

        sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        id_current= sh.getInt("id",0);


        if(type_profil.equals("employe")) {
            setContentView(R.layout.activity_current_profile_empl);
        }
        else{
            setContentView(R.layout.activity_current_profile);
        }

        sumRatingOfEmp();

        imageP= findViewById(R.id.imageView13);
        ratingClient = findViewById(R.id.ratingClient);
        setContentView(R.layout.activity_current_profile);


        ButterKnife.bind(this);
        getVardFromLayout();
        getUserDetails();
    }

    private void sumRatingOfEmp() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Integer> call = apiInterface.getRatOfClientForEmp(id_current);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer responseFromAPI = response.body();
                String ratText = Integer.toString(responseFromAPI);
                ratingClient.setText(ratText);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                // setting text to our text view when
                // we get error response from API.
                ratingClient.setText("Error found is : " + t.getMessage());
            }
        });
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
        Call<Employee> call = apiInterface.getProfilePersonne(id_current);
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