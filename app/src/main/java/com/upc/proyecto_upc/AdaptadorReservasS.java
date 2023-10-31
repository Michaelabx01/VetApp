package com.upc.proyecto_upc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.proyecto_upc.modelo.Especialdiades;

import java.util.List;

public class AdaptadorReservasS extends RecyclerView.Adapter<AdaptadorReservasS.MyViewHolder> {

    Context context;
    List<Especialdiades> lista;
    int size;

    public AdaptadorReservasS(Context context, List<Especialdiades> lista,int size) {
        this.context = context;
        this.lista = lista;
        this.size = size;
    }

    @NonNull
    @Override
    public AdaptadorReservasS.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_reserva_servicio,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorReservasS.MyViewHolder holder, int position) {
        Especialdiades especialdiades = lista.get(position);
        holder.reservasImg.setImageResource(especialdiades.getImagen());
        holder.reservasTitulo.setText(especialdiades.getTitulo());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView reservasImg;
        TextView reservasTitulo;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            reservasImg = itemView.findViewById(R.id.filareservaImg);
            reservasTitulo = itemView.findViewById(R.id.filareservaTxtTitulo);
        }
    }
}
