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
import com.example.apk.fragment.DaftarpertanyaanFragment;
import com.example.apk.fragment.EditPertanyaanFragment;
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
        holder.pertanyaan.setText(model.getPertanyaan());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id_pertanyaan", String.valueOf(model.getId_pertanyaan()));
                bundle.putString("pertanyaan", String.valueOf(model.getPertanyaan()));
                Fragment fragment = new EditPertanyaanFragment();
                fragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
                return true;
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
