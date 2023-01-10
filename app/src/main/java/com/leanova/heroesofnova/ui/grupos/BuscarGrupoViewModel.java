package com.leanova.heroesofnova.ui.grupos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarGrupoViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Grupo> listaGrupos;
    private MutableLiveData<ArrayList<Grupo>> mutableGrupos;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<Integer> mutableShow;

    public BuscarGrupoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaGrupos = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Grupo>> getMutableGrupos() {
        if(mutableGrupos == null) {
            mutableGrupos = new MutableLiveData<>();
        }
        return mutableGrupos;
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
    public ArrayList<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void obtenerGrupos(String nombre) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Grupo>> gruposPromesa = ApiRetrofit.getServiceApi().obtenerGrupos(nombre, token);
        gruposPromesa.enqueue(new Callback<ArrayList<Grupo>>() {
            @Override
            public void onResponse(Call<ArrayList<Grupo>> call, Response<ArrayList<Grupo>> response) {
                if(response.isSuccessful()) {
                    listaGrupos = response.body();
                    mutableGrupos.postValue(listaGrupos);
                    mutableAviso.postValue("");
                    mutableShow.postValue(8);
                } else {
                    listaGrupos = new ArrayList<>();
                    mutableGrupos.postValue(listaGrupos);
                    mutableAviso.postValue("No se encontraron grupos.");
                    mutableShow.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Grupo>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}