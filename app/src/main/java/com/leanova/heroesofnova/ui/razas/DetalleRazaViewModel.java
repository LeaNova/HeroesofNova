package com.leanova.heroesofnova.ui.razas;

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

import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleRazaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Raza> mutableRaza;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleRazaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Raza> getMutableRaza() {
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
    public void getRaza(Bundle bRaza) {
        Raza r = (Raza) bRaza.getSerializable("raza");
        mutableRaza.setValue(r);
        obtenerRaza(r.getIdRaza());
    }

    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    private void obtenerRaza(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Raza> razaPromesa = ApiRetrofit.getServiceApi().obtenerRaza(id, token);
        razaPromesa.enqueue(new Callback<Raza>() {
            @Override
            public void onResponse(Call<Raza> call, Response<Raza> response) {
                if(response.isSuccessful()) {
                    mutableRaza.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Raza> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarRaza(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarRaza(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Se borró la raza", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                } else {
                    Toast.makeText(context, "No se pudo borrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void cambiarDisponibilidad(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Raza> razaPromesa = ApiRetrofit.getServiceApi().bajaRaza(id, token);
        razaPromesa.enqueue(new Callback<Raza>() {
            @Override
            public void onResponse(Call<Raza> call, Response<Raza> response) {
                if(response.isSuccessful()) {
                    Raza r = response.body();
                    Toast.makeText(context, r.isDisponible() ? "Activado" : "Desactivado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Raza> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}