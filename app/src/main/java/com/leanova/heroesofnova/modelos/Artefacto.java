package com.leanova.heroesofnova.modelos;

import java.io.Serializable;
import java.util.Objects;

public class Artefacto implements Serializable {
    private int idArtefacto;
    private String nombre;
    private int seccionId;
    private int rarezaId;
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
    private boolean disponible;
    private Seccion seccion;
    private Rareza rareza;

    public Artefacto() { }
    public Artefacto(int idArtefacto,
                     String nombre,
                     int seccionId,
                     int rarezaId,
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
                     boolean disponible,
                     Seccion seccion,
                     Rareza rareza) {
        this.idArtefacto = idArtefacto;
        this.nombre = nombre;
        this.seccionId = seccionId;
        this.rarezaId = rarezaId;
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
        this.disponible = disponible;
        this.seccion = seccion;
        this.rareza = rareza;
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

    public int getRarezaId() {
        return rarezaId;
    }

    public void setRarezaId(int rarezaId) {
        this.rarezaId = rarezaId;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public Artefacto getCorona() {
        Artefacto corona = new Artefacto(
                0, "Sin corona", 0,
                0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, "Sin corona",
                true, new Seccion(0, "Especial", ""),
                new Rareza().getRareza());

        return corona;
    }

    public Artefacto getIzquierda() {
        Artefacto izquierda = new Artefacto(
                0, "Sin izquierda", 0,
                0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, "Sin izquierda",
                true, new Seccion(0, "Especial", ""),
                new Rareza().getRareza());

        return izquierda;
    }

    public Artefacto getDerecha() {
        Artefacto derecha = new Artefacto(
                0, "Sin derecha", 0,
                0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, "Sin derecha",
                true, new Seccion(0, "Especial", ""),
                new Rareza().getRareza());

        return derecha;
    }

    public Artefacto getAdorno() {
        Artefacto adorno = new Artefacto(
                0, "Sin adorno", 0,
                0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, "Sin adorno",
                true, new Seccion(0, "Especial", ""),
                new Rareza().getRareza());

        return adorno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artefacto artefacto = (Artefacto) o;
        return getIdArtefacto() == artefacto.getIdArtefacto();
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArtefacto);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
