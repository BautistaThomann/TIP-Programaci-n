package com.mycompany.tpi_programacion.modelos;

public class Administrador {
    private int id;
    private String nombre;

    public Administrador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Adminsitrador{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
