package com.leanova.heroesofnova.ui.clases;

import android.app.Application;
import android.content.Context;
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
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearClaseViewModel(@NonNull Application application) {
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

    public void crearClase(String nombre, String descripcion, float vida, float atk, float atm, float def, float dfm, float dex, float eva, float crt, float acc) {
        boolean ok = true;
        String aviso = "";

        if(vida < 1.3f || vida > 1.5f ) {
            aviso += "* El modificador de vida debe estar en el rango de 1.3 a 1.5\n";
            ok = false;
        }

        if((atk < 1.05f || atk > 1.3f) || (atm < 1.05f || atm > 1.3f) || (def < 1.05f || def > 1.3f) || (dfm < 1.05f || dfm > 1.3f) || (dex < 1.05f || dex > 1.3f) || (eva < 1.05f || eva > 1.3f) || (crt < 1.05f || crt > 1.3f) || (acc < 1.05f || acc > 1.3f)) {
            aviso += "* Lod modificadores deben estar en el rango de 1.05 a 1.3\n";
            ok = false;
        }

        if(ok) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Clase> clasePromesa = ApiRetrofit.getServiceApi().crearClase(nombre, descripcion, vida, atk, atm, def, dfm, dex, eva, crt, acc, token);
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
        } else {
            mutableAviso.setValue(aviso);
        }
    }
}