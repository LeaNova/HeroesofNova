package com.leanova.heroesofnova.ui.armaduras;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearArmaduraViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Armadura> mutableArmadura;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearArmaduraViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Armadura> getMutableArmadura() {
        if(mutableArmadura == null) {
            mutableArmadura = new MutableLiveData<>();
        }
        return mutableArmadura;
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
    public void getArmadura(Bundle bArmadura) {
        if(bArmadura != null) {
            Armadura a = (Armadura) bArmadura.getSerializable("armaduraEdit");
            mutableArmadura.setValue(a);
        }
    }

    public void getAviso() {
        mutableAviso.setValue("Revise que todos los campos esten llenos.");
    }

    public void tomarAccion(String accion, String nombre, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, float modDef, float modDfm, int precio, float peso, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if((modDef < 1f || modDef > 2f) || (modDfm < 1f || modDfm > 2f)) {
            ok = false;
            aviso += "* Los modificadores deben estar entre 1 y 2.";
        }

        if(ok && accion.equals("Crear")) {
            crearArmadura(nombre, bonoDef, bonoDfm, bonoDex, bonoEva, modDef, modDfm, precio, peso, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarArmadura(nombre, bonoDef, bonoDfm, bonoDex, bonoEva, modDef, modDfm, precio, peso, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearArmadura(String nombre, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, float modDef, float modDfm, int precio, float peso, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Armadura> armaduraPromesa = ApiRetrofit.getServiceApi().crearArmadura(nombre, bonoDef, bonoDfm, bonoDex, bonoEva, modDef, modDfm, precio, peso, descripcion, token);
        armaduraPromesa.enqueue(new Callback<Armadura>() {
            @Override
            public void onResponse(Call<Armadura> call, Response<Armadura> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Armadura creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Armadura> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarArmadura(String nombre, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, float modDef, float modDfm, int precio, float peso, String descripcion) {
        Armadura a = mutableArmadura.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Armadura> armaduraPromesa = ApiRetrofit.getServiceApi().editarArmadura(a.getIdArmadura(), nombre, bonoDef, bonoDfm, bonoDex, bonoEva, modDef, modDfm, precio, peso, descripcion, token);
        armaduraPromesa.enqueue(new Callback<Armadura>() {
            @Override
            public void onResponse(Call<Armadura> call, Response<Armadura> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Armadura actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Armadura> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}