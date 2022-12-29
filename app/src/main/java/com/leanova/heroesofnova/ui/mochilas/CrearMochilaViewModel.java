package com.leanova.heroesofnova.ui.mochilas;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearMochilaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearMochilaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
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

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente");
    }

    public void setClean() {
        mutableClean.setValue("");
    }

    public void crearMochila(String nombre, String descripcion, int pesoMax) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Mochila> mochilaPromesa = ApiRetrofit.getServiceApi().crearMochila(nombre, descripcion, pesoMax, token);
        mochilaPromesa.enqueue(new Callback<Mochila>() {
            @Override
            public void onResponse(Call<Mochila> call, Response<Mochila> response) {
                if(response.isSuccessful()) {
                    mutableAviso.postValue("");
                    setClean();
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
}