package com.upc.proyecto_upc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.upc.proyecto_upc.pantallas.Cuenta;
import com.upc.proyecto_upc.pantallas.Home;
import com.upc.proyecto_upc.pantallas.Listar_Mascota;
import com.upc.proyecto_upc.pantallas.Reservas;
import com.upc.proyecto_upc.pantallas.Ubicacion;

public class InicioActivity extends AppCompatActivity {

    BottomNavigationView inicioBtnNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        asignarReferencias();
        cargarFragmento(new Home());

        /*FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        String token = task.getResult();
                        Log.d("===>",token);
                    }
                });*/


    }

    private void asignarReferencias() {
        inicioBtnNavigation = findViewById(R.id.inicioBtnNavigation);

        inicioBtnNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_home){
                    cargarFragmento(new Home());
                    item.setChecked(true);
                }

                if (item.getItemId() == R.id.menu_Reservaciones){
                    cargarFragmento(new Reservas());
                    item.setChecked(true);
                }

                if (item.getItemId() == R.id.menu_Mascotas){
                    cargarFragmento(new Listar_Mascota());
                    item.setChecked(true);
                }

                if (item.getItemId() == R.id.menu_cuenta){
                    cargarFragmento(new Cuenta());
                    item.setChecked(true);
                }

                if (item.getItemId() == R.id.menu_mapa){
                    cargarFragmento(new Ubicacion());
                    item.setChecked(true);
                }

                return false;
            }
        });
    }

    private void cargarFragmento(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}