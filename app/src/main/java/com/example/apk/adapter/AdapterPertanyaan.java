package com.example.apk.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apk.R;
import com.example.apk.api.Services;
import com.example.apk.fragment.EditPertanyaanFragment;
import com.example.apk.interfaces.ApiRequest;
import com.example.apk.model.DataPertanyaan;
import com.example.apk.response.R_pertanyaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPertanyaan extends RecyclerView.Adapter<AdapterPertanyaan.HolderData> {

    private Context context;
    private List<DataPertanyaan> dataPertanyaans;

    public AdapterPertanyaan(Context context, List<DataPertanyaan> dataPertanyaans) {
        this.context = context;
        this.dataPertanyaans = dataPertanyaans;
    }

    @NonNull
    @Override
    public AdapterPertanyaan.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pertanyaan, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPertanyaan.HolderData holder, int position) {
        DataPertanyaan model = dataPertanyaans.get(position);
        holder.pertanyaan.setText(model.getPertanyaan());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletePertanyaan(model.getId_pertanyaan());
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id_pertanyaan", String.valueOf(model.getId_pertanyaan()));
                bundle.putString("pertanyaan", String.valueOf(model.getPertanyaan()));
                Fragment fragment = new EditPertanyaanFragment();
                fragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });


    }

    private void deletePertanyaan(String idPertanyaan) {
        Toast.makeText(context.getApplicationContext(), idPertanyaan, Toast.LENGTH_SHORT).show();
        ApiRequest apiRequest = Services.koneksi().create(ApiRequest.class);
        Call<R_pertanyaan> call = apiRequest.hapusPertanyaan(idPertanyaan);
        call.enqueue(new Callback<R_pertanyaan>() {
            @Override
            public void onResponse(Call<R_pertanyaan> call, Response<R_pertanyaan> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context.getApplicationContext(), "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context.getApplicationContext(), "Gagal menghapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<R_pertanyaan> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPertanyaans.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView id_pertanyaan, kd_surat, pertanyaan;
        public HolderData(@NonNull View itemView) {
            super(itemView);
//            id_pertanyaan = itemView.findViewById(R.id.id_pertanyaan);
//            kd_surat = itemView.findViewById(R.id.kd_surat);
            pertanyaan = itemView.findViewById(R.id.pertanyaan);
        }
    }

    private void navigateToLongClickFragment() {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        EditPertanyaanFragment longClickFragment = new EditPertanyaanFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, longClickFragment);
        fragmentTransaction.commit();
    }
}
