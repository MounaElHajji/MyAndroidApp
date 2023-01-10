package com.example.myandroidapp.Activities;

import static com.example.myandroidapp.Activities.ServiceEmpActivity.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentProfile extends AppCompatActivity {
    ApiInterface apiInterface;
    TextView villeTxt, nomTxt, cinTxt, descTxt, telTxt, prenomTxt;
    ImageView imageP;
    String type_profil;
    SharedPreferences sharedPref;
    @BindView(R.id.SettngsText)
    TextView settings;
    @BindView(R.id.EditProfile)
    TextView edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
       type_profil = sharedPref.getString("type_profil", "");
        if(type_profil.equals("employe")) {
            setContentView(R.layout.activity_current_profile_empl);
            imageP= findViewById(R.id.imageView6);
        }
        else{
            setContentView(R.layout.activity_current_profile);
           imageP= findViewById(R.id.imageView6);
        }
        ButterKnife.bind(this);
        getVardFromLayout();
        getUserDetails();
        OnclickButtonListener();
    }

    @OnClick(R.id.EditProfile)
    void EditProfile(){
        Intent i= new Intent(this, EditProfile.class);
        startActivity(i);
    }
    @OnClick(R.id.SettngsText)
    void settings(){
        Intent i= new Intent(this, Settings.class);
        startActivity(i);
    }
    private void getVardFromLayout() {

        if(type_profil.equals("employe")) {
            villeTxt = findViewById(R.id.textView4);
            nomTxt = findViewById(R.id.text_nom);
            prenomTxt = findViewById(R.id.text_nom1);
            cinTxt = findViewById(R.id.textCin);
            descTxt = findViewById(R.id.Description);
            telTxt = findViewById(R.id.textView6);
        }
        else{
            villeTxt = findViewById(R.id.textView4);
            nomTxt = findViewById(R.id.text_nom);
            prenomTxt = findViewById(R.id.text_nom1);
            cinTxt = findViewById(R.id.textView8);
            telTxt = findViewById(R.id.textView6);
            prenomTxt = findViewById(R.id.text_nom1);
        }
    }

    private void getUserDetails() {
        sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        int id= sharedPref.getInt("id",0);
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
                System.out.println("fkjeifjcefjcidse : " + reponseEmp.toString());

                if(type_profil.equals("employe")) {
                    villeTxt.setText(empVille);
                    telTxt.setText(empTel);
                    cinTxt.setText(empCin);
                    nomTxt.setText(empNom);
                    prenomTxt.setText(empPrenom);
                    descTxt.setText(empDesc);
                    System.out.println(imagep);
                    if(imagep!=null) {
                        Picasso.get()
                                .load(Uri.parse(imagep))
                                .centerCrop()
                                .resize(150, 150)
                                .into(imageP);

                        Log.d(TAG, imagep);
                    }

                }
                else{
                    villeTxt.setText(empVille);
                    telTxt.setText(empTel);
                    nomTxt.setText(empNom);
                    prenomTxt.setText(empPrenom);
                    if(imagep!=null) {
                    Picasso.get()
                            .load(Uri.parse(imagep))
                            .centerCrop()
                            .resize(150,150)
                            .into(imageP);

                    Log.d(TAG, imagep);}
                }

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
        Intent intent = new Intent(this, listeServices.class);
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
    public void onMessageEmplClick(View view){
        Intent intent = new Intent(this, MessagesActivity.class);

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