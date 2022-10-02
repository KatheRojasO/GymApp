package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.List;

public class Inicio extends AppCompatActivity {

    TextView correo, nombreUsuario, idUsuario;
    Button eliminar, actualizar, cerrarSesion;
    EditText nuevaContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        for (Iterator<Usuario> it = Usuario.findAll(Usuario.class); it.hasNext(); ) {
            Usuario usuario = it.next();

            System.out.println(usuario.getNombre());
            System.out.println(usuario.getIdentificacion());
            System.out.println(usuario.getCorreo());
            System.out.println(usuario.getContrasena());

        }

        correo = findViewById(R.id.textUsuario);
        nombreUsuario = findViewById(R.id.textNombre);
        idUsuario = findViewById(R.id.textId);

        Bundle bundle = getIntent().getExtras();
        String correoElectronico = bundle.getString("correo");
        correo.setText(correoElectronico);

        List<Usuario> usuario = Usuario.find(Usuario.class, "correo = ? ", correoElectronico);
        Usuario registro = usuario.get(0);
        nombreUsuario.setText(registro.getNombre());
        idUsuario.setText(registro.getIdentificacion());


        //Eliminar usuario

        eliminar = findViewById(R.id.btnEliminar);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Usuario> usuario = Usuario.find(Usuario.class, "correo = ?", correoElectronico);
                Usuario registro = usuario.get(0);
                registro.delete();

                Intent intent = new Intent( Inicio.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //cambiar contraseña

        actualizar = findViewById(R.id.btnEditar);
        nuevaContrasena = findViewById(R.id.editTextPassword);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Hola");

                String txtNuevaContrasena = nuevaContrasena.getText().toString();

                List<Usuario> usuario = Usuario.find(Usuario.class, "correo = ?", correoElectronico);

                if (txtNuevaContrasena.length()<7){
                    nuevaContrasena.setError("Contraseña debe contener 7 caracteres");
                }else{
                    Usuario registro = usuario.get(0);
                    registro.setContrasena(txtNuevaContrasena);
                    registro.save();
                    Toast.makeText(getApplicationContext(), "Contraseña actualizada", Toast.LENGTH_LONG).show();
                }

            }
        });

        //cerrar sesión

        cerrarSesion =findViewById(R.id.btnCerrarSesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Inicio.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
