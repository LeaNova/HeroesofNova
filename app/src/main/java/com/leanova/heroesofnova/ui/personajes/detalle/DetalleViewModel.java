package com.leanova.heroesofnova.ui.personajes.detalle;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.PersonajeValues;

public class DetalleViewModel extends AndroidViewModel {

    public DetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public void obtenerPersonaje(Bundle bPersonaje) {
        Personaje personaje = (Personaje) bPersonaje.getSerializable("personaje");
        PersonajeValues.setPersonaje(personaje);
    }
}