package com.example.myandroidapp.Api;



import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Person;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AccountApi {
    @POST("/account/login")
    Call<Account> loginAccount(@Body Account account);

    @POST("/employees/save")
    Call<Account> signup(@Body Account account);

    @GET("/services/listString")
    Call<List<String>> listServices();

    @GET("/account/profiltype/{username}")
    Call<String> getTypeProfil(@Path("username") String username);
}