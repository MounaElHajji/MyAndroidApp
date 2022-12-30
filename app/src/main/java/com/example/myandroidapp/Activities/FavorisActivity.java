package com.example.myandroidapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.EmployeeAdapter;
import com.example.myandroidapp.Adapters.FavEmployeeAdapter;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.ListFavoris;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorisActivity  extends AppCompatActivity {
    private RecyclerView recyclerViewVar;

    List<Employee> EmployeeList;
    ApiInterface apiInterface;
    EmployeeAdapter employeeAdapter;
   // ImageView btnHeart =findViewById(R.id.btnHeart);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        spinners();
        setList();
        getData();
       // btnHeart.setImageResource(R.drawable.fav);
        ButterKnife.bind(this);
    }

    private void spinners() {
        Spinner myspinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(FavorisActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);
    }

    private void setList() {
        recyclerViewVar = findViewById(R.id.recycleView);
        recyclerViewVar.setHasFixedSize(true);
        recyclerViewVar.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getData() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int id= sh.getInt("id", 0);
        Call<List<ListFavoris>> call = apiInterface.getFav(id);
        call.enqueue(new Callback<List<ListFavoris>>() {
            @Override
            public void onResponse(Call<List<ListFavoris>> call, Response<List<ListFavoris>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FavorisActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ListFavoris> postList = response.body();
                List<Employee> emp= new ArrayList<>();
                for (ListFavoris f:
                    postList ) {
                    emp.add(f.getEmp());
                }
                employeeAdapter = new EmployeeAdapter(FavorisActivity.this, emp);
//                postAdapter.getFilter().filter("Employee");
                recyclerViewVar.setAdapter(employeeAdapter);
            }
            @Override
            public void onFailure(Call<List<ListFavoris>> call, Throwable t) {
                Toast.makeText(FavorisActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
