package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Item implements Serializable {
    private int idItem;
    private String nombre;
    private int tipoId;
    private int bonoVida;
    private int bonoEnergia;
    private int bonoAtk;
    private int bonoAtm;
    private int bonoDef;
    private int bonoDfm;
    private int bonoDex;
    private int bonoEva;
    private int bonoCrt;
    private int bonoAcc;
    private int precio;
    private float peso;
    private String descripcion;
    private Tipo tipo;

    public Item() { }
    public Item(int idItem,
                String nombre,
                int tipoId,
                int bonoVida,
                int bonoEnergia,
                int bonoAtk,
                int bonoAtm,
                int bonoDef,
                int bonoDfm,
                int bonoDex,
                int bonoEva,
                int bonoCrt,
                int bonoAcc,
                int precio,
                float peso,
                String descripcion,
                Tipo tipo) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.bonoVida = bonoVida;
        this.bonoEnergia = bonoEnergia;
        this.bonoAtk = bonoAtk;
        this.bonoAtm = bonoAtm;
        this.bonoDef = bonoDef;
        this.bonoDfm = bonoDfm;
        this.bonoDex = bonoDex;
        this.bonoEva = bonoEva;
        this.bonoCrt = bonoCrt;
        this.bonoAcc = bonoAcc;
        this.precio = precio;
        this.peso = peso;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getBonoVida() {
        return bonoVida;
    }

    public void setBonoVida(int bonoVida) {
        this.bonoVida = bonoVida;
    }

    public int getBonoEnergia() {
        return bonoEnergia;
    }

    public void setBonoEnergia(int bonoEnergia) {
        this.bonoEnergia = bonoEnergia;
    }

    public int getBonoAtk() {
        return bonoAtk;
    }

    public void setBonoAtk(int bonoAtk) {
        this.bonoAtk = bonoAtk;
    }

    public int getBonoAtm() {
        return bonoAtm;
    }

    public void setBonoAtm(int bonoAtm) {
        this.bonoAtm = bonoAtm;
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

    public int getBonoCrt() {
        return bonoCrt;
    }

    public void setBonoCrt(int bonoCrt) {
        this.bonoCrt = bonoCrt;
    }

    public int getBonoAcc() {
        return bonoAcc;
    }

    public void setBonoAcc(int bonoAcc) {
        this.bonoAcc = bonoAcc;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
