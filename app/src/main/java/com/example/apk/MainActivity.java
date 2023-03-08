package com.example.apk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.apk.fragment.DaftarpengajuanFragment;
import com.example.apk.fragment.DaftarpertanyaanFragment;
import com.example.apk.fragment.HomeFragment;
import com.example.apk.fragment.Input_pertanyaanFragment;
import com.example.apk.fragment.PengajuanFragment;
import com.example.apk.fragment.PengaturanFragment;
import com.example.apk.fragment.VerifikasiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.verifikasi:
                        fragment = new VerifikasiFragment();
                        break;
                    case R.id.pengajuan:
                        fragment = new PengajuanFragment();
                        break;
                    case R.id.pengaturan:
                        fragment = new PengaturanFragment();
                        break;
                    case R.id.list:
                        fragment= new DaftarpengajuanFragment();



                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragment).commit();
                return true;

            }
        });
    }

}