package com.example.apk.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DaftarpertanyaanFragment extends Fragment {
    private AppCompatButton tambah;

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;

    List<DataPertanyaan> dataPertanyaans = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daftarpertanyaan, container, false);
        recyclerView = v.findViewById(R.id.list_pertanyaan);
        AppCompatButton tambah = v.findViewById(R.id.btn_tambah);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        String nim= getArguments().getString("nim");
//        Log.d("TAG", "onCreateView: "+nim);
//      Toast.makeText(getActivity(), nim, Toast.LENGTH_SHORT).show();
        getDataPertanyaan(nim);
        String kd_surat= getArguments().getString("kd_surat");
        Log.d("TAG", "onCreateView: "+kd_surat);

        return v;
    }


    private void getDataPertanyaan(String kd_surat) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_pertanyaan> call = apiRequest.daftarPertanyaan(kd_surat);
        call.enqueue(new Callback<R_pertanyaan>() {
            @Override
            public void onResponse(Call<R_pertanyaan> call, Response<R_pertanyaan> response) {
                if (response.isSuccessful()){
                    Log.d("TAG", "onResponse: "+response.body().getMessages());
                    dataPertanyaans = response.body().getData();
                    adapter = new AdapterPertanyaan(getContext(), dataPertanyaans);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
//                    Toast.makeText(getContext(), String , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_pertanyaan> call, Throwable t) {

            }
        });
    }
}