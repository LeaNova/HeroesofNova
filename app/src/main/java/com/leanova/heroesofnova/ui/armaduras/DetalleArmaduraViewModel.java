package com.leanova.heroesofnova.ui.armaduras;

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

import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleArmaduraViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Armadura> mutableArmadura;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleArmaduraViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLE
    public LiveData<Armadura> getMutableArmadura() {
        if(mutableArmadura == null) {
            mutableArmadura = new MutableLiveData<>();
        }
        return mutableArmadura;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void getArmadura(Bundle bArmadura) {
        Armadura a = (Armadura) bArmadura.getSerializable("armadura");
        mutableArmadura.setValue(a);
        obtenerArmadura(a.getIdArmadura());
    }

    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    private void obtenerArmadura(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Armadura> armaduraPromesa = ApiRetrofit.getServiceApi().obtenerArmadura(id, token);
        armaduraPromesa.enqueue(new Callback<Armadura>() {
            @Override
            public void onResponse(Call<Armadura> call, Response<Armadura> response) {
                if(response.isSuccessful()) {
                    mutableArmadura.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Armadura> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarArmadura(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarArmadura(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Se borró la armadura", Toast.LENGTH_SHORT).show();
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