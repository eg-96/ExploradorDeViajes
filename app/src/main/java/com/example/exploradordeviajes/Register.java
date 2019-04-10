package com.example.exploradordeviajes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exploradordeviajes.Modelos.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class Register extends AppCompatActivity {
    private TextView mResponseTv;
    private static final String TAG = "Register activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText emailET = (EditText)findViewById(R.id.email);
        final EditText passwordET = (EditText)findViewById(R.id.password);
        Button submitBtn = (Button) findViewById(R.id.registrarse);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    Users user = new Users(email,password);
                    registrarUsuario(user);
                }
            }
        });
    }

    private void registrarUsuario(Users user){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("UserAuth",0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("email", user.getEmail());
                    editor.apply();

                    Intent intent = new Intent(Register.this, FlySearching.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Register.this, "Hubo un error al crear un usuario.",
                            Toast.LENGTH_SHORT).show();
                }
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
