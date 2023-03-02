package com.leanova.heroesofnova.request;

import android.content.Context;
import android.util.Log;

import com.leanova.heroesofnova.modelos.Categoria;
import com.leanova.heroesofnova.modelos.Rareza;
import com.leanova.heroesofnova.modelos.Seccion;
import com.leanova.heroesofnova.modelos.Tipo;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
