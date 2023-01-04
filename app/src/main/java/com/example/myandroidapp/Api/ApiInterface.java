package com.example.myandroidapp.Api;

import android.media.Rating;

import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.ListFavoris;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.RatingEmp;
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

    @GET("villes/getAllcities")
    Call<List<Ville>> getCities();

    @GET("employees/{id}")
    Call<Employee> getProfilePersonne(@Path("id") int id);

    @DELETE("account/{id}")
    Call<Void> DeleteAccount(@Path("id") int id);

    @GET("/employees/getByCateg/{cat}")
    Call<List<Employee>> getByCateg(@Path("cat") String category);

    @Multipart
    @POST("/favList/deleteFav")
    Call<Void> DeleteFav(@Part("id1") int p1,@Part("id2") int p2);

    @GET("employees/checkLogin/{login}")
    Call<Boolean> checkLogin(@Path("login") String login);

    @GET("/favList/getFav/{id}")
    Call<List<ListFavoris>> getFav(@Path("id") int id);

    @Multipart
    @POST("/favList/addFav")
    Call<Person> addFav(@Part("p1") int p1,@Part("p2") int p2);


    //Ratings
    @POST("/employees/{id}/ratings/{id_client}")
    Call<Rating> AddRating(@Body RatingEmp rating, @Path("id") int id, @Path("id_client") int id_client);

    @GET("/employees/{id}/ratings/{id_client}")
    Call<Long> SumRating(@Path("id") int id,  @Path("id_client") int id_client);

    @GET("/employees/{id}/sumRatingByEmp/{id_client}")
    Call<Long> sumRatingsByImp(@Path("id") int id, @Path("id_client") int id_client);

    @GET("/employees/{id_client}/RatingEmpByClient/{id}")
    Call<Integer> getRatOfClientForEmp(@Path(value = "id_client") int id_client, @Path(value = "id") int id);

}