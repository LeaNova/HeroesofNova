package com.leanova.heroesofnova.modelos;

import java.io.Serializable;
import java.util.Date;

public class Personaje implements Serializable {
    //Principal
    private int idPersonaje;
    private int usuarioId;
    private String nombre;
    private int razaId;
    private int generoId;
    private int claseId;
    private int nivel;
    private int experiencia;
    private int vida;
    private int energia;
    //Estadisticas
    private int ataque;
    private int atkMagico;
    private int defensa;
    private int defMagico;
    private int agilidad;
    private int evasion;
    private int critico;
    private int precision;
    //Extras
    private String descripcion;
    private int mochilaId;
    private boolean disponible;
    private Date fechaCreado;
    //Clases foráneas
    private Usuario usuario;
    private Raza raza;
    private Genero genero;
    private Clase clase;
    private Mochila mochila;

    /**OTROS**/
    private int vidaMax;
    private int vidaAct;
    private int energiaMax;
    private int energiaAct;
    private int nextExp;
    //Add-ons
    private int auxAtaque = 0;
    private int auxAtkMagico = 0;
    private int auxDefensa = 0;
    private int auxDefMagico = 0;
    private int auxAgilidad = 0;
    private int auxEvasion = 0;
    private int auxCritico = 0;
    private int auxPrecision = 0;

    public Personaje() { }
    public Personaje(int idPersonaje,
                     int usuarioId,
                     String nombre,
                     int razaId,
                     int generoId,
                     int claseId,
                     int nivel,
                     int experiencia,
                     int vida,
                     int energia,
                     int ataque,
                     int atkMagico,
                     int defensa,
                     int defMagico,
                     int agilidad,
                     int evasion,
                     int critico,
                     int precision,
                     String descripcion,
                     int mochilaId,
                     boolean disponible,
                     Date fechaCreado, Usuario usuario,
                     Raza raza,
                     Genero genero,
                     Clase clase,
                     Mochila mochila) {
        this.idPersonaje = idPersonaje;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.generoId = generoId;
        this.claseId = claseId;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vida = vida;
        this.energia = energia;
        this.ataque = ataque;
        this.atkMagico = atkMagico;
        this.defensa = defensa;
        this.defMagico = defMagico;
        this.agilidad = agilidad;
        this.evasion = evasion;
        this.critico = critico;
        this.precision = precision;
        this.descripcion = descripcion;
        this.mochilaId = mochilaId;
        this.disponible = disponible;
        this.fechaCreado = fechaCreado;
        this.usuario = usuario;
        this.raza = raza;
        this.genero = genero;
        this.clase = clase;
        this.mochila = mochila;
    }

    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRazaId() {
        return razaId;
    }

    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public int getClaseId() {
        return claseId;
    }

    public void setClaseId(int claseId) {
        this.claseId = claseId;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAtkMagico() {
        return atkMagico;
    }

    public void setAtkMagico(int atkMagico) {
        this.atkMagico = atkMagico;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getDefMagico() {
        return defMagico;
    }

    public void setDefMagico(int defMagico) {
        this.defMagico = defMagico;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getCritico() {
        return critico;
    }

    public void setCritico(int critico) {
        this.critico = critico;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMochilaId() {
        return mochilaId;
    }

    public void setMochilaId(int mochilaId) {
        this.mochilaId = mochilaId;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }

    /**OTROS**/
    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
        this.vidaAct = vidaMax;
    }

    public int getVidaAct() {
        return vidaAct;
    }

    public void setVidaGame() {
        if(vidaMax == 0) {
            vidaMax = vida;
        }
        this.vidaAct = vidaMax;
    }

    public void setEnergiaMax(int energiaMax) {
        this.energiaMax = energiaMax;
        this.energiaAct = energiaMax;
    }

    public int getEnergiaAct() {
        return energiaAct;
    }

    public void setEnergiaGame() {
        if(energiaMax == 0) {
            energiaMax = energia;
        }
        this.energiaAct = energiaMax;
    }

    /**Add-ons**/
    public int getAuxAtaque() {
        return auxAtaque;
    }

    public void setAuxAtaque(int auxAtaque) {
        this.auxAtaque = auxAtaque;
    }

    public int getAuxAtkMagico() {
        return auxAtkMagico;
    }

    public void setAuxAtkMagico(int auxAtkMagico) {
        this.auxAtkMagico = auxAtkMagico;
    }

    public int getAuxDefensa() {
        return auxDefensa;
    }

    public void setAuxDefensa(int auxDefensa) {
        this.auxDefensa = auxDefensa;
    }

    public int getAuxDefMagico() {
        return auxDefMagico;
    }

    public void setAuxDefMagico(int auxDefMagico) {
        this.auxDefMagico = auxDefMagico;
    }

    public int getAuxAgilidad() {
        return auxAgilidad;
    }

    public void setAuxAgilidad(int auxAgilidad) {
        this.auxAgilidad = auxAgilidad;
    }

    public int getAuxEvasion() {
        return auxEvasion;
    }

    public void setAuxEvasion(int auxEvasion) {
        this.auxEvasion = auxEvasion;
    }

    public int getAuxCritico() {
        return auxCritico;
    }

    public void setAuxCritico(int auxCritico) {
        this.auxCritico = auxCritico;
    }

    public int getAuxPrecision() {
        return auxPrecision;
    }

    public void setAuxPrecision(int auxPrecision) {
        this.auxPrecision = auxPrecision;
    }

    public void cleanAux() {
        auxAtaque = 0;
        auxAtkMagico = 0;
        auxDefensa = 0;
        auxDefMagico = 0;
        auxAgilidad = 0;
        auxEvasion = 0;
        auxCritico = 0;
        auxPrecision = 0;
    }
    //EXP
    public int getNextExp() {
        return nextExp;
    }

    public void setNextExp() {
        this.nextExp = (int) (100 * Math.pow(nivel, 2) + 300 * nivel);
    }

    @Override
    public String toString() {
        return nombre + " - Nivel" + nivel;
    }

    /**SET GAME**/
    public void setGame() {
        setVidaGame();
        setEnergiaGame();
        setNextExp();
    }

    /**FUNCIONES DE NIVELES**/
    public void subirHasta(int hasta) {
        if(nivel < 60 && (hasta <= 60 && hasta > 1)) {
            for (int desde = nivel; desde < hasta; desde++) {
                subirNivel();
            }
        }
    }

    public void subirNivel() {
        if(nivel < 60) {
            nivel++;
            setNextExp();

            if(nivel % 3 == 0) {
                ataque = Math.round(ataque * clase.getModAtk());
                atkMagico = Math.round(atkMagico * clase.getModAtm());
                defensa = Math.round(defensa * clase.getModDef());
                defMagico = Math.round(defMagico * clase.getModDfm());
                agilidad = Math.round(agilidad * clase.getModDex());
                evasion = Math.round(evasion * clase.getModEva());
                critico = Math.round(critico * clase.getModCrt());
                precision = Math.round(precision * clase.getModAcc());
            }

            if((nivel+1) % 3 == 0) {
                vida = Math.round(clase.getModVida() * vida);
                energia = Math.round(clase.getModEnergia() * energia);
                setVidaGame();
                setEnergiaGame();
            }
        }
    }

    public void subirExp(int exp) {
        this.experiencia += exp;

        if(experiencia >= nextExp) {
            while (experiencia >= nextExp) {
                if(nivel < 60) {
                    subirNivel();
                } else {
                    experiencia = nextExp;
                    break;
                }
            }
        }
    }

    /**FUNCIONES DE VIDA Y ENERGIA**/
    public void recibirDanioFisico(int danio, int defensaFisica) {
        if(vidaAct > 0) {
            int danioT = danio - Math.round(defensaFisica * 0.5f);
            if(danioT > 0) {
                vidaAct -= danioT;
            }

            if (vidaAct < 0) {
                vidaAct = 0;
            }
        }
    }

    public void recibirDanioMagico(int danio, int defensaMagica) {
        if(vidaAct > 0) {
            int danioT = danio - Math.round(defensaMagica * 0.5f);
            if(danioT > 0) {
                vidaAct -= danioT;
            }

            if (vidaAct < 0) {
                vidaAct = 0;
            }
        }
    }

    //Recuperar vida y/o energía
    public void recuperarVida(int heal) {
        if(vidaAct < vidaMax) {
            vidaAct += heal;

            if (vidaAct > vidaMax) {
                vidaAct = vidaMax;
            }
        }
    }
    public boolean tengoEnergia(int gastar) {
        return gastar < energiaAct;
    }

    public void gastarEnergia(int gastar) {
        energiaAct -= gastar;
    }


    public void recuperarEnergia(int gain) {
        if(energiaAct < energiaMax) {
            energiaAct += gain;

            if (energiaAct > energiaMax) {
                energiaAct = energiaMax;
            }
        }
    }

    public void usarItem(Item item) {
        if(vidaAct < vidaMax || energiaAct < energiaMax) {
            vidaAct += item.getBonoVida();
            energiaAct += item.getBonoEnergia();

            if (vidaAct > vidaMax || energiaAct > energiaMax) {
                vidaAct = vidaMax;
                energiaAct = energiaMax;
            }
        }

        if(item.getBonoAtk() + item.getBonoAtm() + item.getBonoDef() + item.getBonoDfm() + item.getBonoDex() + item.getBonoEva() + item.getBonoCrt() + item.getBonoAcc() > 0) {
            agregarExtras(item);
        }
    }

    /**Add-on**/
    private void agregarExtras(Item item) {
        auxAtaque = item.getBonoAtk();
        auxAtkMagico = item.getBonoAtm();
        auxDefensa = item.getBonoDef();
        auxDefMagico = item.getBonoDfm();
        auxAgilidad = item.getBonoDex();
        auxEvasion = item.getBonoEva();
        auxCritico = item.getBonoCrt();
        auxPrecision = item.getBonoAcc();
    }

    public void quitarExtras() {
        auxAtaque = 0;
        auxAtkMagico = 0;
        auxDefensa = 0;
        auxDefMagico = 0;
        auxAgilidad = 0;
        auxEvasion = 0;
        auxCritico = 0;
        auxPrecision = 0;
    }

    /**ATAQUE HECHIZO**/
    public int ataqueHechizo(int dado, int nivelHechizo, int base) {
        int dadoTotal = dado + nivelHechizo;

        float total = base * 0.15f;

        return Math.round(total * dadoTotal);
    }
}
