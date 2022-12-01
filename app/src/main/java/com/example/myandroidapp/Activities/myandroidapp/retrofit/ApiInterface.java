package com.example.myandroidapp.Activities.myandroidapp.retrofit;

import com.example.myandroidapp.Activities.myandroidapp.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("employees/getAll")
    Call<List<Employee>> getPost();

    @GET("employees/getpLombier")
    Call<List<Employee>> getPlombiers();

    @GET("employees/getPeiture")
    Call<List<Employee>> getPeniture();
}
