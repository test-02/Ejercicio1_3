package com.example.ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ejercicio1_3.Controlador.PersonaControlador;
import com.example.ejercicio1_3.Modelo.Persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Persona> listaPersonas;
    private RecyclerView recyclerView;
    private AdaptadorPersona adaptadorPersona;
    private PersonaControlador personaControlador;
    private Button btnAgregarNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personaControlador = new PersonaControlador(MainActivity.this);

        recyclerView = findViewById(R.id.recyclerView);
        btnAgregarNuevo = findViewById(R.id.btnAgregarNuevo);


        listaPersonas = new ArrayList<>();
        adaptadorPersona = new AdaptadorPersona(listaPersonas);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorPersona);

        viewListaPersona();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Persona personaSelecionada = listaPersonas.get(position);
                Intent intent = new Intent(MainActivity.this, EditarActivity.class);
                intent.putExtra("id", personaSelecionada.getId());
                intent.putExtra("nombre", personaSelecionada.getNombre());
                intent.putExtra("apellido", personaSelecionada.getApellido());
                intent.putExtra("edad", personaSelecionada.getEdad());
                intent.putExtra("correo", personaSelecionada.getCorreo());
                intent.putExtra("direccion", personaSelecionada.getDireccion());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Persona personaEliminar = listaPersonas.get(position);

                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                personaControlador.eliminar(personaEliminar);
                                viewListaPersona();
                                Toast.makeText(MainActivity.this, "Persona Eliminada con Exito. ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la persona " + personaEliminar.getNombre() + " " + personaEliminar.getApellido() +"?")
                        .create();
                dialog.show();
            }
        }));

        btnAgregarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewListaPersona();
    }

    private void viewListaPersona() {
        if (adaptadorPersona == null) return;
        listaPersonas = personaControlador.leer();
        adaptadorPersona.setListaDePersonas(listaPersonas);
        adaptadorPersona.notifyDataSetChanged();
    }
}