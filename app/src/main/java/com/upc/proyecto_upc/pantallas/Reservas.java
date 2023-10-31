package com.upc.proyecto_upc.pantallas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.upc.proyecto_upc.AdaptadorReservasS;
import com.upc.proyecto_upc.R;
import com.upc.proyecto_upc.modelo.Especialdiades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reservas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reservas extends Fragment {

    RecyclerView reservasRvServicio;
    List<Especialdiades> especialdiadesList = new ArrayList<>();
    AdaptadorReservasS adaptadorReservasS;

    //TimePicker
    Button reservasBtnHora;
    EditText reservaTxtHora;
    final Calendar clock = Calendar.getInstance();
    final int hour  = clock.get(Calendar.HOUR_OF_DAY);
    final int minute = clock.get(Calendar.MINUTE);



    //Calendario
    Button reservaBtnFecha;
    EditText reservaTxtFecha;
    final Calendar calendar = Calendar.getInstance();
    final int day  = calendar.get(Calendar.DAY_OF_MONTH);
    final int month = calendar.get(Calendar.MONTH);
    final int year = calendar.get(Calendar.YEAR);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reservas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservas.
     */
    // TODO: Rename and change types and number of parameters
    public static Reservas newInstance(String param1, String param2) {
        Reservas fragment = new Reservas();
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
        View root = inflater.inflate(R.layout.fragment_reservas, container, false);

        reservaBtnFecha = root.findViewById(R.id.reservaBtnFecha);
        reservaTxtFecha = root.findViewById(R.id.reservaTxtFecha);

        reservasBtnHora = root.findViewById(R.id.reservaBtnHora);
        reservaTxtHora = root.findViewById(R.id.reservaTxtHora);

        reservasRvServicio = root.findViewById(R.id.reservasRvServicio);

        adaptadorReservasS = new AdaptadorReservasS(getContext(),especialdiadesList,especialdiadesList.size());
        reservasRvServicio.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        reservasRvServicio.setAdapter(adaptadorReservasS);

        especialdiadesList.add(new Especialdiades(R.drawable.bano_mascota,"Baño"));
        especialdiadesList.add(new Especialdiades(R.drawable.cardiologia,"Cardiología"));
        especialdiadesList.add(new Especialdiades(R.drawable.dental,"Servicio Dental"));
        especialdiadesList.add(new Especialdiades(R.drawable.consulta,"Medicina Interna"));
        especialdiadesList.add(new Especialdiades(R.drawable.cirugia,"Cirugía General"));




        reservaBtnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        reservaTxtFecha.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        reservasBtnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        reservaTxtHora.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });



        return root;
    }
}