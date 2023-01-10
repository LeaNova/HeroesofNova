package com.leanova.heroesofnova.ui.grupos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearGrupoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;
    private MutableLiveData<Grupo> mutableGrupo;

    public CrearGrupoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<String> getMutableClean() {
        if(mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    public LiveData<Grupo> getMutableGrupo() {
        if(mutableGrupo == null) {
            mutableGrupo = new MutableLiveData<>();
        }
        return mutableGrupo;
    }

    //FUNCIONES
    public void obtenerGrupo(Bundle bGrupo) {
        if(bGrupo != null) {
            Grupo g = (Grupo) bGrupo.getSerializable("grupoEdit");
            mutableGrupo.setValue(g);
        }
    }

    public void crearGrupo(String nombre, String descripcion,String pass, Boolean dispopnible) {
        String aviso = "";
        Boolean ok = true;

        if(nombre.isEmpty()) {
            aviso += "* Debe ingresar un nombre para el grupo.\n";
            ok = false;
        }

        if(descripcion.isEmpty()) {
            aviso += "* Es necesario tener una descripción.\n";
            ok = false;
        }

        if(pass.isEmpty()) {
            aviso += "* Ingrese una contraseña para el grupo.\n";
            ok = false;
        }

        if(ok) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Grupo> grupoPromesa = ApiRetrofit.getServiceApi().crearGrupo(nombre, descripcion, pass, dispopnible, token);
            grupoPromesa.enqueue(new Callback<Grupo>() {
                @Override
                public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Grupo creado", Toast.LENGTH_SHORT).show();
                        mutableClean.postValue("");
                        mutableAviso.postValue("");
                    } else {
                        Toast.makeText(context, "Error en crear grupo", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Grupo> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAviso.setValue(aviso);
        }
    }

    public void editarGrupo(int id, String nombre, String descripcion,String pass, Boolean dispopnible) {
        String aviso = "";
        Boolean ok = true;

        if (nombre.isEmpty()) {
            aviso += "* Debe ingresar un nombre para el grupo.\n";
            ok = false;
        }

        if (descripcion.isEmpty()) {
            aviso += "* Es necesario tener una descripción.\n";
            ok = false;
        }

        if (pass.isEmpty()) {
            aviso += "* Ingrese una contraseña para el grupo.\n";
            ok = false;
        }

        if (ok) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Grupo> grupoPromesa = ApiRetrofit.getServiceApi().editarGrupo(id, nombre, descripcion, pass, dispopnible, token);
            grupoPromesa.enqueue(new Callback<Grupo>() {
                @Override
                public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Grupo actualizado", Toast.LENGTH_SHORT).show();
                        mutableAviso.postValue("");
                    } else {
                        Toast.makeText(context, "Error en actualizar grupo", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Grupo> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAviso.setValue(aviso);
        }
    }
}