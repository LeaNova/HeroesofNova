package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class InvArtefacto implements Serializable {
    private int mochilaId;
    private int personajeId;
    private int artefactoId;
    private int cantidad;
    private Artefacto artefacto;

    public InvArtefacto() { }
    public InvArtefacto(int mochilaId, int personajeId, int artefactoId, int cantidad, Artefacto artefacto) {
        this.mochilaId = mochilaId;
        this.personajeId = personajeId;
        this.artefactoId = artefactoId;
        this.cantidad = cantidad;
        this.artefacto = artefacto;
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

    public int getArtefactoId() {
        return artefactoId;
    }

    public void setArtefactoId(int artefactoId) {
        this.artefactoId = artefactoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Artefacto getArtefacto() {
        return artefacto;
    }

    public void setArtefacto(Artefacto artefacto) {
        this.artefacto = artefacto;
    }
}
