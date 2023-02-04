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
    //OTROS
    private int vidaAct;
    private int energiaAct;
    private int nextExp;

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

    public int getVidaAct() {
        return vidaAct;
    }

    public void setVidaAct() {
        this.vidaAct = vida;
    }

    public int getEnergiaAct() {
        return energiaAct;
    }

    public void setEnergiaAct() {
        this.energiaAct = energia;
    }

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

    /**FUNCIONES**/
    public void setGame() {
        setVidaAct();
        setEnergiaAct();
        setNextExp();
    }
    /*Funciones para subir nivel*/
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
                setVidaAct();
                setEnergiaAct();
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

    /*Funciones de Vida*/
    public void recibirDanioFisico(int danio) {
        if(vidaAct > 0) {
            int danioT = danio - Math.round(defensa * 0.5f);
            if(danioT > 0) {
                vidaAct -= danioT;
            }

            if (vidaAct < 0) {
                vidaAct = 0;
            }
        }
    }

    public void recibirDanioMagico(int danio) {
        if(vidaAct > 0) {
            int danioT = danio - Math.round(defMagico * 0.5f);
            if(danioT > 0) {
                vidaAct -= danioT;
            }

            if (vidaAct < 0) {
                vidaAct = 0;
            }
        }
    }

    public void recuperarVida(int heal) {
        if(vidaAct < vida) {
            vidaAct += heal;

            if (vidaAct > vida) {
                vidaAct = vida;
            }
        }
    }

    public void recuperarEnergia(int gain) {
        if(vidaAct < vida) {
            vidaAct += gain;

            if (vidaAct > vida) {
                vidaAct = vida;
            }
        }
    }

    public int getClaseBoost() {
        if(nivel > 3) {
            switch (clase.getNombre()) {
                case "Bárbaro":
                    return (int) Math.round((ataque * (nivel / 2)) / (nivel - 3));
                case "Paladín":
                    return (int) Math.round(atkMagico * 0.25);
                case "Brujo":
                    return (int) Math.round(atkMagico * (nivel / 2)) / (nivel - 3);
                default:
                    return 0;
            }
        }
        return 0;
    }
}
