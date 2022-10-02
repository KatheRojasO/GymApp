package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button registrarse, iniciarSesion;
        EditText correoElectronico, contrasena;

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Botón que lleva a registro
        registrarse=(Button)findViewById(R.id.btnRegistrarse);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent( MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

        // Login Usuario
        correoElectronico = findViewById(R.id.txtCorreoElectronico);
        contrasena = findViewById(R.id.txtContrasena);

        iniciarSesion=findViewById(R.id.btnIniciarSesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correoElectronico.getText().toString();
                String clave = contrasena.getText().toString();

                if(correo.equals("") && clave.equals("")){
                    correoElectronico.setError("Ingrese correo electrónico");
                    contrasena.setError("Ingrese contraseña");
                }
                else if (clave.length()<7){
                    contrasena.setError("Contraseña debe contener 7 caracteres");
                }

                List<Usuario> usuario = Usuario.find(Usuario.class, "correo = ? and contrasena = ?", correo, clave);

                if (usuario.size() != 0 ){
                    Intent intent = new Intent(MainActivity.this, Inicio.class);
                    intent.putExtra("correo", correo);
                    intent.putExtra("contrasena", clave);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}