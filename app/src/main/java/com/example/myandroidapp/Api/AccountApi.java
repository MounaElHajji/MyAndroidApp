package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/account/login")
    Call<Account> loginAccount(@Body Account account);
}
