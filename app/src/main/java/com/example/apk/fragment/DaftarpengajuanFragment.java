package com.example.apk.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apk.R;
import com.example.apk.adapter.AdapterAjuan;
import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.model.DataAjuan;
import com.example.apk.response.R_ajuan;
import com.example.apk.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarpengajuanFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<DataAjuan> dataAjuans = new ArrayList<>();
    SessionManager sessionManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daftarpengajuan, container, false);
        sessionManager = new SessionManager(getContext());
        recyclerView = v.findViewById(R.id.list_ajuan);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getDataAjuan(sessionManager.getNik());
        return v;
    }

    private void getDataAjuan(String nik) {

        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_ajuan> call = apiRequest.daftarAjuan(nik);
        call.enqueue(new Callback<R_ajuan>() {
            @Override
            public void onResponse(Call<R_ajuan> call, Response<R_ajuan> response) {
                if (response.isSuccessful()){
                    dataAjuans = response.body().getData();
                    adapter = new AdapterAjuan(getContext(), dataAjuans);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_ajuan> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}