package com.leanova.heroesofnova.ui.armaduras;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArmaduraViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Armadura> listaArmaduras;
    private MutableLiveData<ArrayList<Armadura>> mutableArmaduras;
    private MutableLiveData<Integer> mutableAccess;

    public ArmaduraViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaArmaduras = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Armadura>> getMutableArmaduras() {
        if(mutableArmaduras == null) {
            mutableArmaduras = new MutableLiveData<>();
        }
        return mutableArmaduras;
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

    public ArrayList<Armadura> getListaArmaduras() {
        return listaArmaduras;
    }

    public void obtenerArmaduras() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Armadura>> armadurasPromesa = ApiRetrofit.getServiceApi().obtenerArmaduras(token);
        armadurasPromesa.enqueue(new Callback<ArrayList<Armadura>>() {
            @Override
            public void onResponse(Call<ArrayList<Armadura>> call, Response<ArrayList<Armadura>> response) {
                if(response.isSuccessful()) {
                    listaArmaduras = response.body();
                    mutableArmaduras.postValue(listaArmaduras);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Armadura>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}