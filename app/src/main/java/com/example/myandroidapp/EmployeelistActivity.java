package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myandroidapp.Adapters.EmployeeAdapter;
import com.example.myandroidapp.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeelistActivity extends AppCompatActivity {


    private RecyclerView recyclerViewVar;

    List<Employee> EmployeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        Spinner myspinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(EmployeelistActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filtrage));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);

        recyclerViewVar = findViewById(R.id.recycleView);
        recyclerViewVar.setHasFixedSize(true);
        recyclerViewVar.setLayoutManager(new LinearLayoutManager(this));

        EmployeeAdapter postAdapter = new EmployeeAdapter(EmployeelistActivity.this, getData());
        recyclerViewVar.setAdapter(postAdapter);
    }

    public static List<Employee> getData() {
        List<Employee> data = new ArrayList<>();
        String[] ids = {"310", "313", "320"};
        String[] noms = {"EL Hajji", "EL Badaoui", "Raki"};
        String[] villes = {"Kenitra", "Rabat", "Casa"};
        String[] descriptions = {"Plombier", "Etudiante", "name of three"};

        for (int i=0; i<ids.length; i++) {
            data.add(new Employee(ids[i], noms[i], villes[i], descriptions[i]));
        }
        return data;
    }
}