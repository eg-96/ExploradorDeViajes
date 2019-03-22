package com.example.exploradordeviajes.Helpers;

import android.content.Context;

import com.example.exploradordeviajes.MainActivity;
import com.example.exploradordeviajes.apis.RegisterService;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ErrorsUtils {
    public static ApiError parserError(Response<?> response, Retrofit retrofitInstance){
        Converter<ResponseBody, ApiError> converter = retrofitInstance.responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try{
            error = converter.convert(response.errorBody());
        } catch (IOException e){
            return new ApiError();
        }
        return error;
    }
}
