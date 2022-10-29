package com.example.ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio1_3.Controlador.PersonaControlador;
import com.example.ejercicio1_3.Modelo.Persona;

public class AgregarActivity extends AppCompatActivity {

    private TextView txtNombre, txtApellido, txtEdad, txtCorreo, txtDireccion;
    private Button btnGuardar, btnCancelar;
    private PersonaControlador personaControlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtApellido = (TextView) findViewById(R.id.txtApellido);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        txtCorreo = (TextView) findViewById(R.id.txtCorreo);
        txtDireccion = (TextView) findViewById(R.id.txtDireccion);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        personaControlador = new PersonaControlador(AgregarActivity.this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setError(null);
                txtApellido.setError(null);
                txtEdad.setError(null);
                txtCorreo.setError(null);
                txtDireccion.setError(null);

                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                String edadCadena = txtEdad.getText().toString();
                String correo = txtCorreo.getText().toString();
                String direccion = txtDireccion.getText().toString();

                if ("".equals(nombre)) {
                    txtNombre.setError("Escriba el nombre");
                    txtNombre.requestFocus();
                    return;
                }

                if ("".equals(apellido)) {
                    txtApellido.setError("Escriba el apellido");
                    txtApellido.requestFocus();
                    return;
                }

                if ("".equals(edadCadena)) {
                    txtEdad.setError("Escribe la edad");
                    txtEdad.requestFocus();
                    return;
                }

                if ("".equals(correo)) {
                    txtCorreo.setError("Escriba el correo");
                    txtCorreo.requestFocus();
                    return;
                }

                if ("".equals(direccion)) {
                    txtDireccion.setError("Escriba la direccion");
                    txtDireccion.requestFocus();
                    return;
                }

                int edad;
                try {
                    edad = Integer.parseInt(txtEdad.getText().toString());

                } catch (NumberFormatException e) {
                    txtEdad.setError("Escribe un entero");
                    txtEdad.requestFocus();
                    return;
                }

                Persona nuevaPersona = new Persona(nombre, apellido, edad, correo, direccion);
                long id = personaControlador.crear(nuevaPersona);

                if (id == -1) {
                    Toast.makeText(AgregarActivity.this, "Error al guardar. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AgregarActivity.this, "Persona agregada con exito. ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}