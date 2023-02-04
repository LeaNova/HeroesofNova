package com.leanova.heroesofnova.request;

import android.content.Context;
import android.util.Log;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Personaje;
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

    private static Personaje personaje;
    private static Jugar jugar;

    public PersonajeValues(Context context) {
        this.listaGeneros = new ArrayList<>();
        this.listaRazas = new ArrayList<>();
        this.listaClases = new ArrayList<>();
        this.listaMochilas = new ArrayList<>();
        obtenerTodo(context);
    }

    private void obtenerTodo(Context context) {
        String token = ApiRetrofit.obtenerToken(context);

        obtenerGeneros(token);
        obtenerRazas(token);
        obtenerClases(token);
        obtenerMochilas(token);
    }

    private void obtenerGeneros(String token) {
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

    private void obtenerRazas(String token) {
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

    private void obtenerClases(String token) {
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

    private void obtenerMochilas(String token) {
        Call<ArrayList<Mochila>> listaMochilasPromesa = ApiRetrofit.getServiceApi().obtenerMochilas(token);
        listaMochilasPromesa.enqueue(new Callback<ArrayList<Mochila>>() {
            @Override
            public void onResponse(Call<ArrayList<Mochila>> call, Response<ArrayList<Mochila>> response) {
                if(response.isSuccessful()) {
                    listaMochilas = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mochila>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public static ArrayList<Genero> getGeneros() {
        return listaGeneros;
    }

    public static ArrayList<Raza> getRazas() {
        return listaRazas;
    }

    public static ArrayList<Clase> getClases() {
        return listaClases;
    }

    public static ArrayList<Mochila> getMochilas() {
        return listaMochilas;
    }

    /**OTRAS COSAS**/
    public static Personaje getPersonaje() {
        return personaje;
    }

    public static void setPersonaje(Personaje p) {
        personaje = p;
    }

    public static Jugar getJugar() {
        return jugar;
    }

    public static void setJugar(Jugar j) {
        jugar = j;
    }
}
