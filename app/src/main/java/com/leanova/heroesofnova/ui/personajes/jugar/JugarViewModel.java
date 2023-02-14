package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JugarViewModel extends AndroidViewModel {
    private Jugar jugar;
    private Personaje personaje;
    private Armadura armadura;
    private Arma arma;
    private Artefacto corona, izquierda, derecha, adorno;

    public JugarViewModel(@NonNull Application application) {
        super(application);
    }

    //FUNCIONES
    public void obtenerPersonaje(Bundle bPersonaje) {
        personaje = (Personaje) bPersonaje.getSerializable("jugar");

        jugar = PersonajeValues.getJugar();
        if(jugar != null) {
            armadura = jugar.getArmadura();
            arma = jugar.getArma();
            corona = jugar.getCorona();
            izquierda = jugar.getIzquierda();
            derecha = jugar.getDerecha();
            adorno = jugar.getAdorno();
        }

        jugar = new Jugar(personaje, arma, armadura, corona, izquierda, derecha, adorno);
        jugar.setGame();
        PersonajeValues.setJugar(jugar);
    }
}