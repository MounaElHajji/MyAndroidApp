package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Message;
import com.example.myandroidapp.Models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("employees/{id}")
    Call<Person> getProfilePersonne(@Path("id") int id);

    @GET("/chat/chat/listMessages/{from}/{to}")
    Call<List<Message>> getChatMsgs(@Path("from") int from,@Path("to") int to);

    @POST("/chat/privateMessage/save")
    Call<Message> saveSentMsg(@Body Message message);
}
