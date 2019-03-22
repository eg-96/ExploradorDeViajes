package com.example.exploradordeviajes.Helpers;

public class ApiSuccess {

    private int statusCode;
    private String endpoint;
    private String message = "Succcess request";
    private String token;

    public int getStatusCode(){ return statusCode; }

    public String getEndpoint() { return endpoint; }

    public String getMessage(){ return message; }

    public String getToken() {return token;}

}
