package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabPersonajeViewModel extends AndroidViewModel {
    private Context context;
    private Jugar jugar;
    private MutableLiveData<Jugar> mutablePersonaje;
    private MutableLiveData<String> mutableClean;

    public TabPersonajeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Jugar> getMutablePersonaje() {
        if (mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public LiveData<String> getMutableClean() {
        if (mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    //FUNCIONES
    public void obtenerPersonaje() {
        jugar = PersonajeValues.getJugar();
        mutablePersonaje.setValue(jugar);
    }

    public void recuperar(int total, String opcion) {
        if (opcion.equals("Vida")) {
            jugar.getPersonaje().recuperarVida(total);
        } else {
            jugar.getPersonaje().recuperarEnergia(total);
        }
        actualizar();
    }

    public void recibirDanio(int danio, String opcion) {
        if (opcion.equals("Fisico")) {
            jugar.getPersonaje().recibirDanioFisico(danio);
        } else {
            jugar.getPersonaje().recibirDanioMagico(danio);
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
        jugar.getPersonaje().subirExp(exp);
        actualizar();
    }

    public void actualizar() {
        PersonajeValues.setJugar(jugar);
        mutablePersonaje.setValue(jugar);
        mutableClean.setValue("");
    }

    public void finalizar(View view) {
        String token = ApiRetrofit.obtenerToken(context);
        Personaje personaje = jugar.getPersonaje();

        Call<Personaje> finalizarPromesa = ApiRetrofit.getServiceApi().finalizar(personaje.getIdPersonaje(), personaje.getNivel(), personaje.getExperiencia(), personaje.getVida(), personaje.getEnergia(), personaje.getAtaque(), personaje.getAtkMagico(), personaje.getDefensa(), personaje.getDefMagico(), personaje.getAgilidad(), personaje.getEvasion(), personaje.getCritico(), personaje.getPrecision(), personaje.getMochilaId(), token);
        finalizarPromesa.enqueue(new Callback<Personaje>() {
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Personaje actualizado", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                }
            }

            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}