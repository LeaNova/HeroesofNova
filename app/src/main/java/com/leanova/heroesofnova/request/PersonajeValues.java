package com.leanova.heroesofnova.request;

import android.content.Context;
import android.util.Log;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Raza;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajeValues {
    private static ArrayList<Genero> listaGeneros;
    private static ArrayList<Raza> listaRazas;
    private static ArrayList<Clase> listaClases;
    private static ArrayList<Mochila> listaMochilas;

    public PersonajeValues(Context context) {
        this.listaGeneros = new ArrayList<>();
        this.listaRazas = new ArrayList<>();
        this.listaClases = new ArrayList<>();
        this.listaMochilas = new ArrayList<>();
        obtenerTodo(context);
    }

    private void obtenerTodo(Context context) {
        obtenerGeneros(context);
        obtenerRazas(context);
        obtenerClases(context);
        obtenerMochilas(context);
    }

    private void obtenerGeneros(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Genero>> listaGeneroPromesa = ApiRetrofit.getServiceApi().obtenerGeneros(token);
        listaGeneroPromesa.enqueue(new Callback<ArrayList<Genero>>() {
            @Override
            public void onResponse(Call<ArrayList<Genero>> call, Response<ArrayList<Genero>> response) {
                if(response.isSuccessful()) {
                    listaGeneros = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Genero>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public static ArrayList<Genero> getGeneros() {
        return listaGeneros;
    }

    private void obtenerRazas(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Raza>> listaRazasPromesa = ApiRetrofit.getServiceApi().obtenerRazas(token);
        listaRazasPromesa.enqueue(new Callback<ArrayList<Raza>>() {
            @Override
            public void onResponse(Call<ArrayList<Raza>> call, Response<ArrayList<Raza>> response) {
                if(response.isSuccessful()) {
                    listaRazas = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Raza>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public static ArrayList<Raza> getRazas() {
        return listaRazas;
    }

    private void obtenerClases(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Clase>> listaClasesPromesa = ApiRetrofit.getServiceApi().obtenerClases(token);
        listaClasesPromesa.enqueue(new Callback<ArrayList<Clase>>() {
            @Override
            public void onResponse(Call<ArrayList<Clase>> call, Response<ArrayList<Clase>> response) {
                if(response.isSuccessful()) {
                    listaClases = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Clase>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public static ArrayList<Clase> getClases() {
        return listaClases;
    }

    private void obtenerMochilas(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

    }

    public static ArrayList<Mochila> getMochilas() {
        return listaMochilas;
    }
}
