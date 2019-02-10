package com.example.exploradordeviajes.apis;

public class ApiUtils {

    private ApiUtils() {}

    public static RegisterService getAPIService(String BASE_URL) {

        return RetroFitClient.getRetrofitInstance(BASE_URL).create(RegisterService.class);
    }
}
