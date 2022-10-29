package com.example.ejercicio1_3.Modelo;

public class Persona {

    private String Nombre;
    private String Apellido;
    private int Edad;
    private String Correo;
    private String Direccion;

    private long id;

    public Persona(String nombre, String apellido, int edad, String correo, String direccion) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Edad = edad;
        this.Correo = correo;
        this.Direccion = direccion;
    }

    public Persona(String nombre, String apellido, int edad, String correo, String direccion, long id) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Edad = edad;
        this.Correo = correo;
        this.Direccion = direccion;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + Nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                ", edad='" + Edad + '\'' +
                ", correo='" + Correo + '\'' +
                ", direccion='" + Correo + '\'' +
                ", id=" + id +
                '}';
    }
}
