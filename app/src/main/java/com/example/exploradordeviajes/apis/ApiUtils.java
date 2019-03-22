package com.example.exploradordeviajes.apis;

import com.example.exploradordeviajes.LoginActivity;

public class ApiUtils {

    private ApiUtils() {}

    public static RegisterService getAPIService() {
        return RetroFitClient.getRetrofitInstance().create(RegisterService.class);
    }
    public static LoginService getAPIServiceActivity() {
        return RetroFitClient.getRetrofitInstance().create(LoginService.class);
    }
}






