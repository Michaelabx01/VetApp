package com.upc.proyecto_upc.pantallas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.proyecto_upc.InicioActivity;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Mascota;

import java.util.ArrayList;
import java.util.List;



public class AdaptadorView extends RecyclerView.Adapter<AdaptadorView.MyViewHolder>  {

    private Context context;
    private List<Mascota> mascotaList = new ArrayList<>();

    public AdaptadorView (Context context,List<Mascota> mascotaList){
        this.context= context;
        this.mascotaList = mascotaList;
    }



    @NonNull
    @Override
    public AdaptadorView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila_rv,parent,false);
        return new MyViewHolder(vista);
    }

    private void cargar(Fragment fragment){

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorView.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.nombre.setText(mascotaList.get(position).getNombre()+"");
        holder.peso.setText(mascotaList.get(position).getPeso()+"");
        holder.sexo.setText(mascotaList.get(position).getSexo()+"");
        holder.fecha.setText(mascotaList.get(position).getFecha()+"");
        holder.tipo.setText(mascotaList.get(position).getEspecie()+"");
        holder.raza.setText(mascotaList.get(position).getRaza()+"");

      holder.filaMascota.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {




               Toast.makeText(context, "HOLA", Toast.LENGTH_SHORT).show();

               Intent intent = new Intent(context,InicioActivity.class);



                intent.putExtra("pid",mascotaList.get(position).getId()+"");
                intent.putExtra("pnombre",mascotaList.get(position).getNombre()+"");
                intent.putExtra("ppeso",mascotaList.get(position).getPeso()+"");
                intent.putExtra("psexo",mascotaList.get(position).getSexo()+"");
                intent.putExtra("pfecha",mascotaList.get(position).getFecha()+"");
                intent.putExtra("ptipo",mascotaList.get(position).getEspecie());
                intent.putExtra("praza",mascotaList.get(position).getRaza());
               // context.startActivity(intent);
                return false;
            }



        });

    }

    @Override
    public int getItemCount() {
        return mascotaList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nombre,peso,fecha,sexo,tipo,raza;
        LinearLayout filaMascota;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            filaMascota = itemView.findViewById(R.id.filaMascota);

            nombre = itemView.findViewById(R.id.listarTxtNombre);
            peso = itemView.findViewById(R.id.listarTxtPeso);
            tipo = itemView.findViewById(R.id.listarTxtTipo);
            fecha = itemView.findViewById(R.id.listarTxtFecha);
            sexo = itemView.findViewById(R.id.listarTxtSexo);
            raza = itemView.findViewById(R.id.listarTxtRaza);

        }
    }
}
