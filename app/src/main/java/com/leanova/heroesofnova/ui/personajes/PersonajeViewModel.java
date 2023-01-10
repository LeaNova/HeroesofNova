package com.leanova.heroesofnova.ui.personajes;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Personaje>> mutablePersonajes;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<Integer> mutableShow;

    public PersonajeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Personaje>> getMutablePersonajes() {
        if(mutablePersonajes == null) {
            mutablePersonajes = new MutableLiveData<>();
        }
        return mutablePersonajes;
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

    public void obtenerPersonajes() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Personaje>> personajesPromesa = ApiRetrofit.getServiceApi().obtenerPersonajes(token);
        personajesPromesa.enqueue(new Callback<ArrayList<Personaje>>() {
            @Override
            public void onResponse(Call<ArrayList<Personaje>> call, Response<ArrayList<Personaje>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Personaje> personajes = response.body();
                    if(personajes.size() > 0) {
                        mutablePersonajes.postValue(personajes);
                        mutableAviso.postValue("");
                        mutableShow.postValue(8);
                    } else {
                        mutableAviso.postValue("Aun no tiene personajes. Puede agregar uno nuevo apretando el botón \"Agregar\" abajo.");
                        mutableShow.postValue(0);
                    }
                } else {
                    mutableAviso.postValue("Ocurrio un error al obtener personajes.");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Personaje>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}