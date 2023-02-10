package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabItemViewModel extends AndroidViewModel {
    private Context context;
    private Jugar jugar;
    private MutableLiveData<Jugar> mutablePersonaje;
    private MutableLiveData<ArrayList<InvItem>> mutableConsumibles;

    public TabItemViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Jugar> getMutablePersonaje() {
        if (mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public LiveData<ArrayList<InvItem>> getMutableConsumibles() {
        if(mutableConsumibles == null) {
            mutableConsumibles = new MutableLiveData<>();
        }
        return mutableConsumibles;
    }

    //FUNCIONES
    public void obtenerPersonaje() {
        jugar = PersonajeValues.getJugar();
        mutablePersonaje.setValue(jugar);
    }

    public void refresh() {
        obtenerConsumibles();
    }

    public void actualizar() {
        PersonajeValues.setJugar(jugar);
        mutablePersonaje.setValue(jugar);
    }

    public void obtenerConsumibles() {
        String token = ApiRetrofit.obtenerToken(context);
        Personaje personaje = jugar.getPersonaje();

        Call<ArrayList<InvItem>> itemsPromesa = ApiRetrofit.getServiceApi().obtenerConsumibles(personaje.getMochilaId(), personaje.getIdPersonaje(), token);
        itemsPromesa.enqueue(new Callback<ArrayList<InvItem>>() {
            @Override
            public void onResponse(Call<ArrayList<InvItem>> call, Response<ArrayList<InvItem>> response) {
                if(response.isSuccessful()) {
                    mutableConsumibles.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvItem>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}