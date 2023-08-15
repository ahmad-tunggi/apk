package com.example.apk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apk.R;
import com.example.apk.adapter.AdapterDiverifikasi;
import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.model.DataDiverifikasi;
import com.example.apk.response.R_diverifikasi;
import com.example.apk.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerifikasiFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SessionManager sessionManager;
    List<DataDiverifikasi> dataDiverifikasi = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_verifikasi, container, false);
        sessionManager = new SessionManager(getContext());
        recyclerView = v.findViewById(R.id.telah_verif);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getDataDiverifikasi(sessionManager.getNik());
        return v;
    }

    private void getDataDiverifikasi(String nik) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_diverifikasi> call = apiRequest.diVerifikasi(nik);
        call.enqueue(new Callback<R_diverifikasi>() {
            @Override
            public void onResponse(Call<R_diverifikasi> call, Response<R_diverifikasi> response) {
                if (response.isSuccessful()) {
                    dataDiverifikasi = response.body().getData();
                    adapter = new AdapterDiverifikasi(getContext(), dataDiverifikasi);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "ada data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Gagal mendapatkan Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_diverifikasi> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

