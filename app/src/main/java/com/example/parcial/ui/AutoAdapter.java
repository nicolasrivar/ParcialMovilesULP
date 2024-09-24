package com.example.parcial.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;
import com.example.parcial.models.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.AutoViewHolder> {

    private List<Auto> autos = new ArrayList<>();

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto, parent, false);
        return new AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder holder, int position) {
        Auto auto = autos.get(position);
        holder.bind(auto);
    }

    @Override
    public int getItemCount() {
        return autos.size();
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
        notifyDataSetChanged();
    }

    static class AutoViewHolder extends RecyclerView.ViewHolder {
        private TextView textPatente, textMarca, textModelo, textPrecio;

        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            textPatente = itemView.findViewById(R.id.textPatente);
            textMarca = itemView.findViewById(R.id.textMarca);
            textModelo = itemView.findViewById(R.id.textModelo);
            textPrecio = itemView.findViewById(R.id.textPrecio);
        }

        public void bind(Auto auto) {
            textPatente.setText(auto.getPatente());
            textMarca.setText(auto.getMarca());
            textModelo.setText(auto.getModelo());
            textPrecio.setText(String.format("$%.2f", auto.getPrecio()));
        }
    }
}