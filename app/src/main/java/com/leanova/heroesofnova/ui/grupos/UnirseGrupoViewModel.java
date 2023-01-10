package com.leanova.heroesofnova.ui.grupos;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnirseGrupoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Grupo> mutableGrupo;
    private MutableLiveData<ArrayList<Personaje>> mutablePersonajes;
    private MutableLiveData<String> mutableAviso;

    public UnirseGrupoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Grupo> getMutableGrupo() {
        if(mutableGrupo == null) {
            mutableGrupo = new MutableLiveData<>();
        }
        return mutableGrupo;
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

    //FUNCIONES
    public void obtenerGrupo(Bundle bGrupo) {
        Grupo g = (Grupo) bGrupo.getSerializable("grupoUnirse");
        mutableGrupo.setValue(g);
    }

    public void obtenerPersonajes() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Personaje>> personajesPromesa = ApiRetrofit.getServiceApi().obtenerPersonajes(token);
        personajesPromesa.enqueue(new Callback<ArrayList<Personaje>>() {
            @Override
            public void onResponse(Call<ArrayList<Personaje>> call, Response<ArrayList<Personaje>> response) {
                if(response.isSuccessful()) {
                    mutablePersonajes.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Personaje>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void unirseGrupo(Grupo grupo, String pass, int idPersonaje, View view) {
        if(pass.equals(grupo.getPass())) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Participante> participantePromesa = ApiRetrofit.getServiceApi().crearParticipante(grupo.getIdGrupo(), idPersonaje, token);
            participantePromesa.enqueue(new Callback<Participante>() {
                @Override
                public void onResponse(Call<Participante> call, Response<Participante> response) {
                    if (response.isSuccessful()) {
                        mutableAviso.postValue("");
                        Toast.makeText(context, "Unido correctamente", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).popBackStack();
                    } else {
                        Toast.makeText(context, "Error en unirse", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Participante> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAviso.setValue("* Contraseña incorrecta");
        }
    }
}