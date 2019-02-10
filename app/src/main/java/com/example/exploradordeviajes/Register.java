package com.example.exploradordeviajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.exploradordeviajes.Modelos.Users;
import com.example.exploradordeviajes.apis.ApiUtils;
import com.example.exploradordeviajes.apis.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class Register extends AppCompatActivity {
    private TextView mResponseTv;
    private RegisterService registerService;
    public static final String BASE_URL = "http://192.168.100.9:8080";
    private static final String TAG = "Register activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText emailET = (EditText)findViewById(R.id.email);
        final EditText passwordET = (EditText)findViewById(R.id.password);
        Button submitBtn = (Button) findViewById(R.id.registrarse);

        registerService = ApiUtils.getAPIService(BASE_URL);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    Users user = new Users(email,password);
                    registerUser(email,password);
                }
            }
        });
    }

    public void registerUser(String email, String password) {
        // asynchronously sends the request and notifies
        registerService.registerUser(email,password).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

}
