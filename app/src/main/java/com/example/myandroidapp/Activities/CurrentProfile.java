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

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentProfile extends AppCompatActivity {
    Employee emp = new Employee();
    ApiInterface apiInterface;
    TextView villeTxt, nomTxt, cinTxt, emploiTxt, descTxt, telTxt;
    ImageView imageP;
    String type_profil;
    LinearLayout clickFavoris;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        type_profil = sharedPref.getString("type_profil", "");
        if(type_profil.equals("employe")) {
            setContentView(R.layout.activity_current_profile_empl);
            imageP= findViewById(R.id.imageView);
        }
        else{
            setContentView(R.layout.activity_current_profile);
           imageP= findViewById(R.id.imageView13);
        }

//       setContentView(R.layout.activity_current_profile);
//        imageP= findViewById(R.id.imageView13);

        /*clickFavoris = findViewById(R.id.clickFavoris);

        clickFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CurrentProfile.this, FavorisActivity.class);
                startActivity(i);
            }
        });*/
        ButterKnife.bind(this);
        getVardFromLayout();
        getUserDetails();
        OnclickButtonListener();
    }
    private void getVardFromLayout() {

        if(type_profil.equals("employe")) {
            villeTxt = findViewById(R.id.textView4);
            nomTxt = findViewById(R.id.textView);
            cinTxt = findViewById(R.id.textView8);
            emploiTxt = findViewById(R.id.textView10);
            descTxt = findViewById(R.id.textView12);
            telTxt = findViewById(R.id.textView6);
        }
        else{
            villeTxt = findViewById(R.id.textView4);
            nomTxt = findViewById(R.id.textView);
            cinTxt = findViewById(R.id.textView8);
            telTxt = findViewById(R.id.textView6);

        }
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

                if(type_profil.equals("employe")) {
                    villeTxt.setText(empVille);
                    telTxt.setText(empTel);
                    nomTxt.setText(empNom +" "+empPrenom);
                    emploiTxt.setText(empEmploie);
                    cinTxt.setText(empCin);
                    descTxt.setText(empDesc);
                    System.out.println(imagep);
                    Picasso.get()
                            .load(Uri.parse(imagep))
                            .centerCrop()
                            .resize(150,150)
                            .into(imageP);

                    Log.d(TAG, imagep);

                }
                else{
                    villeTxt.setText(empVille);
                    telTxt.setText(empTel);
                    nomTxt.setText(empNom +" "+empPrenom);
                    cinTxt.setText(empCin);
                    System.out.println(imagep);
                    Picasso.get()
                            .load(Uri.parse(imagep))
                            .centerCrop()
                            .resize(150,150)
                            .into(imageP);

                    Log.d(TAG, imagep);
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

    int requestCode = 0;
    public void callMe(View view){
        Uri telnumber = Uri.parse("tel:0689339528");
        Intent call = new Intent(Intent.ACTION_CALL, telnumber);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this, "Oops,vous n'avez pas de persmission, veuillez l'activer!", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, requestCode);
            return;
        }
        startActivity(call);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0689339528"));
                startActivity(callIntent);
            }
        }
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
        Intent intent = new Intent(this, EmployeelistActivity.class);

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