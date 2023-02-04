package com.leanova.heroesofnova.ui.mochilas;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearMochilaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Mochila> mutableMochila;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearMochilaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Mochila> getMutableMochila() {
        if(mutableMochila == null) {
            mutableMochila = new MutableLiveData<>();
        }
        return mutableMochila;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<String> getMutableClear() {
        if(mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    //FUNCIONES
    public void getMochila(Bundle bMochila) {
        if(bMochila != null) {
            Mochila m = (Mochila) bMochila.getSerializable("mochilaEdit");
            mutableMochila.setValue(m);
        }
    }

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente.");
    }

    public void tomarAccion(String accion, String nombre, int pesoMax, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if(nombre.isEmpty()) {
            aviso += "* Ingrese un nombre a la mochila\n";
            ok = false;
        }

        if(descripcion.isEmpty()) {
            aviso += "* Debe ingresar una descripción.\n";
            ok = false;
        }

        if(ok && accion.equals("Crear")) {
            crearMochila(nombre, pesoMax, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarMochila(nombre, pesoMax, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearMochila(String nombre, int pesoMax, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Mochila> mochilaPromesa = ApiRetrofit.getServiceApi().crearMochila(nombre, pesoMax, descripcion, token);
        mochilaPromesa.enqueue(new Callback<Mochila>() {
            @Override
            public void onResponse(Call<Mochila> call, Response<Mochila> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Mochila creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear mochila", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mochila> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarMochila(String nombre, int pesoMax, String descripcion) {
        Mochila m = mutableMochila.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Mochila> mochilaPromesa = ApiRetrofit.getServiceApi().editarMochila(m.getIdMochila(), nombre, pesoMax, descripcion, token);
        mochilaPromesa.enqueue(new Callback<Mochila>() {
            @Override
            public void onResponse(Call<Mochila> call, Response<Mochila> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Mochila actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mochila> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}