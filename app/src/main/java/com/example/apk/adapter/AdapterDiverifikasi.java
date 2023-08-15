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
import com.example.apk.model.DataDiverifikasi;

import java.util.List;

public class AdapterDiverifikasi extends RecyclerView.Adapter<AdapterDiverifikasi.Holderdata> {

    private Context context;
    private List<DataDiverifikasi> dataDiverifikasi;

    public AdapterDiverifikasi (Context context, List<DataDiverifikasi>dataDiverifikasi)
    {
        this.context = context;
        this.dataDiverifikasi = dataDiverifikasi;
    }

    @NonNull
    @Override
    public AdapterDiverifikasi.Holderdata onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.telah_verif, parent, false);
        Holderdata holderdata = new Holderdata(layout);
        return holderdata;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiverifikasi.Holderdata holder, int position) {
        DataDiverifikasi model = dataDiverifikasi.get(position);
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

        return dataDiverifikasi.size();
    }

    public class Holderdata extends RecyclerView.ViewHolder {
        TextView nama,nim,judul,instansi,lokasi;
        public Holderdata(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            judul = itemView.findViewById(R.id.judul);
            instansi = itemView.findViewById(R.id.instansi);
            lokasi = itemView.findViewById(R.id.lokasi);
        }

    }
}
