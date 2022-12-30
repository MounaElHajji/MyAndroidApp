package com.example.myandroidapp.Api;

import retrofit2.Call;
import retrofit2.http.POST;

public interface MyImageInterface {
    String IMAGEURL ="http://192.168.1.102:8080/" ;
    @POST("services/list")
    Call<String> getImageData(String image);
}
