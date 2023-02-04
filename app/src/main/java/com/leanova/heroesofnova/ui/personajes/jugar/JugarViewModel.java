package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.PersonajeValues;

public class JugarViewModel extends AndroidViewModel {
    private Personaje personaje;

    public JugarViewModel(@NonNull Application application) {
        super(application);
    }

    //FUNCIONES
    public void obtenerPersonaje(Bundle bPersonaje) {
        personaje = (Personaje) bPersonaje.getSerializable("jugar");
        Jugar jugar = new Jugar(personaje, null, null);

        //PersonajeValues.setPersonaje(personaje);
        PersonajeValues.setJugar(jugar);
    }
}