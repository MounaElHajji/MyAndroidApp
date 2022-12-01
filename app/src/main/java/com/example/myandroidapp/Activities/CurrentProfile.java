package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Settings;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.ApiInterface;
import com.example.myandroidapp.retrofit.RetrofitClient;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentProfile extends AppCompatActivity {
 Employee emp = new Employee();
 ApiInterface apiInterface;
    TextView villeTxt, nomTxt, adressTxt, emploiTxt, descTxt, telTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_profile);
        ButterKnife.bind(this);
        getVardFromLayout();
        getUserDetails();

    }
    private void getVardFromLayout() {

        villeTxt = findViewById(R.id.textView4);
        nomTxt = findViewById(R.id.textView10);
        adressTxt = findViewById(R.id.textView8);
        emploiTxt = findViewById(R.id.textView);
        descTxt = findViewById(R.id.textView12);
        telTxt = findViewById(R.id.textView6);
    }

    private void getUserDetails() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Employee> call = apiInterface.getProfilePersonne();
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CurrentProfile.this, "Success", Toast.LENGTH_LONG).show();
                }

                //get the data from the response
                Employee reponseEmp = response.body();
                String cin= reponseEmp.getCin();
                String empNom = reponseEmp.getFirstName();
                String empEmploie = reponseEmp.getLastName();
                String empVille = reponseEmp.getCity();
                String empTel = response.body().getTel();
                String empfunction = response.body().getFunction();


                //set the data in the layout to the dat coming from the backend
                villeTxt.setText(empVille);
                telTxt.setText(empTel);
                nomTxt.setText(empfunction);
                emploiTxt.setText(empEmploie+""+empNom);
                descTxt.setText(cin);
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
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
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