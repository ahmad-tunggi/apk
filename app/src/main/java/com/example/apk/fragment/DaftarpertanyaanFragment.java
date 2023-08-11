package com.example.apk.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.apk.R;
import com.example.apk.adapter.AdapterAjuan;
import com.example.apk.adapter.AdapterPertanyaan;
import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.model.DataAjuan;
import com.example.apk.model.DataPertanyaan;
import com.example.apk.response.R_ajuan;
import com.example.apk.response.R_pertanyaan;
import com.example.apk.utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DaftarpertanyaanFragment extends Fragment {
    private AppCompatButton verifikasi;

    RecyclerView recyclerView;

    FloatingActionButton btn_tambah;



    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;

    List<DataPertanyaan> dataPertanyaans = new ArrayList<>();


    FragmentManager fragmentManager;
    String kd_surat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame_layout, new DaftarpertanyaanFragment()).commit();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daftarpertanyaan, container, false);

        recyclerView = v.findViewById(R.id.list_pertanyaan);

        verifikasi = v.findViewById(R.id.verif);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        btn_tambah = v.findViewById(R.id.btn_tambah);
        kd_surat = getArguments().getString("kd_surat");
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("kd_surat", kd_surat);
                Fragment fragment = new TambahpertanyaanFragment();
                fragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
            }
        });
        String nim = getArguments().getString("nim");
        verifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifikasiAjuan(kd_surat);
            }
        });
        getDataPertanyaan(kd_surat);

        return v;






    }





    private void verifikasiAjuan(String kd_surat) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_ajuan> call = apiRequest.verifikasi(kd_surat);
        call.enqueue(new Callback<R_ajuan>() {
            @Override
            public void onResponse(Call<R_ajuan> call, Response<R_ajuan> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Berhasil melakukan verifikasi", Toast.LENGTH_SHORT).show();
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, new DaftarpengajuanFragment())
                            .commit();
                }else {
                    Toast.makeText(getContext(), "Gagal melakukan verifikasi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_ajuan> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getDataPertanyaan(String kd_surat) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_pertanyaan> call = apiRequest.daftarPertanyaan(kd_surat);
        call.enqueue(new Callback<R_pertanyaan>() {
            @Override
            public void onResponse(Call<R_pertanyaan> call, Response<R_pertanyaan> response) {
                if (response.isSuccessful()){
                    dataPertanyaans = response.body().getData();
                    adapter = new AdapterPertanyaan(getContext(), dataPertanyaans);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "data "+ dataPertanyaans, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_pertanyaan> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}