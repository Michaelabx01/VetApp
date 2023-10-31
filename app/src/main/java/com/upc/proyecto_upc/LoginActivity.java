package com.upc.proyecto_upc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ImageView loginIvLogo;
    TextView loginlblBienvenido,loginlblRegistrate;
    TextInputLayout loginTxtLayoutCorreo,loginTxtLayoutPassword;
    EditText loginTextPassword,loginTxtCorreo;
    Button loginBtnIngresar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        asignarReferencias();
        LimpiarCampos();

    }

    private void LimpiarCampos() {
        loginTxtCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginTxtLayoutCorreo.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginTxtLayoutPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void transitionRegistrate(){
        Intent intent = new Intent(LoginActivity.this, NuevoUsuarioActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View,String>(loginIvLogo,"logoImageTransition");
        pairs[1] = new Pair<View,String>(loginlblBienvenido,"bienvenidoTransition");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
            finish();
        }
    }

    public void iniciarSesion(String correo,String password){

        mAuth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this,InicioActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciales no coinciden", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean capturarDatos(){
        String correo = loginTxtCorreo.getText().toString();
        String password = loginTextPassword.getText().toString();
        boolean valida = true;


        if (correo.isEmpty()){
            loginTxtLayoutCorreo.setError("Campo Obligatorio");
            valida = false;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            loginTxtLayoutCorreo.setError("Correo Invalido");
            valida = false;

        }
        if (password.isEmpty()){
            loginTxtLayoutPassword.setError("Campo Obligatorio");
            valida = false;

        }
        if(password.length()<=8){
            loginTxtLayoutPassword.setError("Se necesita más de 8 caracteres");
            valida = false;
        }
        if(!Pattern.compile("[0-9]").matcher(password).find()){
            loginTxtLayoutPassword.setError("Se necesita 1 carácter numérico");
            valida = false;
        }
        if(!Pattern.compile("[A-Z]").matcher(password).find()){
            loginTxtLayoutPassword.setError("Se necesita 1 letra Mayúscula");
            valida = false;
        }
        if(!Pattern.compile("\\S+$").matcher(password).find()){
            loginTxtLayoutPassword.setError("no se permiten espacios");
            valida = false;
        }

        if (valida){
            iniciarSesion(correo,password);
        }

        return valida;

    }

    private void asignarReferencias() {
        loginIvLogo = findViewById(R.id.loginIvLogo);
        loginlblBienvenido = findViewById(R.id.loginlblBienvenido);
        loginlblRegistrate = findViewById(R.id.loginlblRegistrate);
        loginTextPassword = findViewById(R.id.loginTxtPassword);
        loginTxtCorreo = findViewById(R.id.loginTxtCorreo);
        loginBtnIngresar = findViewById(R.id.loginBtnIngresar);

        loginTxtLayoutPassword = findViewById(R.id.loginTxtLayoutPassword);
        loginTxtLayoutCorreo = findViewById(R.id.loginTxtLayoutCorreo);

        loginlblRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionRegistrate();
            }
        });

        loginBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();

            }
        });



    }

}