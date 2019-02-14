package com.example.exploradordeviajes.Helpers;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SuccessUtils {
    public static ApiSuccess parserError(Response<?> response, Retrofit retrofitInstance){
        Converter<ResponseBody, ApiSuccess> converter = retrofitInstance.responseBodyConverter(ApiSuccess.class, new Annotation[0]);

        ApiSuccess success;

        try{
            success = converter.convert(response.errorBody());
        } catch (IOException e){
            return new ApiSuccess();
        }
        return success;
    }
}
