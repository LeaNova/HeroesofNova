package com.leanova.heroesofnova.ui.personajes;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Personaje;

public class DetallePersonajeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Personaje> mutablePersonaje;

    public DetallePersonajeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Personaje> getMutablePersonaje() {
        if(mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public void obtenerPersonaje(Bundle bPersonaje) {
        Personaje p = (Personaje) bPersonaje.getSerializable("personaje");
        mutablePersonaje.setValue(p);
    }
}