package com.example.apk.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apk.R;
import com.example.apk.fragment.DaftarpertanyaanFragment;
import com.example.apk.model.DataTelahverifikasi;

import java.util.List;

public class AdapterTelahverfikasi extends RecyclerView.Adapter<AdapterTelahverfikasi.HolderData> {
    private Context context;
    private List<DataTelahverifikasi> dataTelahverifikasi;

    public AdapterTelahverfikasi(Context context, List<DataTelahverifikasi> dataTelahverifikasi){

        this.context = context;
        this.dataTelahverifikasi = dataTelahverifikasi;
    }

    @NonNull
    @Override
    public AdapterTelahverfikasi.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ajuan, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTelahverfikasi.HolderData holder, int position) {
        DataTelahverifikasi model = dataTelahverifikasi.get(position);
        holder.nama.setText(model.getNama_lengkap());
        holder.nim.setText(model.getNim());
        holder.judul.setText(model.getJudul());
        holder.instansi.setText(model.getInstansi());
        holder.lokasi.setText(model.getLokasi_penelitian());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("nim", String.valueOf(model.getNim()));
                bundle.putString("kd_surat", String.valueOf(model.getKd_surat()));
                Fragment fragment = new DaftarpertanyaanFragment();
                fragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataTelahverifikasi.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView nama,nim,judul,instansi,lokasi;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            judul = itemView.findViewById(R.id.judul);
            instansi = itemView.findViewById(R.id.instansi);
            lokasi = itemView.findViewById(R.id.lokasi);
        }
    }
}
