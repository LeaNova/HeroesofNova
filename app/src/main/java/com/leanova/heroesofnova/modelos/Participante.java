package com.leanova.heroesofnova.modelos;

public class Participante {
    private int grupoId;
    private int usuarioId;
    private int personajeId;
    private Grupo grupo;
    private Usuario usuario;
    private Personaje personaje;

    public Participante() { }
    public Participante(int grupoId, int usuarioId, int personajeId, Grupo grupo, Usuario usuario, Personaje personaje) {
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
        this.personajeId = personajeId;
        this.grupo = grupo;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
