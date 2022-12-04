package com.example.myandroidapp.Api;
import static android.os.ParcelFileDescriptor.MODE_APPEND;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myandroidapp.Models.Employee;
import  com.example.myandroidapp.Models.Employee;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    @GET("employees/{id}")
    Call<Employee> getProfilePersonne(@Path("id") int id);
}
