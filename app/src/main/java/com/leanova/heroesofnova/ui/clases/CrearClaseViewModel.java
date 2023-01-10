package com.leanova.heroesofnova.ui.clases;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearClaseViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Clase> mutableClase;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearClaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Clase> getMutableClase() {
        if(mutableClase == null) {
            mutableClase = new MutableLiveData<>();
        }
        return mutableClase;
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
    public void getClase(Bundle bClase) {
        if(bClase != null) {
            Clase c = (Clase) bClase.getSerializable("claseEdit");
            mutableClase.setValue(c);
        }
    }

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente");
    }

    public void setClean() {
        mutableClean.setValue("");
    }

    public void tomarAccion(String accion, String nombre, float vida, float energia, float atk, float atm, float def, float dfm, float dex, float eva, float crt, float acc, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if(vida < 1.3f || vida > 1.5f ) {
            aviso += "* El modificador de vida debe estar en el rango de 1.3 a 1.5\n";
            ok = false;
        }

        if(energia < 1.05f || energia > 1.5f ) {
            aviso += "* El modificador de energía debe estar en el rango de 1.05 a 1.5\n";
            ok = false;
        }

        if((atk < 1.05f || atk > 1.3f) || (atm < 1.05f || atm > 1.3f) || (def < 1.05f || def > 1.3f) || (dfm < 1.05f || dfm > 1.3f) || (dex < 1.05f || dex > 1.3f) || (eva < 1.05f || eva > 1.3f) || (crt < 1.05f || crt > 1.3f) || (acc < 1.05f || acc > 1.3f)) {
            aviso += "* Lod modificadores deben estar en el rango de 1.05 a 1.3\n";
            ok = false;
        }

        if(ok && accion.equals("Crear")) {
            crearClase(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarClase(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
        }

        mutableAviso.setValue(aviso);
    }

    private void crearClase(String nombre, float vida, float energia, float atk, float atm, float def, float dfm, float dex, float eva, float crt, float acc, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Clase> clasePromesa = ApiRetrofit.getServiceApi().crearClase(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion, token);
        clasePromesa.enqueue(new Callback<Clase>() {
            @Override
            public void onResponse(Call<Clase> call, Response<Clase> response) {
                if(response.isSuccessful()) {
                    mutableAviso.postValue("");
                    setClean();
                    Toast.makeText(context, "Clase creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear clase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Clase> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarClase(String nombre, float vida, float energia, float atk, float atm, float def, float dfm, float dex, float eva, float crt, float acc, String descripcion) {
        Clase c = mutableClase.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Clase> clasePromesa = ApiRetrofit.getServiceApi().editarClase(c.getIdClase(), nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion, token);
        clasePromesa.enqueue(new Callback<Clase>() {
            @Override
            public void onResponse(Call<Clase> call, Response<Clase> response) {
                if(response.isSuccessful()) {
                    mutableAviso.postValue("");
                    Toast.makeText(context, "Clase actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Clase> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}