package com.example.exploradordeviajes.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    private String token;

    public Users(String email, String password ){
        this.setEmail(email);
        this.setPassword(password);

    }

    public Users(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\''+
                '}';
    }
}
