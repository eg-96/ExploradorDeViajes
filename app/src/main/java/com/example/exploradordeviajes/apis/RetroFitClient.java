package com.example.exploradordeviajes.apis;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient extends Exception {


    private static Retrofit retrofit;

//Define the base URL//
public static final String BASE_URL = "http://192.168.100.9:8080";
//Create the Retrofit instance//

    public static Retrofit getRetrofitInstance() {
        OkHttpClient okHttpClient = initOkHttpInterceptor();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build(); //Build the Retrofit instance//
        }
        return retrofit;
    }

    public static OkHttpClient initOkHttpInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Response response = chain.proceed(request);

                        // todo deal with the issues the way you need to
                        if (response.code() == 500) {
                            return response;
                        }
                        if (response.code() == 400) {
                            return response;
                        }
                        if (response.code() == 200) {
                            return response;
                        }

                        return response;
                    }
                })
                .build();

        return okHttpClient;
    }
}