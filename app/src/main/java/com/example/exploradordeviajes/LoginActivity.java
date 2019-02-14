package com.example.exploradordeviajes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exploradordeviajes.Helpers.ApiError;
import com.example.exploradordeviajes.Helpers.ErrorsUtils;
import com.example.exploradordeviajes.Modelos.Users;
import com.example.exploradordeviajes.apis.ApiUtils;
import com.example.exploradordeviajes.apis.RegisterService;
import com.example.exploradordeviajes.apis.RetroFitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private RegisterService registerService;
    private static final String TAG = "Login activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        final AutoCompleteTextView mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        final EditText mPasswordView = (EditText) findViewById(R.id.password);
        Button submitBtn = (Button) findViewById(R.id.email_sign_in_button);

        registerService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString().trim();
                String password = mPasswordView.getText().toString().trim();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    Users user = new Users(email,password);
                    loginUser(user);
                }
            }
        });

    }

    public void loginUser(Users user) {

        // asynchronously sends the request and notifies
        registerService.loginUser(user).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call call, Response response) {

                if(response.isSuccessful()) {
                    showResponse(response.body());
                    Toast.makeText(getApplicationContext(),"This is my toast message",Toast.LENGTH_LONG).show();// Set your own toast  message
                    Log.i(TAG, "post submitted to API." + response.body());
                }else{
                    ApiError error = ErrorsUtils.parserError(response, RetroFitClient.getRetrofitInstance());
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();// Set your own toast  message
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                showBadResponse(t);
                Log.e(TAG, t.getMessage());
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(Object response) {
        System.out.println("================ Response ==================");
        System.out.println(response);
        System.out.println("==================================");
    }
    public void showBadResponse(Throwable response) {
        System.out.println("================BAD Response ==================");
        System.out.println(response);
        System.out.println("==================================");
    }



}

