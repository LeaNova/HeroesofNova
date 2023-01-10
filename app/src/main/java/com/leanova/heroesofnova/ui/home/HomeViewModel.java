package com.leanova.heroesofnova.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Usuario;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mutableUsuario;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMutableUsuario() {
        if(mutableUsuario == null) {
            mutableUsuario = new MutableLiveData<>();
        }
        return mutableUsuario;
    }

    public void getUsuario() {
         String token = ApiRetrofit.obtenerToken(context);

         Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtener(token);
         usuarioPromesa.enqueue(new Callback<Usuario>() {
             @Override
             public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                 Usuario u = response.body();
                 DefaultValues.setUsuario(u);
                 mutableUsuario.postValue(u);
             }

             @Override
             public void onFailure(Call<Usuario> call, Throwable t) {

             }
         });
    }
}