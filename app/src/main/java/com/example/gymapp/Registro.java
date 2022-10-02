package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button registrarse;
        EditText nombre, identificacion, correo, contrasena;

        nombre = findViewById(R.id.txtRegistroNombre);
        identificacion = findViewById(R.id.txtRegistroIdentificacion);
        correo = findViewById(R.id.txtRegistroCorreo);
        contrasena = findViewById(R.id.txtRegistroContrasena);
        registrarse = findViewById(R.id.btnRegistrarseUsuario);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = nombre.getText().toString();
                String idUsuario = identificacion.getText().toString();
                String correoUsuario = correo.getText().toString();
                String contrasenaUsuario = contrasena.getText().toString();

                if (nombreUsuario.equals("") && idUsuario.equals("") && correoUsuario.equals("") && contrasenaUsuario.equals("")){
                    nombre.setError("Ingrese nombre");
                    identificacion.setError("Ingrese id");
                    correo.setError("Ingrese correo");
                    contrasena.setError("Ingrese contraseña");
                }
                else if(!correoUsuario.contains("@")){
                    correo.setError("Correo debe contener @");
                }
                else if(!correoUsuario.contains(".com")){
                    correo.setError("Correo debe contener .com");
                }
                else if(contrasenaUsuario.length()<7){
                    contrasena.setError("Contraseña debe contener 7 caracteres");
                }else {
                    Usuario usuario = new Usuario (nombreUsuario, idUsuario,  correoUsuario, contrasenaUsuario);
                    usuario.save();

                    nombre.setText("");
                    identificacion.setText("");
                    correo.setText("");
                    contrasena.setText("");

                    Toast.makeText(getApplicationContext(), "Usuario guardado", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( Registro.this, MainActivity.class);
                    startActivity(intent);
                }



            }
        });
    }


}