package com.upc.proyecto_upc.pantallas;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Mascota;
import com.upc.proyecto_upc.modelo.Usuario;

import java.util.Calendar;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mascotas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mascotas extends Fragment {

    boolean registro = true;
    String id;

    Usuario usuario;
    Mascota mascota;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    //String id = mAuth.getCurrentUser().getProviderId();



    //Listas
    AutoCompleteTextView mascotaAcTxtEspecie;

    String selection;

    String[] especie = {"Perro","Gato"};
    String[] perro = {"BÓXER","BULLDOG","BULLMASTIFF","CHOW – CHOW","GOLDEN RETRIEVER","HUSKY SIBERIANO"
            ,"LABRADOR RETRIEVER","MASTIN NAPOLITANO","PASTOR ALEMÁN","PEKINÉS","POODLE","SCHNAUZER"
            ," SHIH TZU","VIRINGO"};
    String[] gato = {"PERSA","AZUL RUSO","SIAMES","ANGORA TURCO","SIBERIANO","MAINE COON","BENGALI"};

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterRaza;


    //Calendario
    Button mascotaBtnFecha;
    EditText mascotaTxtFecha;
    final Calendar calendar = Calendar.getInstance();
    final int day  = calendar.get(Calendar.DAY_OF_MONTH);
    final int month = calendar.get(Calendar.MONTH);
    final int year = calendar.get(Calendar.YEAR);

    //Sexo Mascota
    RadioButton mascotaRbMacho, mascotaRbHembra;
    String sexoMascota;
    TextInputLayout mascotaTxtLayoutEspecie;



    EditText mascotaTxtNombre,mascotaTxtPeso,mascotaTxtRaza;
    Button mascotaBtnRegistrar;
    TextView mascotalblSexo;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Mascotas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Mascotas.
     */
    // TODO: Rename and change types and number of parameters
    public static Mascotas newInstance(String param1, String param2) {
        Mascotas fragment = new Mascotas();
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

        View root = inflater.inflate(R.layout.fragment_mascotas, container, false);


        mascotaAcTxtEspecie = root.findViewById(R.id.mascotaAcTxtEspecie);
        mascotaBtnFecha = root.findViewById(R.id.mascotaBtnFecha);
        mascotaTxtNombre = root.findViewById(R.id.mascotaTxtNombre);
        mascotaRbMacho = root.findViewById(R.id.mascotaRbMacho);
        mascotaRbHembra = root.findViewById(R.id.mascotaRbHembra);
        mascotaTxtRaza = root.findViewById(R.id.mascotaTxtRaza);
        mascotaBtnRegistrar = root.findViewById(R.id.mascotaBrnRegistrar);
        mascotaTxtPeso = root.findViewById(R.id.mascotaTxtPeso);
        mascotalblSexo = root.findViewById(R.id.mascotalblSexo);
        mascotaTxtFecha = root.findViewById(R.id.mascotaTxtFecha);
        mascotaTxtLayoutEspecie = root.findViewById(R.id.mascotaTxtLayoutEspecie);

        llenadoLista();
        iniciarFireBase();
        //obtenerValores();




        mascotaBtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capturarDatos()){



                    reference.child("Mascota").child(mascota.getId()).setValue(mascota);
                    //reference.child("Mascota").push().setValue(mascota);

                    Toast.makeText(getContext(), "Se registro su Mascota", Toast.LENGTH_SHORT).show();

                    Fragment listaMascota = new Listar_Mascota();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor,listaMascota).commit();
                }
            }



        });

        mascotaRbMacho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexoMascota = mascotaRbMacho.getText().toString();
            }
        });

        mascotaRbHembra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexoMascota = mascotaRbHembra.getText().toString();


            }
        });

        //Calendario--------------------------------------------------------------------------------
        mascotaBtnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        mascotaTxtFecha.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        return root;

    }

    private void obtenerValores() {
        if (getActivity().getIntent().hasExtra("pid")){
            registro = false;
            id = getActivity().getIntent().getStringExtra("pid");
            mascotaTxtNombre.setText(getActivity().getIntent().getStringExtra("pnombre"));
            mascotaTxtPeso.setText(getActivity().getIntent().getStringExtra("ppeso"));
            mascotaTxtFecha.setText(getActivity().getIntent().getStringExtra("pfecha"));
            mascotaAcTxtEspecie.setText(getActivity().getIntent().getStringExtra("ptipo"));
            mascotaTxtRaza.setText(getActivity().getIntent().getStringExtra("praza"));
            sexoMascota = getActivity().getIntent().getStringExtra("psexo");


            }
        }


    private void iniciarFireBase() {

        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    private  void llenadoLista(){
        adapter = new ArrayAdapter<String>(getContext(),R.layout.lista_especie,especie);
        mascotaAcTxtEspecie.setAdapter(adapter);
    }


    private Boolean capturarDatos() {

        String nombre = mascotaTxtNombre.getText().toString();
        String peso = mascotaTxtPeso.getText().toString();
        String fecha = mascotaTxtFecha.getText().toString();
        String especie = mascotaAcTxtEspecie.getText().toString();
        String raza = mascotaTxtRaza.getText().toString();


        boolean valida = true;

        String a = sexoMascota;
        if (nombre.isEmpty()){
            mascotaTxtNombre.setError("Debe Ingresar un Nombre ");
            valida = false;
        }
        if (peso.isEmpty()){
            mascotaTxtPeso.setError("Debe Ingresar el Peso");
            valida = false;
        }

        if (mascotaRbMacho.isChecked() == false && mascotaRbHembra.isChecked() == false){

            mascotalblSexo.setError("Selecciona el Sexo de su Mascota");
            valida = false;
        }

        if (fecha.isEmpty()){
            mascotaTxtFecha.setError("Seleccione la Fecha de Nacimiento");

            valida = false;
        }
        if (especie.isEmpty()){
            mascotaTxtLayoutEspecie.setError("Campo Obligatorio");
            valida = false;
        }
        if (raza.isEmpty()){
            mascotaTxtRaza.setError("Ingrese la Raza de su mascota");
            valida = false;
        }
        if (valida){
            mascota = new Mascota();




            mascota.setId(UUID.randomUUID().toString());
            mascota.setNombre(nombre);
            mascota.setPeso(Double.parseDouble(peso));
            mascota.setSexo(sexoMascota);
            mascota.setFecha(fecha);
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
        }


        return valida;

    }


}