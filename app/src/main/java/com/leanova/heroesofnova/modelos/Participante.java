package com.leanova.heroesofnova.modelos;

import java.io.Serializable;

public class Participante implements Serializable {
    private int grupoId;
    private int usuarioId;
    private int personajeId;
    private Grupo grupo;
    private Usuario jugador;
    private Personaje personaje;

    public Participante() { }
    public Participante(int grupoId, int usuarioId, int personajeId, Grupo grupo, Usuario jugador, Personaje personaje) {
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
        this.personajeId = personajeId;
        this.grupo = grupo;
        this.jugador = jugador;
        this.personaje = personaje;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(int personajeId) {
        this.personajeId = personajeId;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
