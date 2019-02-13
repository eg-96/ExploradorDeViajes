package com.example.exploradordeviajes.apis;

import com.example.exploradordeviajes.Modelos.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterService {

    //Specify the request type and pass the relative URL//
    @POST("/api/register")
    // This will indicate that the request will have its MIME
    // type (a header field that identifies the format of the body of an HTTP
    // request or response) set to application/x-www-form-urlencoded and also
    // that its field names and values will be UTF-8 encoded before being URI-encoded
//    @FormUrlEncoded
    //Wrap the response in a Call object with the type of the expected result//
//    Call<Users> registerUser(@Field("email") String email,
//                             @Field("password") String password);

//   THIS WAY CAN BE USED ONLY IN A POST OR PUT
    Call<Users> registerUser(@Body Users user);
}
