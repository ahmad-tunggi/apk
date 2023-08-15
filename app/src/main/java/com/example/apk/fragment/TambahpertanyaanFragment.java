package com.example.apk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apk.R;
import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahpertanyaanFragment extends Fragment {
    FragmentManager fragmentManager;
    AppCompatButton btn_cancel, btn_simpan;
    EditText edit_tambah;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tambahpertanyaan, container, false);

        btn_cancel = v.findViewById(R.id.btn_cancel);
        edit_tambah = v.findViewById(R.id.edit_tambah);
        btn_simpan = v.findViewById(R.id.btn_simpan);
        toolbar = v.findViewById(R.id.toolbar);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, new DaftarpengajuanFragment())
                        .commit();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = edit_tambah.getText().toString();
                String kd_surat = getArguments().getString("kd_surat");

                if (sText.isEmpty()){
                    edit_tambah.setError("Harus diisi!");
                } else {
                    postData(kd_surat, sText);
                    Toast.makeText(getContext(), "Tambah Pertanyaan Berhasil", Toast.LENGTH_SHORT).show();


                }
            }
        });

        return v;
    }

    private void postData(String kd_surat, String sText) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call call = apiRequest.tambahPertanyaan(kd_surat, sText);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    fragmentManager = requireActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, new DaftarpengajuanFragment())
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Tambah Pertanyaan Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure here
            }
        });
    }
}
