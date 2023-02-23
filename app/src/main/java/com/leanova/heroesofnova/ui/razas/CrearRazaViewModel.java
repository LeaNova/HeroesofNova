package com.leanova.heroesofnova.ui.razas;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearRazaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Raza> mutableRaza;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearRazaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Raza> getMutableRaza() {
        if(mutableRaza == null) {
            mutableRaza = new MutableLiveData<>();
        }
        return mutableRaza;
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
    public void getRaza(Bundle bRaza) {
        if(bRaza != null) {
            Raza r = (Raza) bRaza.getSerializable("razaEdit");
            mutableRaza.setValue(r);
        }
    }

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente");
    }

    public void tomarAccion(String accion, String nombre, int vida, int energia, int atk, int atm, int def, int dfm, int dex, int eva, int crt, int acc, String descripcion) {
        boolean ok = true;
        String aviso = "";
        final int TOTAL = 68;

        if(vida%2!=0 || (vida < 6 || vida > 12)) {
            aviso += "* La vida debe ser un dado de 6, 8, 10 o 12 caras.\n";
            ok = false;
        }

        if(energia%2!=0 || (vida < 6 || vida > 12)) {
            aviso += "* La energia debe ser un dado de 6, 8, 10 o 12 caras.\n";
            ok = false;
        }

        if(atk + atm + def + dfm + dex + eva + crt + acc > TOTAL) {
            aviso += "* El total de las estadisticas no puede superar los " + TOTAL  + " puntos en total.\n";
            ok = false;
        }

        if(atk > 20 || atm > 20 || def > 20 || dfm > 20 || dex > 20 || eva > 20 || crt > 20 || acc > 20) {
            aviso += "* La base de las estadisticas no puede superar los 20 puntos.\n";
            ok = false;
        }

        if(ok && accion.equals("Crear")) {
            crearRaza(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarRaza(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearRaza(String nombre, int vida, int energia, int atk, int atm, int def, int dfm, int dex, int eva, int crt, int acc, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Raza> razaPromesa = ApiRetrofit.getServiceApi().crearRaza(nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion, true, token);
        razaPromesa.enqueue(new Callback<Raza>() {
            @Override
            public void onResponse(Call<Raza> call, Response<Raza> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Raza creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear raza", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Raza> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarRaza(String nombre, int vida, int energia, int atk, int atm, int def, int dfm, int dex, int eva, int crt, int acc, String descripcion) {
        Raza r = mutableRaza.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Raza> razaPromesa = ApiRetrofit.getServiceApi().editarRaza(r.getIdRaza(), nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion, true, token);
        razaPromesa.enqueue(new Callback<Raza>() {
            @Override
            public void onResponse(Call<Raza> call, Response<Raza> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Raza actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Raza> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}