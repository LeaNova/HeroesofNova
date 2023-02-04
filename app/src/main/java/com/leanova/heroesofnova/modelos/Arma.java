package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Arma implements Serializable {
    private int idArma;
    private String nombre;
    private int categoriaId;
    private int danioArma;
    private int bonoArma;
    private int bonoAtk;
    private int bonoAtm;
    private int bonoDef;
    private int bonoDfm;
    private int bonoCrt;
    private int bonoAcc;
    private float modAtk;
    private float modAtm;
    private float modDef;
    private float modDfm;
    private int precio;
    private float peso;
    private String descripcion;
    private Categoria categoria;

    public Arma() { }
    public Arma(int idArma,
                String nombre,
                int categoriaId,
                int danioArma,
                int bonoArma,
                int bonoAtk,
                int bonoAtm,
                int bonoDef,
                int bonoDfm,
                int bonoCrt,
                int bonoAcc,
                float modAtk,
                float modAtm,
                float modDef,
                float modDfm,
                int precio,
                float peso,
                String descripcion,
                Categoria categoria) {
        this.idArma = idArma;
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.danioArma = danioArma;
        this.bonoArma = bonoArma;
        this.bonoAtk = bonoAtk;
        this.bonoAtm = bonoAtm;
        this.bonoDef = bonoDef;
        this.bonoDfm = bonoDfm;
        this.bonoCrt = bonoCrt;
        this.bonoAcc = bonoAcc;
        this.modAtk = modAtk;
        this.modAtm = modAtm;
        this.modDef = modDef;
        this.modDfm = modDfm;
        this.precio = precio;
        this.peso = peso;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getDanioArma() {
        return danioArma;
    }

    public void setDanioArma(int danioArma) {
        this.danioArma = danioArma;
    }

    public int getBonoArma() {
        return bonoArma;
    }

    public void setBonoArma(int bonoArma) {
        this.bonoArma = bonoArma;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre;
    }
    /**FUNCIONES**/
    public int ataque(Personaje personaje, String tipo) {
        int dadoR = (int) ((Math.random()*danioArma + 1) + bonoArma);

        float atk;
        if(tipo.equals("Físico")) {
            atk = personaje.getAtaque() * 0.05f;
        } else {
            atk = personaje.getAtkMagico() * 0.05f;
        }

        return (int) Math.round(atk * dadoR);
    }

    public int ataqueEspecial(Personaje personaje, String tipo) {
        int dadoR = (int) ((Math.random()*danioArma + 1) + bonoArma);

        float atk;
        if(tipo.equals("Físico")) {
            atk = personaje.getAtaque() * 0.10f + personaje.getClaseBoost();
        } else {
            atk = personaje.getAtkMagico() * 0.10f + personaje.getClaseBoost();
        }

        return (int) Math.round(atk * dadoR);
    }
}
