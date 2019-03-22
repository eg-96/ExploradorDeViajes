package com.example.exploradordeviajes.apis.viajes;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ViajesService {

    @GET("/api/{userId}/viajes")
    Call<ResponseBody> getViajesUser(@Path("id") int idUser,
                                     @Header("Authorization") String token );
}
