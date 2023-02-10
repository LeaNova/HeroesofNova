package com.leanova.heroesofnova.modelos;

public class Seccion {
    private int idSeccion;
    private String nombre;
    private String descripcion;

    public Seccion(int idSeccion, String nombre, String descripcion) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
