package com.example.prayerschedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prayerschedule.R;
import com.example.prayerschedule.models.Kuliah;

import java.util.ArrayList;
import java.util.List;

public class kuliah_adapter extends RecyclerView.Adapter<kuliah_adapter.KuliahViewHolder>{

    private Context mCtx;
    private ArrayList<Kuliah> kuliahs;
    private ArrayList<Kuliah> set;

    public kuliah_adapter(Context mCtx, ArrayList<Kuliah> kuliahs) {
        this.mCtx = mCtx;
        this.kuliahs = kuliahs;
    }

    @Override
    public KuliahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_kuliah, null);
        return new KuliahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KuliahViewHolder holder, int position) {
        Kuliah kuliah = kuliahs.get(position);

        holder.mNama.setText(kuliah.getNama());
        holder.mNim.setText(kuliah.getNim());
        holder.mKodeMatkul.setText(kuliah.getKode_matkul());
        holder.mNamaMatkul.setText(kuliah.getNama_matkul());
        holder.mSks.setText(kuliah.getSks());
//        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return kuliahs.size();
    }

    class KuliahViewHolder extends RecyclerView.ViewHolder {

        TextView mNama, mNim, mKodeMatkul, mNamaMatkul, mSks;

        ImageView imageView;

        public KuliahViewHolder(View itemView) {
            super(itemView);

            mNama = itemView.findViewById(R.id.tv_nama);
            mNim = itemView.findViewById(R.id.tv_nim);
            mKodeMatkul = itemView.findViewById(R.id.tv_kodeMatkul);
            mNamaMatkul = itemView.findViewById(R.id.tv_namaMatkul);
            mSks = itemView.findViewById(R.id.tv_sks);
        }
    }

    public void setFilter(ArrayList<Kuliah> newList){
        this.kuliahs = new ArrayList<>();
        this.kuliahs.addAll(newList);
        Log.d("Masa gk bisa", "Isinya ada?"+newList.size());
        notifyDataSetChanged();
    }
}
