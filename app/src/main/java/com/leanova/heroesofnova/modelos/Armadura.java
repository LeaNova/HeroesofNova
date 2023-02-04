package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Armadura implements Serializable {
    private int idArmadura;
    private String nombre;
    private int bonoDef;
    private int bonoDfm;
    private int bonoDex;
    private int bonoEva;
    private float modDef;
    private float modDfm;
    private int precio;
    private float peso;
    private String descripcion;

    public Armadura() { }
    public Armadura(int idArmadura,
                    String nombre,
                    int bonoDef,
                    int bonoDfm,
                    int bonoDex,
                    int bonoEva,
                    float modDef,
                    float modDfm,
                    int precio,
                    float peso,
                    String descripcion) {
        this.idArmadura = idArmadura;
        this.nombre = nombre;
        this.bonoDef = bonoDef;
        this.bonoDfm = bonoDfm;
        this.bonoDex = bonoDex;
        this.bonoEva = bonoEva;
        this.modDef = modDef;
        this.modDfm = modDfm;
        this.precio = precio;
        this.peso = peso;
        this.descripcion = descripcion;
    }

    public int getIdArmadura() {
        return idArmadura;
    }

    public void setIdArmadura(int idArmadura) {
        this.idArmadura = idArmadura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBonoDef() {
        return bonoDef;
    }

    public void setBonoDef(int bonoDef) {
        this.bonoDef = bonoDef;
    }

    public int getBonoDfm() {
        return bonoDfm;
    }

    public void setBonoDfm(int bonoDfm) {
        this.bonoDfm = bonoDfm;
    }

    public int getBonoDex() {
        return bonoDex;
    }

    public void setBonoDex(int bonoDex) {
        this.bonoDex = bonoDex;
    }

    public int getBonoEva() {
        return bonoEva;
    }

    public void setBonoEva(int bonoEva) {
        this.bonoEva = bonoEva;
    }

    public float getModDef() {
        return modDef;
    }

    public void setModDef(float modDef) {
        this.modDef = modDef;
    }

    public float getModDfm() {
        return modDfm;
    }

    public void setModDfm(float modDfm) {
        this.modDfm = modDfm;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
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
