package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroidapp.Adapters.ServiceAdapter;
import com.example.myandroidapp.Models.Service;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Api.IApiServices;
import com.example.myandroidapp.retrofit.RetrofitBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listeServices extends AppCompatActivity {
    @BindView(R.id.recycleView)
//    ListView listServ;
    RecyclerView recyclerViewVar;
    IApiServices apiInterface;
    ServiceAdapter serviceAdapter;
    List<Service> services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services);
        setList();
        listServices();

    }


    private void setList() {
        recyclerViewVar = findViewById(R.id.recycleView);
        recyclerViewVar.setHasFixedSize(true);
        recyclerViewVar.setLayoutManager(new LinearLayoutManager(this));
    }

    private void listServices(){
        apiInterface = RetrofitBack.getRetrofitInstance().create(IApiServices.class);
        Call<List<Service>> call = apiInterface.getServices();
        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(listeServices.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Service> postList = (ArrayList<Service>) response.body();


               serviceAdapter= new ServiceAdapter(listeServices.this, postList);
                recyclerViewVar.setAdapter(serviceAdapter);

            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(listeServices.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnItemClick(R.id.recycleView)
    public void onListTransactionClicked(int position){
        Intent i = new Intent(this, EmployeelistActivity.class);
        startActivity(i);
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
        Intent intent = new Intent(this, HomeActivity.class);
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