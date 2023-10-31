package com.upc.proyecto_upc.pantallas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upc.proyecto_upc.AdaptadorHome;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Especialdiades;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    RecyclerView rvHome;

    List<Especialdiades> listaEspecialdiades;
    AdaptadorHome adaptadorHome;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        listaEspecialdiades = new ArrayList<>();
        listaEspecialdiades.add(new Especialdiades(R.drawable.bano_mascota,"Baño",R.string.texto_ejemplo,"25.50"));
        listaEspecialdiades.add(new Especialdiades(R.drawable.cardiologia,"Cardiología",R.string.texto_ejemplo,"80.50"));
        listaEspecialdiades.add(new Especialdiades(R.drawable.dental,"Servicio Dental",R.string.texto_ejemplo,"70.5"));
        listaEspecialdiades.add(new Especialdiades(R.drawable.consulta,"Medicina Interna",R.string.texto_ejemplo,"40.5"));
        listaEspecialdiades.add(new Especialdiades(R.drawable.cirugia,"Cirugía General",R.string.texto_ejemplo,"125.5"));


        adaptadorHome = new AdaptadorHome(getContext(),listaEspecialdiades,listaEspecialdiades.size());
        rvHome = root.findViewById(R.id.rvHome1);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(adaptadorHome);


        return root;
    }


}