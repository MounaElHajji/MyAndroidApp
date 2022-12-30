package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiServices {
    @GET("services/list")
    Call<List<Service>> getServices();
}
