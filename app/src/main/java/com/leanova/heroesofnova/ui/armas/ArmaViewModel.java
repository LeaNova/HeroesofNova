package com.leanova.heroesofnova.ui.armas;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArmaViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Arma> listaArmas;
    private MutableLiveData<ArrayList<Arma>> mutableArmas;
    private MutableLiveData<Integer> mutableAccess;

    public ArmaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaArmas = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Arma>> getMutableArmas() {
        if(mutableArmas == null) {
            mutableArmas = new MutableLiveData<>();
        }
        return mutableArmas;
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

    public ArrayList<Arma> getListaArmas() {
        return listaArmas;
    }

    public void obtenerArmas() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Arma>> armasPromesa = ApiRetrofit.getServiceApi().obtenerArmas(token);
        armasPromesa.enqueue(new Callback<ArrayList<Arma>>() {
            @Override
            public void onResponse(Call<ArrayList<Arma>> call, Response<ArrayList<Arma>> response) {
                if(response.isSuccessful()) {
                    listaArmas = response.body();
                    mutableArmas.postValue(listaArmas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Arma>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}