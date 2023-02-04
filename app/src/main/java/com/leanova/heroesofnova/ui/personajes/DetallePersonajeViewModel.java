package com.leanova.heroesofnova.ui.personajes;

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

import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePersonajeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Personaje> mutablePersonaje;
    private ArrayList<InvArma> listaArmas;
    private ArrayList<InvArmadura> listaArmaduras;
    private ArrayList<InvItem> listaItems;
    private MutableLiveData<ArrayList<InvArma>> mutableArmas;
    private MutableLiveData<ArrayList<InvArmadura>> mutableArmaduras;
    private MutableLiveData<ArrayList<InvItem>> mutableItems;

    public DetallePersonajeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaArmas = new ArrayList<>();
        this.listaArmaduras = new ArrayList<>();
        this.listaItems = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<Personaje> getMutablePersonaje() {
        if(mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public LiveData<ArrayList<InvArma>> getMutableArmas() {
        if(mutableArmas == null) {
            mutableArmas = new MutableLiveData<>();
        }
        return mutableArmas;
    }

    public LiveData<ArrayList<InvArmadura>> getMutableArmaduras() {
        if(mutableArmaduras == null) {
            mutableArmaduras = new MutableLiveData<>();
        }
        return mutableArmaduras;
    }

    public LiveData<ArrayList<InvItem>> getMutableItems() {
        if(mutableItems == null) {
            mutableItems = new MutableLiveData<>();
        }
        return mutableItems;
    }

    //FUNCIONES
    public void obtenerPersonaje(Bundle bPersonaje) {
        Personaje p = (Personaje) bPersonaje.getSerializable("personaje");
        mutablePersonaje.setValue(p);
    }

    public ArrayList<InvArma> getListaArmas() {
        return listaArmas;
    }

    public ArrayList<InvArmadura> getListaArmaduras() {
        return listaArmaduras;
    }

    public ArrayList<InvItem> getListaItems() {
        return listaItems;
    }

    public void obtenerTodo() {
        Personaje p = mutablePersonaje.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        obtenerArmas(p.getIdPersonaje(), token);
        obtenerArmaduras(p.getIdPersonaje(), token);
        obtenerItems(p.getIdPersonaje(), token);
    }

    private void obtenerArmas(int id, String token) {
        Call<ArrayList<InvArma>> armasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmas(id, token);
        armasPromesa.enqueue(new Callback<ArrayList<InvArma>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArma>> call, Response<ArrayList<InvArma>> response) {
                if(response.isSuccessful()) {
                    listaArmas = response.body();
                }
                mutableArmas.postValue(listaArmas);
            }

            @Override
            public void onFailure(Call<ArrayList<InvArma>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    private void obtenerArmaduras(int id, String token) {
        Call<ArrayList<InvArmadura>> armadurasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmaduras(id, token);
        armadurasPromesa.enqueue(new Callback<ArrayList<InvArmadura>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArmadura>> call, Response<ArrayList<InvArmadura>> response) {
                if(response.isSuccessful()) {
                    listaArmaduras = response.body();
                }
                mutableArmaduras.postValue(listaArmaduras);
            }

            @Override
            public void onFailure(Call<ArrayList<InvArmadura>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    private void obtenerItems(int id, String token) {
        Call<ArrayList<InvItem>> itemsPromesa = ApiRetrofit.getServiceApi().obtenerMisItems(id, token);
        itemsPromesa.enqueue(new Callback<ArrayList<InvItem>>() {
            @Override
            public void onResponse(Call<ArrayList<InvItem>> call, Response<ArrayList<InvItem>> response) {
                if(response.isSuccessful()) {
                    listaItems = response.body();
                }
                mutableItems.postValue(listaItems);
            }

            @Override
            public void onFailure(Call<ArrayList<InvItem>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }

    public void borrarPersonaje(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarPersonaje(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Personaje borrado", Toast.LENGTH_SHORT).show();
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