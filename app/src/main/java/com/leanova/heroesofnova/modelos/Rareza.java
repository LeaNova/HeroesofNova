package com.leanova.heroesofnova.modelos;

public class Rareza {
    private int idRareza;
    private String nombre;
    private String iniciales;
    private int nivelMin;
    private String codColor;

    public Rareza() { }
    public Rareza(int idRareza, String nombre, String iniciales, int nivelMin, String codColor) {
        this.idRareza = idRareza;
        this.nombre = nombre;
        this.iniciales = iniciales;
        this.nivelMin = nivelMin;
        this.codColor = codColor;
    }

    public int getIdRareza() {
        return idRareza;
    }

    public void setIdRareza(int idRareza) {
        this.idRareza = idRareza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public int getNivelMin() {
        return nivelMin;
    }

    public void setNivelMin(int nivelMin) {
        this.nivelMin = nivelMin;
    }

    public String getCodColor() {
        return codColor;
    }

    public void setCodColor(String codColor) {
        this.codColor = codColor;
    }

    public Rareza getRareza() {
        Rareza rareza = new Rareza(0, "Especial", "(♦)", 0, "#FFF000");
        return rareza;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
