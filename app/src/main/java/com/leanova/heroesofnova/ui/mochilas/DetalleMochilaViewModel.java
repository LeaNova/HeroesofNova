package com.leanova.heroesofnova.ui.mochilas;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleMochilaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Mochila> mutableMochila;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleMochilaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Mochila> getMutableMochila() {
        if(mutableMochila == null) {
            mutableMochila = new MutableLiveData<>();
        }
        return mutableMochila;
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

    public void getMochila(Bundle bMochila) {
        Mochila m = (Mochila) bMochila.getSerializable("mochila");
        obtenerMochila(m.getIdMochila());
    }

    private void obtenerMochila(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Mochila> mochilaPromesa = ApiRetrofit.getServiceApi().obtenerMochila(id, token);
        mochilaPromesa.enqueue(new Callback<Mochila>() {
            @Override
            public void onResponse(Call<Mochila> call, Response<Mochila> response) {
                if(response.isSuccessful()) {
                    mutableMochila.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Mochila> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarMochila(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarMochila(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Se borró la mochila", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                } else {
                    Toast.makeText(context, "No se pudo borrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}