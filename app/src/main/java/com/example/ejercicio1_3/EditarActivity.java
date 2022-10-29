package com.example.ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio1_3.Controlador.PersonaControlador;
import com.example.ejercicio1_3.Modelo.Persona;

public class EditarActivity extends AppCompatActivity {

    private TextView txtNombreE, txtApellidoE, txtEdadE, txtCorreoE, txtDireccionE;
    private Button btnActualizar, btnCancelarE;
    private Persona persona;
    private PersonaControlador personaControlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        txtNombreE = findViewById(R.id.txtNombreE);
        txtApellidoE = findViewById(R.id.txtApellidoE);
        txtEdadE = findViewById(R.id.txtEdadE);
        txtCorreoE = findViewById(R.id.txtCorreoE);
        txtDireccionE = findViewById(R.id.txtDireccionE);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnCancelarE = findViewById(R.id.btnCancelarE);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        personaControlador = new PersonaControlador(EditarActivity.this);

        long id = extras.getLong("id");
        String nombre = extras.getString("nombre");
        String apellido = extras.getString("apellido");
        int edad = extras.getInt("edad");
        String correo = extras.getString("correo");
        String direccion = extras.getString("direccion");
        persona = new Persona(nombre, apellido, edad, correo, direccion, id);


        txtNombreE.setText(persona.getNombre());
        txtApellidoE.setText(persona.getApellido());
        txtEdadE.setText(String.valueOf(persona.getEdad()));
        txtCorreoE.setText(persona.getCorreo());
        txtDireccionE.setText(persona.getDireccion());

        btnCancelarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombreE.setError(null);
                txtApellidoE.setError(null);
                txtEdadE.setError(null);
                txtCorreoE.setError(null);
                txtDireccionE.setError(null);

                String nuevoNombre = txtNombreE.getText().toString();
                String nuevoApellido = txtApellidoE.getText().toString();
                String posibleNuevaEdad = txtEdadE.getText().toString();
                String nuevoCorreo = txtCorreoE.getText().toString();
                String nuevaDireccion = txtDireccionE.getText().toString();

                if (nuevoNombre.isEmpty()) {
                    txtNombreE.setError("Escriba el nombre");
                    txtNombreE.requestFocus();
                    return;
                }

                if (nuevoApellido.isEmpty()) {
                    txtApellidoE.setError("Escriba el apellido");
                    txtApellidoE.requestFocus();
                    return;
                }

                if (posibleNuevaEdad.isEmpty()) {
                    txtEdadE.setError("Escriba la edad");
                    txtEdadE.requestFocus();
                    return;
                }

                if (nuevoCorreo.isEmpty()) {
                    txtCorreoE.setError("Escriba el correo");
                    txtCorreoE.requestFocus();
                    return;
                }

                if (nuevaDireccion.isEmpty()) {
                    txtDireccionE.setError("Escriba la direccion");
                    txtDireccionE.requestFocus();
                    return;
                }

                int nuevaEdad;
                try {
                    nuevaEdad = Integer.parseInt(posibleNuevaEdad);

                } catch (NumberFormatException e) {
                    txtEdadE.setError("Escriba un entero");
                    txtEdadE.requestFocus();
                    return;
                }

                Persona personaCambios = new Persona(nuevoNombre, nuevoApellido, nuevaEdad, nuevoCorreo, nuevaDireccion, persona.getId());
                int filasModificadas = personaControlador.actualizar(personaCambios);

                if (filasModificadas != 1) {
                    Toast.makeText(EditarActivity.this, "Error guardando cambios. Intente de nuevo. ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EditarActivity.this, "Persona Actualizada con exito. ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}