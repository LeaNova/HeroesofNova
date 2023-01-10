package com.leanova.heroesofnova.ui.personajes;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

public class JugarViewModel extends AndroidViewModel {
    private Context context;
    private Personaje personaje;
    private MutableLiveData<Personaje> mutablePersonaje;
    private MutableLiveData<String> mutableClean;

    public JugarViewModel(@NonNull Application application) {
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

    public LiveData<String> getMutableClean() {
        if(mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    //FUNCIONES
    public void obtenerPersonaje(Bundle bPersonaje) {
        Personaje p = (Personaje) bPersonaje.getSerializable("jugar");
        personaje = p;
        mutablePersonaje.setValue(personaje);
    }

    public void recuperar(int total, String opcion) {
        if(opcion.equals("Vida")) {
            personaje.recuperarVida(total);
        } else {
            personaje.recuperarEnergia(total);
        }
        actualizar();
    }

    public void recibirDanio(int danio, String opcion) {
        if(opcion.equals("Fisico")) {
            personaje.recibirDanioFisico(danio);
        } else {
            personaje.recibirDanioMagico(danio);
        }
        actualizar();
    }

    /*
    public void hacerDanio(int danio, String opcion) {
        if(opcion.equals("Fisico")) {
            personaje.recibirDanioFisico(danio);
        } else {
            personaje.recibirDanioMagico(danio);
        }
        actualizar();
    }*/

    public void getExp(int exp) {
        personaje.subirExp(exp);
        actualizar();
    }

    private void actualizar() {
        mutablePersonaje.setValue(personaje);
        mutableClean.setValue("");
    }

    public void finalizar() {
        String token = ApiRetrofit.obtenerToken(context);
    }
}