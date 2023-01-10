package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Mochila implements Serializable {
    private int idMochila;
    private String nombre;
    private int pesoMax;
    private String descripcion;

    public Mochila() { }
    public Mochila(int idMochila, String nombre, int pesoMax, String descripcion) {
        this.idMochila = idMochila;
        this.nombre = nombre;
        this.pesoMax = pesoMax;
        this.descripcion = descripcion;
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

    public int getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
