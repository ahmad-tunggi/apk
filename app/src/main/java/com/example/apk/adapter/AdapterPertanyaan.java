package com.example.apk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apk.R;
import com.example.apk.model.DataAjuan;
import com.example.apk.model.DataPertanyaan;

import java.util.List;

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
//        holder.id_pertanyaan.setText(model.getId_pertanyaan());
//        holder.kd_surat.setText(model.getKd_surat());
        holder.pertanyaan.setText(model.getPertanyaan());



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
}
