package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @DELETE("account/{id}")
    Call<Void> DeleteAccount(@Path("id") int id);

    @GET("employees/checkLogin/{login}")
    Call<Boolean> checkLogin(@Path("login") String login);

    @GET("/chat/listChats/{myId}")
    Call<List<Message>> getConversations(@Path("myId") int id);

    @GET("/chat/listMessages/{from}/{to}")
    Call<List<Message>> getChatMsgs(@Path("from") int from,@Path("to") int to);

    @POST("/privateMessage/{to}")
    Call<Message> saveSentMsg(@Path("to") int to);
}
