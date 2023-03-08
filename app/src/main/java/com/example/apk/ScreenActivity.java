package com.example.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreenActivity extends AppCompatActivity {

    private int waktu_loading=4000;
    //4000=4 dettik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login= new Intent(ScreenActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        },waktu_loading);

    }
}