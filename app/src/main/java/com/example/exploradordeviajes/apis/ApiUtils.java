package com.example.exploradordeviajes.apis;

public class ApiUtils {

    private ApiUtils() {}

    public static RegisterService getAPIService() {

        return RetroFitClient.getRetrofitInstance().create(RegisterService.class);
    }
}






