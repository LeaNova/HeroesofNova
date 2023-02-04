package com.leanova.heroesofnova.modelos;

public class Jugar {
    private Personaje personaje;
    private Arma arma;
    private Armadura armadura;

    public Jugar() {}
    public Jugar(Personaje personaje, Arma arma, Armadura armadura) {
        this.personaje = personaje;
        if(arma == null) arma = new Arma(0, "Sin arma", 12, 1, 0, 0, 0, 0, 0, 0, 0,0, 1, 1, 1, 1, 0, "A puño libre", null);
        this.arma = arma;
        if(armadura == null) armadura = new Armadura(0, "Sin armadura", 0, 0, 0, 0, 1, 1, 0, 0, "Sin armadura a todo gas");
        this.armadura = armadura;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    /**GETTERS PERSONAJE**/
    public int getVida() {
        int vida = personaje.getVida();
        int vidaAux;
        return vida;
    }

    public int getEnergia() {
        int energia = personaje.getEnergia();
        return energia;
    }

    public int getAtaque() {
        int ataque = personaje.getAtaque();
        int ataqueAux = (int) Math.round(ataque * (arma.getModAtm() - 1)) + arma.getBonoAtk();
        return ataque + ataqueAux;
    }

    public int getAtkMagico() {
        int atkMagico = personaje.getAtkMagico();
        int atkMagicoAux = (int) Math.round(atkMagico * (arma.getModAtm() - 1)) + arma.getBonoAtm();
        return atkMagico + atkMagicoAux;
    }

    public int getDefensa() {
        int defensa = personaje.getDefensa();
        int defensaAux1 = (int) Math.round(defensa * (arma.getModDef() - 1)) + arma.getBonoDef();
        int defensaAux2 = (int) Math.round(defensa * (armadura.getModDef() - 1)) + armadura.getBonoDef();
        return defensa + defensaAux1 + defensaAux2;
    }

    public int getDefMagico() {
        int defMagico = personaje.getDefMagico();
        int defMagicoAux1 = (int) Math.round(defMagico * (arma.getModDfm() - 1)) + arma.getBonoDfm();
        int defMagicoAux2 = (int) Math.round(defMagico * (armadura.getModDfm() - 1)) + armadura.getBonoDfm();
        return defMagico + defMagicoAux1 + defMagicoAux2;
    }

    public int getAgilidad() {
        int agilidad = personaje.getAgilidad();
        int agilidadAux;
        return agilidad;
    }

    public int getEvasion() {
        int evasion = personaje.getEvasion();
        int evasionAux;
        return evasion;
    }

    public int getCritico() {
        int critico = personaje.getCritico();
        int criticoAux = arma.getBonoCrt();
        return critico + criticoAux;
    }

    public int getPrecision() {
        int precision = personaje.getPrecision();
        int precisionAux = arma.getBonoAcc();
        return precision + precisionAux;
    }
}
