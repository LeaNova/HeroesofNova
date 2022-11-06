package com.leanova.heroesofnova.request;


import android.util.Log;

import com.leanova.heroesofnova.modelos.Rol;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultValues {
    private static ArrayList<Rol> listaRoles;

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
}
