package com.upc.proyecto_upc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.proyecto_upc.modelo.Especialdiades;

import java.util.List;

public class AdaptadorHome extends RecyclerView.Adapter<AdaptadorHome.MyViewHolder> {

    Context context;
    List<Especialdiades> lista;
    int size;

    public AdaptadorHome(Context context, List<Especialdiades> lista, int size) {
        this.context = context;
        this.lista = lista;
        this.size = size;
    }

    @NonNull
    @Override
    public AdaptadorHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_home,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorHome.MyViewHolder holder, int position) {
            Especialdiades especialdiades = lista.get(position);
            holder.imagen.setImageResource(especialdiades.getImagen());
            holder.titulo.setText(especialdiades.getTitulo());
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.consulta_servicios,null);

                    ImageView consultaImg;
                    TextView titulo,desc,precio;

                    consultaImg = dialogView.findViewById(R.id.consultaImg);
                    titulo = dialogView.findViewById(R.id.consultaTxtTitulo);
                    desc = dialogView.findViewById(R.id.consultaTxtDesc);
                    precio = dialogView.findViewById(R.id.consultaTxtPrecio);

                    consultaImg.setImageResource(especialdiades.getImagen());
                    titulo.setText(especialdiades.getTitulo());
                    desc.setText(especialdiades.getDescripcion());
                    precio.setText(especialdiades.getPrecio());

                    builder.setView(dialogView);
                    builder.setCancelable(true);
                    builder.show();




                }
            });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView titulo;
        Button btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgFilaHome);
            titulo = itemView.findViewById(R.id.filaHomeTxtTitulo);
            btn = itemView.findViewById(R.id.filaHomeBtn);

        }


    }
}
