package com.leanova.heroesofnova.modelos;

public class Mochila {
    private int idMochila;
    private String nombre;
    private String descripcion;
    private int pesoMax;

    public Mochila() { }
    public Mochila(int idMochila, String nombre, String descripcion, int pesoMax) {
        this.idMochila = idMochila;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pesoMax = pesoMax;
    }

    public int getIdMochila() {
        return idMochila;
    }

    public void setIdMochila(int idMochila) {
        this.idMochila = idMochila;
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

    public int getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }
}
