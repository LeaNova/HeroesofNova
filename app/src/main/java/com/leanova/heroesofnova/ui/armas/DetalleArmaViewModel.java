package com.leanova.heroesofnova.ui.armas;

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

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleArmaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Arma> mutableArma;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleArmaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLE
    public LiveData<Arma> getMutableArma() {
        if(mutableArma == null) {
            mutableArma = new MutableLiveData<>();
        }
        return mutableArma;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void getArma(Bundle bAmra) {
        Arma a = (Arma) bAmra.getSerializable("arma");
        mutableArma.setValue(a);
        obtenerArma(a.getIdArma());
    }

    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    private void obtenerArma(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Arma> armaPromesa = ApiRetrofit.getServiceApi().obtenerArma(id, token);
        armaPromesa.enqueue(new Callback<Arma>() {
            @Override
            public void onResponse(Call<Arma> call, Response<Arma> response) {
                if(response.isSuccessful()) {
                    mutableArma.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Arma> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarArma(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarArma(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Se borró el arma", Toast.LENGTH_SHORT).show();
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
}