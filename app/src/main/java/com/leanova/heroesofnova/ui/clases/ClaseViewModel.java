package com.leanova.heroesofnova.ui.clases;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaseViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Clase> listaClases;
    private MutableLiveData<ArrayList<Clase>> mutableClases;

    public ClaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaClases = new ArrayList<>();
    }

    public LiveData<ArrayList<Clase>> getMutableClases() {
        if(mutableClases == null) {
            mutableClases = new MutableLiveData<>();
        }
        return mutableClases;
    }

    public ArrayList<Clase> getListaClases() {
        return listaClases;
    }

    public void obtenerClases() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Clase>> clasesPromesa = ApiRetrofit.getServiceApi().obtenerClases(token);
        clasesPromesa.enqueue(new Callback<ArrayList<Clase>>() {
            @Override
            public void onResponse(Call<ArrayList<Clase>> call, Response<ArrayList<Clase>> response) {
                if(response.isSuccessful()) {
                    listaClases = response.body();
                    mutableClases.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Clase>> call, Throwable t) {

            }
        });
    }
}