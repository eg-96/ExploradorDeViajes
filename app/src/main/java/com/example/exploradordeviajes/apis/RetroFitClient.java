package com.example.exploradordeviajes.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {


    private static Retrofit retrofit;

//Define the base URL//


//Create the Retrofit instance//

    public static Retrofit getRetrofitInstance(String BASE_URL) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)

//Add the converter//

                    .addConverterFactory(GsonConverterFactory.create())

//Build the Retrofit instance//

                    .build();
        }
        return retrofit;
    }
}