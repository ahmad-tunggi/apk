package com.example.apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.model.DataLogin;
import com.example.apk.response.R_login;
import com.example.apk.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btn;
    private EditText el_username, el_password;
    private SharedPreferences sharedpreferences;
    ApiRequest apiInterfaces;
    SessionManager sessionManager;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(LoginActivity.this);
        if(sessionManager.isLoggedIn()){
            String username = sessionManager.getUserDetail().get(SessionManager.STATUS);
            if(username.equals("")){
                Toast.makeText(getApplicationContext(), "Login Dulu Bossque", Toast.LENGTH_SHORT).show();
            }else {
                moveToMain();
                }

            }
            el_username = findViewById(R.id.edit1);
            el_password = findViewById(R.id.edit2);

            AppCompatButton btnLogin = findViewById(R.id.btn_lgn);
            btnLogin.setOnClickListener(this);

        }

    private void login(String username, String password) {
        apiInterfaces = Services.koneksi().create(ApiRequest.class);
        Call<R_login> loginCall =apiInterfaces.login(username,password);

        loginCall.enqueue(new Callback<R_login>() {
            @Override
            public void onResponse(Call<R_login> call, Response<R_login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    sessionManager = new SessionManager(LoginActivity.this);
                    DataLogin loginData = response.body().getDataLogin();
                    sessionManager.createLoginSession(loginData);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<R_login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToMain() {
//        if (getlv)
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View view) {
    switch (view.getId()) {
        case R.id.btn_lgn:
            username = el_username.getText().toString();
            password = el_password.getText().toString();
            Toast.makeText(getApplicationContext(), username  +  password, Toast.LENGTH_SHORT).show();
            if (username.equals("")) {
                Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                el_username.setError("Masukan Username Anda!");
            } else if (password.equals("")) {
                el_password.setError("Masukan Password Anda!");
                Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            } else {
                login(username, password);
            }
            break;
        }
    }
}