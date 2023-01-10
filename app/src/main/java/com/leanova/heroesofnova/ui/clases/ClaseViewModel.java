package com.leanova.heroesofnova.ui.clases;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaseViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Clase> listaClases;
    private MutableLiveData<ArrayList<Clase>> mutableClases;
    private MutableLiveData<Integer> mutableAccess;

    public ClaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaClases = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Clase>> getMutableClases() {
        if(mutableClases == null) {
            mutableClases = new MutableLiveData<>();
        }
        return mutableClases;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
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
                    mutableClases.postValue(listaClases);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Clase>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}