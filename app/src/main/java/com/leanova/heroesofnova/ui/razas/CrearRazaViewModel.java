package com.leanova.heroesofnova.ui.razas;

import android.app.Application;
import android.content.Context;
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
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearRazaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
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

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente");
    }

    public void setClean() {
        mutableClean.setValue("");
    }

    public void crearRaza(String nombre, String descripcion, int vida, int atk, int atm, int def, int dfm, int dex, int eva, int crt, int acc) {
        boolean ok = true;
        String aviso = "";

        if(vida%2!=0 || (vida < 6 || vida > 12)) {
            aviso += "* La vida debe estar entre 6 y 12\n";
            ok = false;
        }

        if(atk + atm + def + dfm + dex + eva + crt + acc > 72) {
            aviso += "* La base total no puede superar los 72 puntos en total\n";
            ok = false;
        }

        if(atk > 20 || atm > 20 || def > 20 || dfm > 20 || dex > 20 || eva > 20 || crt > 20 || acc > 20) {
            aviso += "* La base de las estadisticas no puede superar los 20 puntos\n";
            ok = false;
        }

        if(ok) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Raza> razaPromesa = ApiRetrofit.getServiceApi().crearRaza(nombre, descripcion, vida, atk, atm, def, dfm, dex, eva, crt, acc, token);
            razaPromesa.enqueue(new Callback<Raza>() {
                @Override
                public void onResponse(Call<Raza> call, Response<Raza> response) {
                    if(response.isSuccessful()) {
                        mutableAviso.postValue("");
                        setClean();
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
        } else {
            mutableAviso.setValue(aviso);
        }
    }
}