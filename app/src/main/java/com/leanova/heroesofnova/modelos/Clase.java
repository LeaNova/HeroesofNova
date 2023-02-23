package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Clase implements Serializable {
    private int idClase;
    private String nombre;
    private float modVida;
    private float modEnergia;
    private float modAtk;
    private float modAtm;
    private float modDef;
    private float modDfm;
    private float modDex;
    private float modEva;
    private float modCrt;
    private float modAcc;
    private String descripcion;
    private boolean disponible;

    public Clase() { }
    public Clase(int idClase, String nombre, float modVida, float modEnergia, float modAtk, float modAtm, float modDef, float modDfm, float modDex, float modEva, float modCrt, float modAcc, String descripcion, boolean disponible) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.modVida = modVida;
        this.modEnergia = modEnergia;
        this.modAtk = modAtk;
        this.modAtm = modAtm;
        this.modDef = modDef;
        this.modDfm = modDfm;
        this.modDex = modDex;
        this.modEva = modEva;
        this.modCrt = modCrt;
        this.modAcc = modAcc;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getModVida() {
        return modVida;
    }

    public void setModVida(float modVida) {
        this.modVida = modVida;
    }

    public float getModEnergia() {
        return modEnergia;
    }

    public void setModEnergia(float modEnergia) {
        this.modEnergia = modEnergia;
    }

    public float getModAtk() {
        return modAtk;
    }

    public void setModAtk(float modAtk) {
        this.modAtk = modAtk;
    }

    public float getModAtm() {
        return modAtm;
    }

    public void setModAtm(float modAtm) {
        this.modAtm = modAtm;
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

    public float getModDex() {
        return modDex;
    }

    public void setModDex(float modDex) {
        this.modDex = modDex;
    }

    public float getModEva() {
        return modEva;
    }

    public void setModEva(float modEva) {
        this.modEva = modEva;
    }

    public float getModCrt() {
        return modCrt;
    }

    public void setModCrt(float modCrt) {
        this.modCrt = modCrt;
    }

    public float getModAcc() {
        return modAcc;
    }

    public void setModAcc(float modAcc) {
        this.modAcc = modAcc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**FUNCIONES**/
}
