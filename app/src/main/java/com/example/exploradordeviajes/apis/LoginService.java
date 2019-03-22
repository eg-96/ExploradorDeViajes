package com.example.exploradordeviajes.apis;

import com.example.exploradordeviajes.Modelos.Users;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/api/login")
    Call<ResponseBody> loginUser(@Body Users user);
}
