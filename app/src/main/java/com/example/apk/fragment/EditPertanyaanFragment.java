package com.example.apk.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apk.R;
import com.example.apk.api.Services;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.response.R_pertanyaan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPertanyaanFragment extends Fragment {

    AppCompatButton btnEdit;
    EditText etPertanyaan;
    String pertanyaan, id_pertanyaan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_pertanyaan, container, false);

        btnEdit = v.findViewById(R.id.btn_edit);
        etPertanyaan = v.findViewById(R.id.pertanyaan);
        id_pertanyaan= getArguments().getString("id_pertanyaan");
        pertanyaan= getArguments().getString("pertanyaan");
        etPertanyaan.setText(pertanyaan);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPertanyaan.getText().toString().equals("")){
                    etPertanyaan.setError("Mohon di isi");
                }else {
                    String sPertanyaan = etPertanyaan.getText().toString();
                    editPertanyaan(sPertanyaan, id_pertanyaan);
                }
            }
        });

        return v;
    }

    private void editPertanyaan(String pertanyaan, String id_pertanyaan) {
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_pertanyaan> call = apiRequest.editPertanyaan(id_pertanyaan, pertanyaan);
        Toast.makeText(getActivity(), pertanyaan+id_pertanyaan, Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<R_pertanyaan>() {
            @Override
            public void onResponse(Call<R_pertanyaan> call, Response<R_pertanyaan> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Berhasil merubah data", Toast.LENGTH_SHORT).show();
                    EditPertanyaanFragment fragtry = new EditPertanyaanFragment();
                    FragmentManager mFragmentManager = getFragmentManager();
                    FragmentTransaction mFragmentTransaction = mFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, fragtry);
                    mFragmentTransaction.addToBackStack(null).commit();
                }else {
                    Toast.makeText(getContext(), "Gagal merubah data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_pertanyaan> call, Throwable t) {
//                Fragment fragment = new EditPertanyaanFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
                Toast.makeText(getContext(), "Berhasil mengubah tampilan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}