package com.leanova.heroesofnova.ui.razas;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RazaViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Raza> listaRazas;
    private MutableLiveData<ArrayList<Raza>> mutableRaza;
    private MutableLiveData<Integer> mutableAccess;

    public RazaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaRazas = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Raza>> getMutableRazas() {
        if(mutableRaza == null) {
            mutableRaza = new MutableLiveData<>();
        }
        return mutableRaza;
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

    public ArrayList<Raza> getListaRazas() {
        return listaRazas;
    }

    public void obtenerRazas() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Raza>> razasPromesa = ApiRetrofit.getServiceApi().obtenerRazas(token);
        razasPromesa.enqueue(new Callback<ArrayList<Raza>>() {
            @Override
            public void onResponse(Call<ArrayList<Raza>> call, Response<ArrayList<Raza>> response) {
                if(response.isSuccessful()) {
                    listaRazas = response.body();
                    mutableRaza.postValue(listaRazas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Raza>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}