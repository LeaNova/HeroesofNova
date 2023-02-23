package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Raza implements Serializable {
    private int idRaza;
    private String nombre;
    private int vidaBase;
    private int energiaBase;
    private int baseAtk;
    private int baseAtm;
    private int baseDef;
    private int baseDfm;
    private int baseDex;
    private int baseEva;
    private int baseCrt;
    private int baseAcc;
    private String descripcion;
    private boolean disponible;

    public Raza() { }
    public Raza(int idRaza, String nombre, int vidaBase, int energiaBase, int baseAtk, int baseAtm, int baseDef, int baseDfm, int baseDex, int baseEva, int baseCrt, int baseAcc, String descripcion, boolean disponible) {
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.vidaBase = vidaBase;
        this.energiaBase = energiaBase;
        this.baseAtk = baseAtk;
        this.baseAtm = baseAtm;
        this.baseDef = baseDef;
        this.baseDfm = baseDfm;
        this.baseDex = baseDex;
        this.baseEva = baseEva;
        this.baseCrt = baseCrt;
        this.baseAcc = baseAcc;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidaBase() {
        return vidaBase;
    }

    public void setVidaBase(int vidaBase) {
        this.vidaBase = vidaBase;
    }

    public int getEnergiaBase() {
        return energiaBase;
    }

    public void setEnergiaBase(int energiaBase) {
        this.energiaBase = energiaBase;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }

    public int getBaseAtm() {
        return baseAtm;
    }

    public void setBaseAtm(int baseAtm) {
        this.baseAtm = baseAtm;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = baseDef;
    }

    public int getBaseDfm() {
        return baseDfm;
    }

    public void setBaseDfm(int baseDfm) {
        this.baseDfm = baseDfm;
    }

    public int getBaseDex() {
        return baseDex;
    }

    public void setBaseDex(int baseDex) {
        this.baseDex = baseDex;
    }

    public int getBaseEva() {
        return baseEva;
    }

    public void setBaseEva(int baseEva) {
        this.baseEva = baseEva;
    }

    public int getBaseCrt() {
        return baseCrt;
    }

    public void setBaseCrt(int baseCrt) {
        this.baseCrt = baseCrt;
    }

    public int getBaseAcc() {
        return baseAcc;
    }

    public void setBaseAcc(int baseAcc) {
        this.baseAcc = baseAcc;
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
}
