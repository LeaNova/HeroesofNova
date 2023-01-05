package com.leanova.heroesofnova.modelos;

import java.util.Date;

public class Grupo {
    private int idGrupo;
    private int masterId;
    private String nombre ;
    private String pass;
    private String descripcion;
    private Date fechaCreado;
    private Usuario master;

    public Grupo() { }
    public Grupo(int idGrupo, int masterId, String nombre, String pass, String descripcion, Date fechaCreado, Usuario master) {
        this.idGrupo = idGrupo;
        this.masterId = masterId;
        this.nombre = nombre;
        this.pass = pass;
        this.descripcion = descripcion;
        this.fechaCreado = fechaCreado;
        this.master = master;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Usuario getMaster() {
        return master;
    }

    public void setMaster(Usuario master) {
        this.master = master;
    }
}
