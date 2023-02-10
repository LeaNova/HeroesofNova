package com.leanova.heroesofnova.ui.items;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Tipo;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearItemViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Item> mutableItem;
    private MutableLiveData<ArrayList<Tipo>> mutableTipos;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearItemViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Item> getMutableItem() {
        if(mutableItem == null) {
            mutableItem = new MutableLiveData<>();
        }
        return mutableItem;
    }

    public LiveData<ArrayList<Tipo>> getMutableTipos() {
        if(mutableTipos == null) {
            mutableTipos = new MutableLiveData<>();
        }
        return mutableTipos;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<String> getMutableClear() {
        if(mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    //FUNCIONES
    public void getItem(Bundle bItem) {
        if(bItem != null) {
            Item i = (Item) bItem.getSerializable("itemEdit");
            mutableItem.setValue(i);
        }
    }

    public void obtenerTipos() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Tipo>> tiposPromesa = ApiRetrofit.getServiceApi().obtenerTipos(token);
        tiposPromesa.enqueue(new Callback<ArrayList<Tipo>>() {
            @Override
            public void onResponse(Call<ArrayList<Tipo>> call, Response<ArrayList<Tipo>> response) {
                if(response.isSuccessful()) {
                    mutableTipos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Tipo>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void getAviso() {
        mutableAviso.setValue("Revise que todos los campos esten llenos.");
    }

    public void tomarAccion(String accion, String nombre, Tipo tipo, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if(descripcion.equals("")) {
            aviso = "El item debe tener una descripción.";
            ok = false;
        }

        if(ok && accion.equals("Crear")) {
            crearItem(nombre, tipo, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarItem(nombre, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearItem(String nombre, Tipo tipo, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Item> itemPromesa = ApiRetrofit.getServiceApi().crearItem(nombre, tipo.getIdTipo(), bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion, token);
        itemPromesa.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Item creado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarItem(String nombre, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        Item i = mutableItem.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Item> itemPromesa = ApiRetrofit.getServiceApi().editarItem(i.getIdItem(), nombre, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion, token);
        itemPromesa.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Item actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}