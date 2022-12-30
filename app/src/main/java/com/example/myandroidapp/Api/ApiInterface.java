package com.example.myandroidapp.Api;

import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.ListFavoris;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.Rating;
import com.example.myandroidapp.Models.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("villes/getAllcities")
    Call<List<Ville>> getCities();

    @GET("employees/{id}")
    Call<Employee> getProfilePersonne(@Path("id") int id);

    @DELETE("account/{id}")
    Call<Void> DeleteAccount(@Path("id") int id);

    @Multipart
    @POST("/favList/deleteFav")
    Call<Void> DeleteFav(@Part("id1") int p1,@Part("id2") String p2);

    @GET("employees/checkLogin/{login}")
    Call<Boolean> checkLogin(@Path("login") String login);

    @GET("/favList/getFav/{id}")
    Call<List<ListFavoris>> getFav(@Path("id") int id);

    @Multipart
    @POST("/favList/addFav")
    Call<Person> addFav(@Part("p1") int p1,@Part("p2") String p2);

    @POST("/employees/{id}/ratings")
    Call<Employee> AddRating(@Body Rating rating, @Path("id") String id);

    @GET("/employees/{id}/ratings")
    Call<Float> SumRating(@Path("id") String id);

    @GET("/employees/{id}/sumRatingByEmp")
    Call<Integer> sumRatingsByImp(@Path("id") String id);

    @PUT("/{id}/ratings/{id_rating}")
    Call<Employee> updateRating(@Path("id") String id, @Path("id_rating") int id_rating, @Body Rating ratingRequest);
}