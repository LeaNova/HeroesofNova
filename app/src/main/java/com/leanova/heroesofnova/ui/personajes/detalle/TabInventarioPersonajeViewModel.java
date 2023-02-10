package com.leanova.heroesofnova.ui.personajes.detalle;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvArtefacto;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabInventarioPersonajeViewModel extends AndroidViewModel {
    private Context context;
    private Personaje personaje;
    private MutableLiveData<Personaje> mutablePersonaje;
    private MutableLiveData<ArrayList<InvArma>> mutableArmas;
    private MutableLiveData<ArrayList<InvArmadura>> mutableArmaduras;
    private MutableLiveData<ArrayList<InvItem>> mutableItems;
    private MutableLiveData<ArrayList<InvArtefacto>> mutableArtefactos;

    public TabInventarioPersonajeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Personaje> getMutablePersonaje() {
        if(mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public LiveData<ArrayList<InvArma>> getMutableArmas() {
        if(mutableArmas == null) {
            mutableArmas = new MutableLiveData<>();
        }
        return mutableArmas;
    }

    public LiveData<ArrayList<InvArmadura>> getMutableArmaduras() {
        if(mutableArmaduras == null) {
            mutableArmaduras = new MutableLiveData<>();
        }
        return mutableArmaduras;
    }

    public LiveData<ArrayList<InvItem>> getMutableItems() {
        if(mutableItems == null) {
            mutableItems = new MutableLiveData<>();
        }
        return mutableItems;
    }

    public LiveData<ArrayList<InvArtefacto>> getMutableArtefactos() {
        if(mutableArtefactos == null) {
            mutableArtefactos = new MutableLiveData<>();
        }
        return mutableArtefactos;
    }

    //FUNCIONES
    public void getPersonaje() {
        personaje = PersonajeValues.getPersonaje();

        String token = ApiRetrofit.obtenerToken(context);

        obtenerArmas(personaje.getMochilaId(), personaje.getIdPersonaje(), token);
        obtenerArmaduras(personaje.getMochilaId(), personaje.getIdPersonaje(), token);
        obtenerItems(personaje.getMochilaId(), personaje.getIdPersonaje(), token);
        obtenerArtefactos(personaje.getMochilaId(), personaje.getIdPersonaje(), token);
    }

    private void obtenerArmas(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArma>> armasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmas(mochilaId, personajeId, token);
        armasPromesa.enqueue(new Callback<ArrayList<InvArma>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArma>> call, Response<ArrayList<InvArma>> response) {
                if(response.isSuccessful()) {
                    mutableArmas.postValue(response.body());
                } else {
                    mutableArmas.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArma>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    private void obtenerArmaduras(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArmadura>> armadurasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmaduras(mochilaId, personajeId, token);
        armadurasPromesa.enqueue(new Callback<ArrayList<InvArmadura>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArmadura>> call, Response<ArrayList<InvArmadura>> response) {
                if(response.isSuccessful()) {
                    mutableArmaduras.postValue(response.body());
                } else {
                    mutableArmaduras.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArmadura>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    private void obtenerItems(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvItem>> itemsPromesa = ApiRetrofit.getServiceApi().obtenerMisItems(mochilaId, personajeId, token);
        itemsPromesa.enqueue(new Callback<ArrayList<InvItem>>() {
            @Override
            public void onResponse(Call<ArrayList<InvItem>> call, Response<ArrayList<InvItem>> response) {
                if(response.isSuccessful()) {
                    mutableItems.postValue(response.body());
                } else {
                    mutableItems.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvItem>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    private void obtenerArtefactos(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArtefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerMisArtefactos(mochilaId, personajeId, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<InvArtefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArtefacto>> call, Response<ArrayList<InvArtefacto>> response) {
                if(response.isSuccessful()) {
                    mutableArtefactos.postValue(response.body());
                } else {
                    mutableArtefactos.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArtefacto>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}