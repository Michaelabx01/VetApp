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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upc.proyecto_upc.modelo.Usuario;

import java.util.regex.Pattern;

public class NuevoUsuarioActivity extends AppCompatActivity {




    Usuario usuario;
    FirebaseDatabase database;
    DatabaseReference reference;

    ImageView usuarioIvLogo;
    TextView usuariolblBienvenido,usuariolblLogin;
    TextInputLayout usuarioTxtLayoutNombre,usuarioTxtLayoutApellidos,usuarioTxtLayoutCorreo,usuarioTxtLayoutPassword,usuarioTxtLayoutPasswordC;
    EditText usuarioTxtNombre,usuarioTxtApellidos,usuarioTxtCorreo,usuarioTxtPassword,usuarioTxtPasswordC;
    Button usuarioBtnRegistrar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        asignarReferencias();
        iniciarFireBase();
        Authentication();
        LimpiarCampos();
    }

    private void LimpiarCampos() {
        usuarioTxtNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioTxtLayoutNombre.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usuarioTxtApellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioTxtLayoutApellidos.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usuarioTxtCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioTxtLayoutCorreo.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usuarioTxtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioTxtLayoutPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usuarioTxtPasswordC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioTxtLayoutPasswordC.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Authentication(){

        mAuth = FirebaseAuth.getInstance();
    }

    private void iniciarFireBase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public void transitionBack(){
        Intent intent = new Intent(NuevoUsuarioActivity.this, LoginActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View,String>(usuarioIvLogo,"logoImageTransition");
        pairs[1] = new Pair<View,String>(usuariolblBienvenido,"bienvenidoTransition");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(NuevoUsuarioActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
            finish();
        }

    }

    private boolean capturarDatos(){


        String nombre = usuarioTxtNombre.getText().toString();
        String apellido = usuarioTxtApellidos.getText().toString();
        String correo2 = usuarioTxtCorreo.getText().toString();
        String password = usuarioTxtPassword.getText().toString();
        String confirmpassword = usuarioTxtPasswordC.getText().toString();

        boolean valida = true;

        if(nombre.isEmpty()){
            usuarioTxtLayoutNombre.setError("Campo Obligatorio");
            valida = false;
        }
        if (apellido.isEmpty()){
            usuarioTxtLayoutApellidos.setError("Campo Obligatorio");
            valida = false;

        }
        if (correo2.isEmpty()){
            usuarioTxtLayoutCorreo.setError("Campo Obligatorio");
            valida = false;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(correo2).matches()){
            usuarioTxtLayoutCorreo.setError("Correo Invalido");
            valida = false;

        }
        if (password.isEmpty()){
            usuarioTxtLayoutPassword.setError("Campo Obligatorio");
            valida = false;

        }
        if(password.length()<=8){
            usuarioTxtLayoutPassword.setError("Se necesita más de 8 caracteres");
            valida = false;
        }
        if(!Pattern.compile("[0-9]").matcher(password).find()){
            usuarioTxtLayoutPassword.setError("Se necesita 1 carácter numérico");
            valida = false;
        }
        if(!Pattern.compile("[A-Z]").matcher(password).find()){
            usuarioTxtLayoutPassword.setError("Se necesita 1 letra Mayúscula");
            valida = false;
        }
        if(!Pattern.compile("\\S+$").matcher(password).find()){
            usuarioTxtLayoutPassword.setError("no se permiten espacios");
            valida = false;
        }


        if (confirmpassword.isEmpty()){
            usuarioTxtLayoutPasswordC.setError("Campo Obligatorio");
            valida = false;
        }
        if(!confirmpassword.equals(password)){
            usuarioTxtLayoutPasswordC.setError("Las contraseñas no coinciden");
            valida = false;

        }


        if (valida){

            usuario = new Usuario();
            //usuario.setId(UUID.randomUUID().toString());
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo2);
            usuario.setPassword(password);
            usuario.setConfirmpassword(confirmpassword);



            credenciales(correo2,password);

        }

        return valida;
    }

    public void credenciales(String correo , String password){



        mAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    String id = mAuth.getCurrentUser().getUid();

                    /*Map<String,Object> map = new HashMap<>();
                    map.put("nombre",usuarioTxtNombre.getText().toString());
                    map.put("apellido",usuarioTxtApellidos.getText().toString());
                    map.put("correo",usuarioTxtCorreo.getText().toString());
                    map.put("password",usuarioTxtPassword.getText().toString());*/


                    // reference.child("Usuario").child(id).setValue(map);
                    reference.child("Usuario").child(id).setValue(usuario);

                    //transicion nueva
                    Intent intent = new Intent(NuevoUsuarioActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
            }
        });
                }

    private void asignarReferencias() {
        //imagen
        usuarioIvLogo = findViewById(R.id.usuarioIvLogo);
        //textView
        usuariolblBienvenido = findViewById(R.id.usuariolblBienvenido);
        usuariolblLogin = findViewById(R.id.usuariolblLogin);
        //TextInputLayout
        usuarioTxtLayoutNombre = findViewById(R.id.usuarioTxtLayoutNombre);
        usuarioTxtLayoutApellidos = findViewById(R.id.usuarioTxtLayoutApellidos);
        usuarioTxtLayoutCorreo = findViewById(R.id.usuarioTxtLayoutCorreo);
        usuarioTxtLayoutPassword = findViewById(R.id.usuarioTxtLayoutPassword);
        usuarioTxtLayoutPasswordC = findViewById(R.id.usuarioTxtLayoutPasswordC);
        //EditText
        usuarioTxtNombre = findViewById(R.id.usuarioTxtNombre);
        usuarioTxtApellidos = findViewById(R.id.usuarioTxtApellidos);
        usuarioTxtCorreo = findViewById(R.id.usuarioTxtCorreo);
        usuarioTxtPassword = findViewById(R.id.usuarioTxtPassword);
        usuarioTxtPasswordC = findViewById(R.id.usuarioTxtPasswordC);
        //Button
        usuarioBtnRegistrar = findViewById(R.id.usuarioBtnRegistrar);




        usuarioBtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (capturarDatos()){
                   Toast.makeText(NuevoUsuarioActivity.this, "Se registro Correectamente", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(NuevoUsuarioActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
            }
        });

        usuariolblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });

    }
}