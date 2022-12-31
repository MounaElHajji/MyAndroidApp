package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.RatingEmp;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.List;

public class EmployeesDetails extends AppCompatActivity {

    List<RatingEmp> ratingList;
    RatingBar ratingBar, ratingBarTotal;
    TextView villeTxt, nomTxt, adressTxt, emploiTxt, descTxt, telTxt, employeeDeatls, responseTV, averageRating, ratingSumText, employeeVille, lastNamemployye;
    String nom;
    String ville;
    String description;
    String image;
    String telephone, lastNameEmp;
    String emploie;
    String ratingValue;
    String id;
    String typeProfile;
    float myRating = 0;
    ApiInterface apiInterface;
    SharedPreferences sh;
    RetrofitS retrofitS= new RetrofitS();
    AccountApi api=retrofitS.getRetrofit().create(AccountApi.class);
    int id_current, id_emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Employee emp = new Employee();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_details);

        sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        id_current= sh.getInt("id",0);

        villeTxt = findViewById(R.id.textView4);
        nomTxt = findViewById(R.id.textView);
        adressTxt = findViewById(R.id.textView8);
        emploiTxt = findViewById(R.id.textView10);
        descTxt = findViewById(R.id.textView12);
        telTxt = findViewById(R.id.textView6);
        ratingBar = findViewById(R.id.ratingBar);
        responseTV = findViewById(R.id.idTVResponse);
        averageRating = findViewById(R.id.rating);
        ratingBarTotal = findViewById(R.id.ratingBar2);
        ratingSumText =findViewById(R.id.ratingSumText);
        employeeVille = findViewById(R.id.employeeVille);
        lastNamemployye = findViewById(R.id.textViewNom);


        Intent intent = getIntent();
        nom = intent.getStringExtra("firstName");
        ville = intent.getStringExtra("city");
        description = intent.getStringExtra("description");
        image = intent.getStringExtra("imageP");
        telephone = intent.getStringExtra("tel");
        emploie = intent.getStringExtra("service_title");
//        ratingValue = intent.getStringExtra("label");

        id = intent.getStringExtra("id");
        id_emp = Integer.valueOf(id);
        typeProfile = intent.getStringExtra("typeProfil");
        lastNameEmp = intent.getStringExtra("lastName");



        SumRating();
        sumColumnsRating();
//
        if(id_current != id_emp)
        {
            ratingOfClientForEmp();
        }

//        if(ratingBar.getRating() != 0)
//        {
//            ratingBar.setIsIndicator(true);
//        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating = (int) v;
                String message = null;

                myRating = ratingBar.getRating();
                ratingBar.setRating(myRating);
                RateEmplployee(myRating);
                Toast.makeText(EmployeesDetails.this, message, Toast.LENGTH_SHORT).show();
            }
        });


        nomTxt.setText(nom);
        villeTxt.setText(ville);
        descTxt.setText(description);
        emploiTxt.setText(emploie);
        telTxt.setText(telephone);
        employeeVille.setText(ville);
//        employeeDeatls.setText(id);
        lastNamemployye.setText(lastNameEmp);

    }

    private void ratingOfClientForEmp()
    {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Integer> call = apiInterface.getRatOfClientForEmp(id_current, id_emp);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                Integer responseFromAPI = response.body();
                myRating = ratingBar.getRating();

                ratingBar.setRating(responseFromAPI);


            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                averageRating.setText("Error found is : " + t.getMessage());
            }
        });
    }

    private void RateEmplployee(Float label) {
        RatingEmp rating = new RatingEmp(label);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Employee> call = apiInterface.AddRating(rating, id_emp, id_current);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                // this method is called when we get response from our api.
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                // on below line we are setting empty
                // text to our both edit text.

                // we are getting a response from our body and
                // passing it to our modal class.
                Employee responseFromAPI = response.body();

                // on below line we are getting our data from modal class
                // and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getRating();

                // below line we are setting our string to our text view.
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }

    private void SumRating()
    {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Integer> call = apiInterface.SumRating(id_emp, id_current);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                // this method is called when we get response from our api.
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                Integer responseFromAPI = response.body();
                String ratTextAverage = Integer.toString(responseFromAPI);
                ratingBarTotal.setRating(responseFromAPI);
                averageRating.setText(ratTextAverage);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                averageRating.setText("Error found is : " + t.getMessage());
            }
        });
    }


    private void sumColumnsRating() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Integer> call = apiInterface.sumRatingsByImp(id_emp, id_current);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                // this method is called when we get response from our api.
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                // on below line we are setting empty
                // text to our both edit text.

                // we are getting a response from our body and
                // passing it to our modal class.
                Integer responseFromAPI = response.body();

                // on below line we are getting our data from modal class
                // and adding it to our string.

                // below line we are setting our string to our text view.

                String ratText = Integer.toString(responseFromAPI);

                ratingSumText.setText(ratText);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                // setting text to our text view when
                // we get error response from API.
                averageRating.setText("Error found is : " + t.getMessage());
            }
        });
    }


}