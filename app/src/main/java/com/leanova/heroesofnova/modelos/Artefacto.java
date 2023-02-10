package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Artefacto implements Serializable {
    private int idArtefacto;
    private String nombre;
    private int seccionId;
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
    private Seccion seccion;

    public Artefacto() { }
    public Artefacto(int idArtefacto,
                     String nombre,
                     int seccionId,
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
                     Seccion seccion) {
        this.idArtefacto = idArtefacto;
        this.nombre = nombre;
        this.seccionId = seccionId;
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
        this.seccion = seccion;
    }

    public int getIdArtefacto() {
        return idArtefacto;
    }

    public void setIdArtefacto(int idArtefacto) {
        this.idArtefacto = idArtefacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
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

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
