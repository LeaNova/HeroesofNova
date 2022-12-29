package com.leanova.heroesofnova.ui.clases;

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

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.request.ApiRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleClaseViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Clase> mutableClase;

    public DetalleClaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Clase> getMutableClase() {
        if(mutableClase == null) {
            mutableClase = new MutableLiveData<>();
        }
        return mutableClase;
    }

    public void obtenerClase(Bundle bClase) {
        Clase c = (Clase) bClase.getSerializable("clase");
        mutableClase.setValue(c);
    }

    public void borrarClase(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarClase(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Se borró la clase", Toast.LENGTH_SHORT).show();
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