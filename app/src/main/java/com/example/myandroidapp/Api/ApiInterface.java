package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("employees/getAll")
    Call<List<Employee>> getPost();

    @GET("employees/getPlombier")
    Call<List<Employee>> getPlombiers();

    @GET("employees/getPeinture")
    Call<List<Employee>> getPeniture();

    @GET("employees/getElectricite")
    Call<List<Employee>> getElectricite();

    @GET("employees/getClimatisation")
    Call<List<Employee>> getClimatisation();

    @GET("employees/getBricolage")
    Call<List<Employee>> getBricolage();

    @GET("employees/getFemmeMenage")
    Call<List<Employee>> getFemmeMenage();


    @GET("employees/8")
    Call<Employee> getProfilePersonne();

    @GET("employees/{id}")
    Call<Employee> getProfilePersonne(@Path("id") int id);

    @DELETE("account/{id}")
    Call<Void> DeleteAccount(@Path("id") int id);

    @GET("employees/checkLogin/{login}")
    Call<Boolean> checkLogin(@Path("login") String login);


}
