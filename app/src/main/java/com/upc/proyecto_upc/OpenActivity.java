package com.upc.proyecto_upc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OpenActivity extends AppCompatActivity {
    TextView openlblVetapp;
    ImageView openIvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        asignarReferencias();
        animaciones();


    }

    private void asignarReferencias() {
        openIvLogo = findViewById(R.id.opnenIvLogo);
        openlblVetapp = findViewById(R.id.openlblVetapp);
    }

    private void animaciones(){
        Animation animacion01 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion02 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        openIvLogo.setAnimation(animacion01);
        openlblVetapp.setAnimation(animacion02);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(OpenActivity.this,InicioActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(OpenActivity.this, LoginActivity.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(openIvLogo,"logoImageTransition");
                    pairs[1] = new Pair<View,String>(openlblVetapp,"bienvenidoTransition");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OpenActivity.this,pairs);
                        startActivity(intent,options.toBundle());
                    }else {
                        startActivity(intent);
                        finish();
                    }

                }


            }
        },4000);

    }
}