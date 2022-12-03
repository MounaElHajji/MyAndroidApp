package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myandroidapp.Adapters.EmployeeAdapter;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FemmeMenageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewVar;
    List<Employee> EmployeeList;
    ApiInterface apiInterface;
    EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        spinners();
        setList();
        getData();
    }

    private void spinners() {
        Spinner myspinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(FemmeMenageActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
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
        Call<List<Employee>> call = apiInterface.getFemmeMenage();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FemmeMenageActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> postList = response.body();
                employeeAdapter = new EmployeeAdapter(FemmeMenageActivity.this, postList);
                recyclerViewVar.setAdapter(employeeAdapter);
            }
            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(FemmeMenageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}