package com.example.ejercicio1_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio1_3.Modelo.Persona;

import java.util.List;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.MyViewHolder> {

    private List<Persona> listaPersonas;

    public void setListaDePersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public AdaptadorPersona(List<Persona> personas) {
        this.listaPersonas = personas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaPersona = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_persona, viewGroup, false);
        return new MyViewHolder(filaPersona);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Persona persona = listaPersonas.get(i);

        String nombre = persona.getNombre();
        String apellido = persona.getApellido();
        int edad = persona.getEdad();
        String correo = persona.getCorreo();
        String direccion = persona.getDireccion();
        long id = persona.getId();

        myViewHolder.nombre.setText(nombre);
        myViewHolder.apellido.setText(apellido);
        myViewHolder.edad.setText(String.valueOf(edad));
        myViewHolder.correo.setText(correo);
        myViewHolder.direccion.setText(direccion);
        myViewHolder.id.setText(String.valueOf(id));
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, apellido, edad, correo, direccion, id;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tfNombre);
            this.apellido = itemView.findViewById(R.id.tfApellido);
            this.edad = itemView.findViewById(R.id.tfEdad);
            this.correo = itemView.findViewById(R.id.tfCorreo);
            this.direccion = itemView.findViewById(R.id.tfDireccion);
            this.id = itemView.findViewById(R.id.tfId);
        }
    }
}
