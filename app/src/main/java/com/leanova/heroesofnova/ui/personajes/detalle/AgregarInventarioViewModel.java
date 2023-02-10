package com.leanova.heroesofnova.ui.personajes.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInventarioViewModel extends AndroidViewModel {
    private Context context;
    private Personaje personaje;
    private MutableLiveData<ArrayList<Object>> mutableInventario;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<Integer> mutableShow;

    public AgregarInventarioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<ArrayList<Object>> getMutableInventario() {
        if(mutableInventario == null) {
            mutableInventario = new MutableLiveData<>();
        }
        return mutableInventario;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<Integer> getMutableShow() {
        if(mutableShow == null) {
            mutableShow = new MutableLiveData<>();
        }
        return mutableShow;
    }

    //FUNCIONES
    public void getPersonaje(Bundle bPersonaje) {
        personaje = (Personaje) bPersonaje.getSerializable("agregar");
    }

    public void buscarObjeto(String objeto, String nombre) {
        String token = ApiRetrofit.obtenerToken(context);

        switch(objeto) {
            case "Armadura":
                buscarArmaduras(nombre, token);
                break;
            case "Arma":
                buscarArmas(nombre, token);
                break;
            case "Item":
                buscarItems(nombre, token);
                break;
            case "Artefacto":
                buscarArtefactos(nombre, token);
                break;
            default: break;
        }
    }

    private void buscarArmaduras(String nombre, String token) {
        Call<ArrayList<Armadura>> armadurasPromesa = ApiRetrofit.getServiceApi().buscarArmaduras(nombre, token);
        armadurasPromesa.enqueue(new Callback<ArrayList<Armadura>>() {
            @Override
            public void onResponse(Call<ArrayList<Armadura>> call, Response<ArrayList<Armadura>> response) {
                if(response.isSuccessful()) {
                    mutableInventario.postValue(armaduraToObject(response.body()));
                    mutableAviso.postValue("");
                    mutableShow.postValue(8);
                } else {
                    mutableInventario.postValue(new ArrayList<>());
                    mutableAviso.postValue("No se encontraron armaduras.");
                    mutableShow.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Armadura>> call, Throwable t) {

            }
        });
    }

    private void buscarArmas(String nombre, String token) {
        Call<ArrayList<Arma>> armasPromesa = ApiRetrofit.getServiceApi().buscarArmas(nombre, token);
        armasPromesa.enqueue(new Callback<ArrayList<Arma>>() {
            @Override
            public void onResponse(Call<ArrayList<Arma>> call, Response<ArrayList<Arma>> response) {
                if(response.isSuccessful()) {
                    mutableInventario.postValue(armaToObject(response.body()));
                    mutableAviso.postValue("");
                    mutableShow.postValue(8);
                } else {
                    mutableInventario.postValue(new ArrayList<>());
                    mutableAviso.postValue("No se encontraron armas.");
                    mutableShow.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Arma>> call, Throwable t) {

            }
        });
    }

    private void buscarItems(String nombre, String token) {
        Call<ArrayList<Item>> itemsPromesa = ApiRetrofit.getServiceApi().buscarItems(nombre, token);
        itemsPromesa.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if (response.isSuccessful()) {
                    mutableInventario.postValue(itemToObject(response.body()));
                    mutableAviso.postValue("");
                    mutableShow.postValue(8);
                } else {
                    mutableInventario.postValue(new ArrayList<>());
                    mutableAviso.postValue("No se encontraron items.");
                    mutableShow.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {

            }
        });
    }

    private void buscarArtefactos(String nombre, String token) {
        Call<ArrayList<Artefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().buscarArtefactos(nombre, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<Artefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<Artefacto>> call, Response<ArrayList<Artefacto>> response) {
                if(response.isSuccessful()) {
                    mutableInventario.postValue(artefactoToObject(response.body()));
                    mutableAviso.postValue("");
                    mutableShow.postValue(8);
                } else {
                    mutableInventario.postValue(new ArrayList<>());
                    mutableAviso.postValue("No se encontraron artefactos.");
                    mutableShow.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Artefacto>> call, Throwable t) {

            }
        });
    }

    private ArrayList<Object> armaduraToObject(ArrayList<Armadura> body) {
        ArrayList<Object> lista = new ArrayList<>();
        lista.addAll(body);
        return lista;
    }

    private ArrayList<Object> armaToObject(ArrayList<Arma> body) {
        ArrayList<Object> lista = new ArrayList<>();
        lista.addAll(body);
        return lista;
    }

    private ArrayList<Object> itemToObject(ArrayList<Item> body) {
        ArrayList<Object> lista = new ArrayList<>();
        lista.addAll(body);
        return lista;
    }

    private ArrayList<Object> artefactoToObject(ArrayList<Artefacto> body) {
        ArrayList<Object> lista = new ArrayList<>();
        lista.addAll(body);
        return lista;
    }
}