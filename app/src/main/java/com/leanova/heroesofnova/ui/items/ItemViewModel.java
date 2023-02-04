package com.leanova.heroesofnova.ui.items;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Item> listaItems;
    private MutableLiveData<ArrayList<Item>> mutableItems;
    private MutableLiveData<Integer> mutableAccess;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaItems = new ArrayList<>();
    }

    //MUTABLES
    public LiveData<ArrayList<Item>> getMutableItems() {
        if(mutableItems == null) {
            mutableItems = new MutableLiveData<>();
        }
        return mutableItems;
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

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public void obtenerItems() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Item>> itemsPromesa = ApiRetrofit.getServiceApi().obtenerItems(token);
        itemsPromesa.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(response.isSuccessful()) {
                    listaItems = response.body();
                    mutableItems.postValue(listaItems);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}