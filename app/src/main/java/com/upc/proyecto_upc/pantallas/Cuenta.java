package com.upc.proyecto_upc.pantallas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upc.proyecto_upc.LoginActivity;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cuenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cuenta extends Fragment {

    Usuario usuario;

    TextView cuentalblNombres,cuentalblApellido;
    Button cuentaBtnLogout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cuenta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cuenta.
     */
    // TODO: Rename and change types and number of parameters
    public static Cuenta newInstance(String param1, String param2) {
        Cuenta fragment = new Cuenta();
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

        View root =  inflater.inflate(R.layout.fragment_cuenta, container, false);
        cuentaBtnLogout = root.findViewById(R.id.cuentaBtnLogout);
        cuentalblNombres = root.findViewById(R.id.cuentalblNombre);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mdata = FirebaseDatabase.getInstance().getReference();
        if(user != null){
            cuentalblNombres.setText(user.getEmail());



        }

        cuentaBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

            }
        });


        return root;
    }
}