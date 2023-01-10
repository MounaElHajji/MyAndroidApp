package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.RatingEmp;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;
import com.example.myandroidapp.retrofit.RetrofitS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeesDetails extends AppCompatActivity {

    RatingBar ratingBar, ratingBarTotal;
    TextView villeTxt, nomTxt, adressTxt, emploiTxt, descTxt, telTxt, responseTV, averageRating, ratingSumText, employeeVille, lastNamemployye;
    String nom, ville, description, image,telephone, lastNameEmp,emploie, ratingValue,id,typeProfile, empPropfileImg;
    ImageView imgProfile, fav;
    float myRating = 0;
    View layout;
    ApiInterface apiInterface;
    SharedPreferences sh;
    int id_current, id_emp;

    private Boolean HasRated = true;
    @SuppressLint("MissingInflatedId")
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
        emploiTxt = findViewById(R.id.employee_type);
        descTxt = findViewById(R.id.textView12);
        telTxt = findViewById(R.id.textView6);
        ratingBar = findViewById(R.id.ratingBar);
        responseTV = findViewById(R.id.idTVResponse);
        averageRating = findViewById(R.id.rating);
        ratingBarTotal = findViewById(R.id.ratingBar2);
        ratingSumText =findViewById(R.id.ratingSumText);
        employeeVille = findViewById(R.id.employeeVille);
        lastNamemployye = findViewById(R.id.textViewNom);
        imgProfile = findViewById(R.id.imageView13);
        layout= findViewById(R.id.layout3);
        fav = findViewById(R.id.heartf);

        Intent intent = getIntent();
        nom = intent.getStringExtra("firstName");
        ville = intent.getStringExtra("city");
        description = intent.getStringExtra("description");
        image = intent.getStringExtra("imageP");
        telephone = intent.getStringExtra("tel");
        emploie = intent.getStringExtra("service_title");
        empPropfileImg = intent.getStringExtra("imagep");
//        ratingValue = intent.getStringExtra("label");

        id = intent.getStringExtra("id");
        id_emp = Integer.valueOf(id);
        typeProfile = intent.getStringExtra("typeProfil");
        lastNameEmp = intent.getStringExtra("lastName");

        SumRating();
        sumColumnsRating();
        String type_profil = sh.getString("type_profil", "");
        if(type_profil.equals("employe")) {
            layout.setVisibility(View.GONE);
            fav.setVisibility(View.GONE);
        }
        if(id_current != id_emp)
        {
            ratingOfClientForEmp();
        }


        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int)starsf + 1;
                    RateEmplployee(Float.valueOf(stars));
                    v.setPressed(false);
//                    UpdateRating(String.valueOf(stars));
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }

                return false;
            }
        });


        nomTxt.setText(nom);
        villeTxt.setText(ville);
        descTxt.setText(description);
        emploiTxt.setText(emploie);
        telTxt.setText(telephone);
        employeeVille.setText(ville);
        lastNamemployye.setText(lastNameEmp);

        if(intent.getStringExtra("fav").equals("true")){
            fav.setImageResource(R.drawable.fav);
        }else{
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

        Picasso.get()
                .load(Uri.parse(empPropfileImg))
                .centerCrop()
                .resize(150,150)
                .placeholder(R.drawable.personne)
                .into(imgProfile);

        ButterKnife.bind(this);
    }

    private void ratingOfClientForEmp()
    {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Integer> call = apiInterface.getRatOfClientForEmp(id_current, id_emp);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
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

    @OnClick(R.id.heartf)
    public void clickHeart(){
        int id1=sh.getInt("id", 0);
        System.out.println("fav click");
        if(fav.getDrawable().getConstantState() == EmployeesDetails.this.getResources().getDrawable(R.drawable.fav).getConstantState()){
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            Call<Void> call2=apiInterface.DeleteFav(id1, Integer.parseInt(id));
            call2.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });
        }else{
            fav.setImageResource(R.drawable.fav);
            Call<Person> call1 = apiInterface.addFav(id1, Integer.parseInt(id));
            call1.enqueue(new Callback<Person>() {
                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                }
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                }
            });
        }
    }

    private void RateEmplployee(Float label) {
        RatingEmp rating = new RatingEmp(label);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Rating> call = apiInterface.AddRating(rating, id_emp, id_current);

        call.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                Toast.makeText(EmployeesDetails.this, "Rating", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }

    private void SumRating()
    {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Long> call = apiInterface.SumRating(id_emp, id_current);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                Long responseFromAPI = response.body();
                String ratTextAverage = String.valueOf(responseFromAPI);
                ratingBarTotal.setRating(responseFromAPI);
                averageRating.setText(ratTextAverage);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                averageRating.setText("Error found is : " + t.getMessage());
            }
        });
    }


    private void sumColumnsRating() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        // calling a method to create an update and passing our modal class.
        Call<Long> call = apiInterface.sumRatingsByImp(id_emp, id_current);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Toast.makeText(EmployeesDetails.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                Long responseFromAPI = response.body();
                String ratText = String.valueOf(responseFromAPI);

                ratingSumText.setText(ratText);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

                averageRating.setText("Error found is : " + t.getMessage());
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
}