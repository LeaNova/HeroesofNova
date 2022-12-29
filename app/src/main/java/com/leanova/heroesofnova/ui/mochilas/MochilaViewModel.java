package com.leanova.heroesofnova.ui.mochilas;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MochilaViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Mochila> listaMochilas;
    private MutableLiveData<ArrayList<Mochila>> mutableMochilas;

    public MochilaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaMochilas = new ArrayList<>();
    }

    public LiveData<ArrayList<Mochila>> getMutableMochilas() {
        if(mutableMochilas == null) {
            mutableMochilas = new MutableLiveData<>();
        }
        return mutableMochilas;
    }

    public ArrayList<Mochila> getListaMochilas() {
        return listaMochilas;
    }

    public void obtenerMochilas() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Mochila>> mochilasPromesa = ApiRetrofit.getServiceApi().obtenerMochilas(token);
        mochilasPromesa.enqueue(new Callback<ArrayList<Mochila>>() {
            @Override
            public void onResponse(Call<ArrayList<Mochila>> call, Response<ArrayList<Mochila>> response) {
                if(response.isSuccessful()) {
                    listaMochilas = response.body();
                    mutableMochilas.postValue(listaMochilas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mochila>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}