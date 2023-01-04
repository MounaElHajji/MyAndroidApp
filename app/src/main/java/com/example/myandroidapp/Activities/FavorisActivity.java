package com.example.myandroidapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.myandroidapp.Models.Ville;
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
    FavEmployeeAdapter employeeAdapter;
    // ImageView btnHeart =findViewById(R.id.btnHeart);
    EditText searchView;
    LinearLayout SearchLayout;
    Spinner myspinner, myspinnerVille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        myspinnerVille = findViewById(R.id.spinnerVille);
        searchView = findViewById(R.id.search_bar);
        myspinner = findViewById(R.id.spinner1);

        SearchByVille();
        SearchByName();
        DepndantList();
        spinners();
        setList();
        getData();
        // btnHeart.setImageResource(R.drawable.fav);
        ButterKnife.bind(this);
    }

    private void SearchByName() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                employeeAdapter.getFilter().filter(s);
            }
        });
    }
    private void SearchByVille() {
        myspinnerVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                employeeAdapter.getFilter().filter(myspinnerVille.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void DepndantList() {
        //Spinners
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(FavorisActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);

        myspinnerVille = findViewById(R.id.spinnerVille);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SearchLayout = findViewById(R.id.search_Layout);
                if(position == 1){
                    SearchLayout.setVisibility(View.VISIBLE);
                    myspinnerVille.setVisibility(View.GONE);
                }
                if(position==2){
                    myspinnerVille.setVisibility(View.VISIBLE);
                    getVilles();
//                    ArrayAdapter<String> myAdapterVille = new ArrayAdapter<>(ElectriciteActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ville));
//                    myAdapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    myspinnerVille.setAdapter(myAdapterVille);
                    SearchLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getVilles() {
        List <String> Ville =new ArrayList<>();
        ArrayAdapter <String> villeAdapter= new ArrayAdapter<>(FavorisActivity.this, android.R.layout.simple_list_item_1,Ville);
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<com.example.myandroidapp.Models.Ville>> call = apiInterface.getCities();
        call.enqueue(new Callback<List<com.example.myandroidapp.Models.Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if(response.isSuccessful()) {
                    for (Ville villeList :response.body()){
                        String nom_ville= villeList.getNom_ville();
                        // Ville object = new Ville(nom_ville);
                        Ville.add(nom_ville);

                        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        myspinnerVille.setAdapter(villeAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Toast.makeText(FavorisActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
                employeeAdapter = new FavEmployeeAdapter(FavorisActivity.this, emp);
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