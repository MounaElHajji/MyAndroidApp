package com.example.myandroidapp.retrofit;

import com.example.myandroidapp.Activities.ToStringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBack {
    private static Retrofit retrofit;

    private static final String URL_Services = "http://192.168.171.44:8080/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_Services)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    //for get type profil!
    public static Retrofit getRetrofitIns(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_Services)
                            .addConverterFactory(new ToStringConverterFactory())
                .build();
        }
        return retrofit;
    }
}
