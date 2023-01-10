package com.leanova.heroesofnova.request;

import com.leanova.heroesofnova.modelos.Usuario;

public class DefaultValues {
    private static Usuario usuario;

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static String getAccess() {
        return usuario.getRol().getNombre();
    }
}
