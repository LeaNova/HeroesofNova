package com.leanova.heroesofnova.modelos;

public class InvArma {
    private int mochilaId;
    private int personajeId;
    private int armaId;
    private int cantidad;
    private Arma arma;

    public InvArma() { }
    public InvArma(int mochilaId, int personajeId, int armaId, int cantidad, Arma arma) {
        this.mochilaId = mochilaId;
        this.personajeId = personajeId;
        this.armaId = armaId;
        this.cantidad = cantidad;
        this.arma = arma;
    }

    public int getMochilaId() {
        return mochilaId;
    }

    public void setMochilaId(int mochilaId) {
        this.mochilaId = mochilaId;
    }

    public int getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(int personajeId) {
        this.personajeId = personajeId;
    }

    public int getArmaId() {
        return armaId;
    }

    public void setArmaId(int armaId) {
        this.armaId = armaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }
}
