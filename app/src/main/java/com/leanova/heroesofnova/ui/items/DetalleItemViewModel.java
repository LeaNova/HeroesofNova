package com.leanova.heroesofnova.ui.items;

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

import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleItemViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Item> mutableItem;
    private MutableLiveData<Integer> mutableAccess;

    public DetalleItemViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLE
    public LiveData<Item> getMutableItem() {
        if(mutableItem == null) {
            mutableItem = new MutableLiveData<>();
        }
        return mutableItem;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    //FUNCIONES
    public void getItem(Bundle bItem) {
        Item i = (Item) bItem.getSerializable("item");
        mutableItem.setValue(i);
        obtenerItem(i.getIdItem());
    }

    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Admin")) {
            mutableAccess.setValue(0);
        } else {
            mutableAccess.setValue(8);
        }
    }

    private void obtenerItem(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Item> itemPromesa = ApiRetrofit.getServiceApi().obtenerItem(id, token);
        itemPromesa.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    mutableItem.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarItem(int id, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarItem(id, token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Se borró el item", Toast.LENGTH_SHORT).show();
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

        Call<Item> itemPromesa = ApiRetrofit.getServiceApi().bajaItem(id, token);
        itemPromesa.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    Item i = response.body();
                    Toast.makeText(context, i.isDisponible() ? "Activado" : "Desactivado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}