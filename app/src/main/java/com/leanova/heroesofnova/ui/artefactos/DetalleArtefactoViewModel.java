package com.leanova.heroesofnova.ui.artefactos;

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

import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleArtefactoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Artefacto> mutableArtefacto;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleArtefactoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLE
    public LiveData<Artefacto> getMutableArtefacto() {
        if(mutableArtefacto == null) {
            mutableArtefacto = new MutableLiveData<>();
        }
        return mutableArtefacto;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void getArtefacto(Bundle bArtefacto) {
        Artefacto a = (Artefacto) bArtefacto.getSerializable("artefacto");
        mutableArtefacto.setValue(a);
        obtenerArtefacto(a.getIdArtefacto());
    }

    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    private void obtenerArtefacto(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Artefacto> artefactoPromesa = ApiRetrofit.getServiceApi().obtenerArtefacto(id, token);
        artefactoPromesa.enqueue(new Callback<Artefacto>() {
            @Override
            public void onResponse(Call<Artefacto> call, Response<Artefacto> response) {
                if(response.isSuccessful()) {
                    mutableArtefacto.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Artefacto> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarArtefacto(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarArtefacto(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Artefacto borrado", Toast.LENGTH_SHORT).show();
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

        Call<Artefacto> artefactoPromesa = ApiRetrofit.getServiceApi().bajaArtefacto(id, token);
        artefactoPromesa.enqueue(new Callback<Artefacto>() {
            @Override
            public void onResponse(Call<Artefacto> call, Response<Artefacto> response) {
                if(response.isSuccessful()) {
                    Artefacto a = response.body();
                    Toast.makeText(context, a.isDisponible() ? "Activado" : "Desactivado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Artefacto> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}