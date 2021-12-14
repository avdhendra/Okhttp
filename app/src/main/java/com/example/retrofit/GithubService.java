package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("users") //remaining url that u need to capture
    Call <List<GithubUser>> getUser();

    @GET("users/{id}")
    Call<GithubUser>getUserId(@Path("id")String id );
    @GET("search/users")
    Call<UserResponse> searchUsers(@Query("q")String query);
    // callback is used to get the response from api and it will set it in our POJO class

}
