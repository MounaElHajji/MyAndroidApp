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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.EmployeeAdapter;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Ville;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeelistActivity extends AppCompatActivity {
    private RecyclerView recyclerViewVar;
    ApiInterface apiInterface;
    EmployeeAdapter employeeAdapter;
    LinearLayout SearchLayout;
    Spinner myspinner, myspinnerVille;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);
        myspinnerVille = findViewById(R.id.spinnerVille);
        searchView = findViewById(R.id.search_bar);
        spinners();
        setList();
        getData();
        DepndantList();
        SearchByVille();
        SearchByName();
    }



    private void spinners() {
        Spinner myspinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(EmployeelistActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
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
        Call<List<Employee>> call = apiInterface.getPost();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(EmployeelistActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> postList = response.body();
                List<Employee> employeeList= new ArrayList<>();
                for (Employee emp:
                        postList) {
                    if (emp.getTypeProfil().equals("Employé")){
                        employeeList.add(emp);
                    }
                }
                employeeAdapter = new EmployeeAdapter(EmployeelistActivity.this, employeeList);
//                postAdapter.getFilter().filter("Employee");
                recyclerViewVar.setAdapter(employeeAdapter);
            }
            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(EmployeelistActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        myspinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(EmployeelistActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
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
                  /*  ArrayAdapter<String> myAdapterVille = new ArrayAdapter<>(ElectriciteActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ville));
                    myAdapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    myspinnerVille.setAdapter(myAdapterVille);*/
                    getVilles();
                    SearchLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getVilles() {
        List<String> ville = new ArrayList<>();
        ArrayAdapter<String> villeAdapter = new ArrayAdapter<>(EmployeelistActivity.this, android.R.layout.simple_list_item_1, ville);
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Ville>> call = apiInterface.getCities();
        call.enqueue(new Callback<List<Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if (response.isSuccessful()) {
                    for (Ville villeList : response.body()) {
                        String nom_ville = villeList.getNom_ville();
                        System.out.println(nom_ville);
                        // Ville object = new Ville(nom_ville);
                        ville.add(nom_ville);

                        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        myspinnerVille.setAdapter(villeAdapter);
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Toast.makeText(EmployeelistActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onMessageEmplClick(View view){
        Intent intent = new Intent(this, MessagesActivity.class);

        startActivity(intent);
        finish();
    }

    public void onHomeEmployeClick(View view) {
        Intent intent = new Intent(this, EmployeelistActivity.class);
        startActivity(intent);
        finish();
    }
    // -------- Footer icons listeners /
}