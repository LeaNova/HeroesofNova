package com.leanova.heroesofnova.modelos;

public class InvArmadura {
    private int mochilaId;
    private int personajeId;
    private int armaduraId;
    private int cantidad;
    private Armadura armadura;

    public InvArmadura() { }
    public InvArmadura(int mochilaId, int personajeId, int armaduraId, int cantidad, Armadura armadura) {
        this.mochilaId = mochilaId;
        this.personajeId = personajeId;
        this.armaduraId = armaduraId;
        this.cantidad = cantidad;
        this.armadura = armadura;
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

    public int getArmaduraId() {
        return armaduraId;
    }

    public void setArmaduraId(int armaduraId) {
        this.armaduraId = armaduraId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }
}
