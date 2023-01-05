package com.leanova.heroesofnova.ui.grupos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrupoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Grupo>> mutableGrupos;
    private MutableLiveData<ArrayList<Participante>> mutableMisGrupos;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableAction;

    public GrupoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //Mutables
    public LiveData<ArrayList<Grupo>> getMutableGrupos() {
        if(mutableGrupos == null) {
            mutableGrupos = new MutableLiveData<>();
        }
        return mutableGrupos;
    }
    public LiveData<ArrayList<Participante>> getMutableMisGrupos() {
        if(mutableMisGrupos == null) {
            mutableMisGrupos = new MutableLiveData<>();
        }
        return mutableMisGrupos;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<String> getMutableAction() {
        if(mutableAction == null) {
            mutableAction = new MutableLiveData<>();
        }
        return mutableAction;
    }

    //Funciones
    public void setAction() {
        if(DefaultValues.getAccess().equals("Master")) {
            mutableAction.setValue("Nuevo");
        } else {
            mutableAction.setValue("Buscar");
        }
    }

    public void navegar(String accion, View view) {
        if(accion.equals("Master")) {
            Navigation.findNavController(view).navigate(R.id.crearGrupoFragment);
        } else {
            Navigation.findNavController(view).navigate(R.id.buscarGrupoFragment);
        }
    }

    public void obtenerGrupos() {
        String token = ApiRetrofit.obtenerToken(context);

        if(DefaultValues.getAccess().equals("Master")) {
            Call<ArrayList<Grupo>> gruposPromesa = ApiRetrofit.getServiceApi().obtenerGrupos(token);
            gruposPromesa.enqueue(new Callback<ArrayList<Grupo>>() {
                @Override
                public void onResponse(Call<ArrayList<Grupo>> call, Response<ArrayList<Grupo>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().size() > 0) {
                            mutableGrupos.postValue(response.body());
                            mutableAviso.postValue("");
                        } else {
                            mutableAviso.postValue("No tiene ningun grupo creado por el momento");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Grupo>> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            Call<ArrayList<Participante>> misGruposPromesa = ApiRetrofit.getServiceApi().obtenerMisGrupos(token);
            misGruposPromesa.enqueue(new Callback<ArrayList<Participante>>() {
                @Override
                public void onResponse(Call<ArrayList<Participante>> call, Response<ArrayList<Participante>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().size() > 0) {
                            mutableMisGrupos.postValue(response.body());
                            mutableAviso.postValue("");
                        } else {
                            mutableAviso.postValue("No tiene ningun grupo creado por el momento");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Participante>> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        }
    }
}