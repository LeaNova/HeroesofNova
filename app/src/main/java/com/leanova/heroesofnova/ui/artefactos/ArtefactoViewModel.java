package com.leanova.heroesofnova.ui.artefactos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtefactoViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Artefacto> listaArtefactos;
    private MutableLiveData<ArrayList<Artefacto>> mutableArtefactos;
    private MutableLiveData<String> mutableResultado;
    private MutableLiveData<Integer> mutableAccess;

    public ArtefactoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaArtefactos = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Artefacto>> getMutableArtefactos() {
        if(mutableArtefactos == null) {
            mutableArtefactos = new MutableLiveData<>();
        }
        return mutableArtefactos;
    }

    public LiveData<String> getMutableResultado() {
        if(mutableResultado == null) {
            mutableResultado = new MutableLiveData<>();
        }
        return mutableResultado;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    public ArrayList<Artefacto> getListaArtefactos() {
        return listaArtefactos;
    }

    public void obtenerArtefactos() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Artefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerArtefactos(token);
        artefactosPromesa.enqueue(new Callback<ArrayList<Artefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<Artefacto>> call, Response<ArrayList<Artefacto>> response) {
                if(response.isSuccessful()) {
                    listaArtefactos = response.body();
                    mutableArtefactos.postValue(listaArtefactos);
                    mutableResultado.postValue("");
                } else {
                    mutableResultado.postValue("No hay armas cargadas.");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Artefacto>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void obtenerPorNombre(String nombre) {
        if(!nombre.equals("")) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<ArrayList<Artefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().buscarArtefactos(nombre, token);
            artefactosPromesa.enqueue(new Callback<ArrayList<Artefacto>>() {
                @Override
                public void onResponse(Call<ArrayList<Artefacto>> call, Response<ArrayList<Artefacto>> response) {
                    if (response.isSuccessful()) {
                        listaArtefactos = response.body();
                        mutableArtefactos.postValue(listaArtefactos);
                        mutableResultado.postValue("");
                    } else {
                        mutableArtefactos.postValue(new ArrayList<>());
                        mutableResultado.postValue("Sin resultados.");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Artefacto>> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else  {
            obtenerArtefactos();
        }
    }
}