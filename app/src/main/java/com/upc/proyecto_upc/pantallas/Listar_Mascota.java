package com.upc.proyecto_upc.pantallas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Mascota;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Listar_Mascota#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Listar_Mascota extends Fragment {

    RecyclerView mascotalistarRv;
    FloatingActionButton mascotalistarFBtn;
    FirebaseDatabase database;
    DatabaseReference reference;



    private List<Mascota> mascotaList = new ArrayList<>();
    AdaptadorView adaptadorView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Listar_Mascota() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Listar_Mascota.
     */
    // TODO: Rename and change types and number of parameters
    public static Listar_Mascota newInstance(String param1, String param2) {
        Listar_Mascota fragment = new Listar_Mascota();
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
        View root =  inflater.inflate(R.layout.fragment_listar__mascota, container, false);

        mascotalistarRv = root.findViewById(R.id.mascotalistarRv);
        mascotalistarFBtn = root.findViewById(R.id.mascotalistarFBtn);



        mascotalistarFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Fragment MascotaNueva = new Mascotas();
              FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.contenedor,MascotaNueva).commit();
            }
        });

        iniciarFirebase();
        mostrarMascota();
        return root;


    }

    private void mostrarMascota() {


        reference.child("Mascota").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mascotaList.clear();
                for (DataSnapshot item:snapshot.getChildren()){

                    Mascota mascota = item.getValue(Mascota.class);
                    mascotaList.add(mascota);

                }
                adaptadorView = new AdaptadorView(getContext(),mascotaList);
                mascotalistarRv.setAdapter(adaptadorView);
                mascotalistarRv.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                String id = mascotaList.get(pos).getId();
                mascotaList.remove(pos);
                adaptadorView.notifyDataSetChanged();
                reference.child("Mascota").child(id).removeValue();


            }
        }).attachToRecyclerView(mascotalistarRv);

    }




}