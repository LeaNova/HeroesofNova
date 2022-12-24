package com.leanova.heroesofnova.modelos;

public class Personaje {
    //Principal
    private int idPersonaje;
    private int usuarioId;
    private String nombre;
    private int razaId;
    private int generoId;
    private String claseId;
    private int vida;
    private int nivel;
    private int experiencia;
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
    //Clases foráneas
    private Usuario usuario;
    private Raza raza;
    private Genero genero;
    private Clase clase;
    private Mochila mochila;

    public Personaje() { }
    public Personaje(int idPersonaje,
                     int usuarioId,
                     String nombre,
                     int razaId,
                     int generoId,
                     String claseId,
                     int vida,
                     int nivel,
                     int experiencia,
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
                     Usuario usuario,
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
        this.vida = vida;
        this.nivel = nivel;
        this.experiencia = experiencia;
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

    public String getClaseId() {
        return claseId;
    }

    public void setClaseId(String claseId) {
        this.claseId = claseId;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    /*******___Funciones___*******/
    /*Funciones para subir nivel*/
    public void subirHasta(int hasta) {
        if(this.nivel < 60 && (hasta <= 60 && hasta > 1)) {
            for (int desde = this.nivel; desde < hasta; desde++) {
                subirNivel();
            }
        }
    }

    public void subirNivel() {
        if(this.nivel <= 60) {
            this.nivel++;

            if (this.nivel % 3 == 0) {
                this.ataque *= this.clase.getModAtk();
                this.atkMagico *= this.clase.getModAtm();
                this.defensa *= this.clase.getModDef();
                this.defMagico *= this.clase.getModDfm();
                this.agilidad *= this.clase.getModDex();
                this.evasion *= this.clase.getModEva();
                this.critico *= this.clase.getModCrt();
                this.precision *= this.clase.getModAcc();
            }
        }
    }
}
