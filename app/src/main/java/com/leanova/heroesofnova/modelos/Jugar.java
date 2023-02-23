package com.leanova.heroesofnova.modelos;

public class Jugar {
    private Personaje personaje;
    private Arma arma;
    private Armadura armadura;
    private Artefacto corona, izquierda, derecha, adorno;

    //public Jugar() { }
    public Jugar(Personaje personaje, Arma arma, Armadura armadura, Artefacto corona, Artefacto izquierda, Artefacto derecha, Artefacto adorno) {
        this.personaje = personaje;

        if(arma == null) arma = new Arma().getArma();
        this.arma = arma;

        if(armadura == null) armadura = new Armadura().getArmadura();
        this.armadura = armadura;

        if(corona == null) corona = new Artefacto().getCorona();
        this.corona = corona;

        if(izquierda == null) izquierda = new Artefacto().getIzquierda();
        this.izquierda = izquierda;

        if(derecha == null) derecha = new Artefacto().getDerecha();
        this.derecha = derecha;

        if(adorno == null) adorno = new Artefacto().getAdorno();
        this.adorno = adorno;
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

    public Artefacto getCorona() {
        return corona;
    }

    public void setCorona(Artefacto corona) {
        this.corona = corona;
    }

    public Artefacto getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Artefacto izquierda) {
        this.izquierda = izquierda;
    }

    public Artefacto getDerecha() {
        return derecha;
    }

    public void setDerecha(Artefacto derecha) {
        this.derecha = derecha;
    }

    public Artefacto getAdorno() {
        return adorno;
    }

    public void setAdorno(Artefacto adorno) {
        this.adorno = adorno;
    }

    /**SET GAME**/
    public void setGame() {
        personaje.setVidaMax(getVida());
        personaje.setEnergiaMax(getEnergia());
    }

    /**FUNCIONES DE VIDA Y ENERGIA**/
    public void recuperar(String opcion, int total) {
        if(opcion.equals("Vida")) {
            personaje.recuperarVida(total);
        } else {
            personaje.recuperarEnergia(total);
        }
    }

    public void recibirDanio(String opcion, int danio) {
        if(opcion.equals("Fisico")) {
            personaje.recibirDanioFisico(danio, getDefensa());
        } else {
            personaje.recibirDanioMagico(danio, getDefMagico());
        }
    }

    /**FUNCIONES DE ATAQUE**/
    public int atacar(String opcion, int nivel, int dado, boolean especial) {
        int gastarEspecial = Math.round(5 * (personaje.getNivel()/2));
         //Verificar si el ataque es especial y luego hace la devolucion dependiendo del tipo.
         if(especial && personaje.tengoEnergia(gastarEspecial)) {
             if(opcion.equals("Fisico")) {
                 personaje.gastarEnergia(gastarEspecial);
                 return arma.ataqueFisicoEspecial(dado, getAtaque());
             }

             if(opcion.equals("Magico")) {
                 personaje.gastarEnergia(gastarEspecial);
                 return arma.ataqueMagicoEspecial(dado, getAtkMagico());
             }
         }

         //Si no es especial devuelve el respectivo ataque.
         if(opcion.equals("Fisico")) {
             return arma.ataqueFisico(dado, getAtaque());
         }

         if(opcion.equals("Magico")) {
             return arma.ataqueMagico(dado, getAtkMagico());
         }

         //Si es hechizo entra aca.
         if(opcion.equals("Hechizo")) {
             int gastar = (nivel+1) * (personaje.getNivel() / 3) * 10;
             if(personaje.tengoEnergia(gastar)) {
                 personaje.gastarEnergia(gastar);
                 return personaje.ataqueHechizo(dado, nivel, getAtkMagico());
             }
         }

         //Return 0 por si pasa algo no esperado.
         return 0;
    }

    /**GETTERS STATS PERSONAJE**/
    public int getVida() {
        int vida = personaje.getVida();
        int vidaAux = corona.getBonoVida() + izquierda.getBonoVida() + derecha.getBonoVida() + adorno.getBonoVida();
        return vida + vidaAux;
    }

    public int getEnergia() {
        int energia = personaje.getEnergia();
        int energiaAux = corona.getBonoEnergia() + izquierda.getBonoEnergia() + derecha.getBonoEnergia() + adorno.getBonoEnergia();
        return energia + energiaAux;
    }

    public int getAtaque() {
        int ataque = personaje.getAtaque() + personaje.getAuxAtaque();
        int ataqueAux1 = Math.round(ataque * (arma.getModAtk() - 1)) + arma.getBonoAtk();
        int ataqueAux2 = corona.getBonoAtk() + izquierda.getBonoAtk() + derecha.getBonoAtk() + adorno.getBonoAtk();
        return ataque + ataqueAux1 + ataqueAux2;
    }

    public int getAtkMagico() {
        int atkMagico = personaje.getAtkMagico() + personaje.getAuxAtkMagico();
        int atkMagicoAux1 = Math.round(atkMagico * (arma.getModAtm() - 1)) + arma.getBonoAtm();
        int atkMagicoAux2 = corona.getBonoAtm() + izquierda.getBonoAtm() + derecha.getBonoAtm() + adorno.getBonoAtm();
        return atkMagico + atkMagicoAux1 + atkMagicoAux2;
    }

    public int getDefensa() {
        int defensa = personaje.getDefensa() + personaje.getAuxDefensa();
        int defensaAux1 = Math.round(defensa * (arma.getModDef() - 1)) + arma.getBonoDef();
        int defensaAux2 = Math.round(defensa * (armadura.getModDef() - 1)) + armadura.getBonoDef();
        int defensaAux3 = corona.getBonoDef() + izquierda.getBonoDef() + derecha.getBonoDef() + adorno.getBonoDef();
        return defensa + defensaAux1 + defensaAux2 + defensaAux3;
    }

    public int getDefMagico() {
        int defMagico = personaje.getDefMagico() + personaje.getAuxDefMagico();
        int defMagicoAux1 = Math.round(defMagico * (arma.getModDfm() - 1)) + arma.getBonoDfm();
        int defMagicoAux2 = Math.round(defMagico * (armadura.getModDfm() - 1)) + armadura.getBonoDfm();
        int defMagicoAux3 = corona.getBonoDfm() + izquierda.getBonoDfm() + derecha.getBonoDfm() + adorno.getBonoDfm();
        return defMagico + defMagicoAux1 + defMagicoAux2 + defMagicoAux3;
    }

    public int getAgilidad() {
        int agilidad = personaje.getAgilidad() + personaje.getAuxAgilidad();
        int agilidadAux = corona.getBonoDex() + izquierda.getBonoDex() + derecha.getBonoDex() + adorno.getBonoDex();
        return agilidad + agilidadAux;
    }

    public int getEvasion() {
        int evasion = personaje.getEvasion() + personaje.getAuxEvasion();
        int evasionAux = corona.getBonoEva() + izquierda.getBonoEva() + derecha.getBonoEva() + adorno.getBonoEva();
        return evasion + evasionAux;
    }

    public int getCritico() {
        int critico = personaje.getCritico() + personaje.getAuxCritico();
        int criticoAux = arma.getBonoCrt() + corona.getBonoCrt() + izquierda.getBonoCrt() + derecha.getBonoCrt() + adorno.getBonoCrt();
        return critico + criticoAux;
    }

    public int getPrecision() {
        int precision = personaje.getPrecision() + personaje.getAuxPrecision();
        int precisionAux = arma.getBonoAcc() + corona.getBonoAcc() + izquierda.getBonoAcc() + derecha.getBonoAcc() + adorno.getBonoAcc();
        return precision + precisionAux;
    }
}
