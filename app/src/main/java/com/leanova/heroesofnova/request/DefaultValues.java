package com.leanova.heroesofnova.request;


import android.content.Context;
import android.util.Log;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultValues {
    private static ArrayList<Rol> listaRoles;
    private static Usuario usuario;

    public DefaultValues() {
        this.listaRoles = new ArrayList<>();
        obtenerRoles();
    }

    private void obtenerRoles() {
        Call<ArrayList<Rol>> rolesPromesa = ApiRetrofit.getServiceApi().obtenerRoles();
        rolesPromesa.enqueue(new Callback<ArrayList<Rol>>() {
            @Override
            public void onResponse(Call<ArrayList<Rol>> call, Response<ArrayList<Rol>> response) {
                if(response.isSuccessful()) {
                    listaRoles = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Rol>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public static ArrayList<Rol> getRoles() {
        return listaRoles;
    }

    public static void setUsuario(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtener(token);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    usuario = response.body();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Usuario getUsuario() {
        return usuario;
    }
}
