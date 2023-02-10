package com.leanova.heroesofnova.ui.personajes;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPersonajeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Raza> mutableRaza;
    private MutableLiveData<ArrayList<String>> mutableTiros;
    private MutableLiveData<String> mutableAviso;

    public CrearPersonajeViewModel(@NonNull Application application) {
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

    public LiveData<ArrayList<String>> getMutableTiros() {
        if(mutableTiros == null) {
            mutableTiros = new MutableLiveData<>();
        }
        return mutableTiros;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    //FUNCIONES
    public void setRaza(Raza raza) {
        mutableRaza.setValue(raza);
    }

    public void hacerTiros(Raza raza) {
        ArrayList<String> tiros = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            int tiro = (int) (Math.random() * raza.getVidaBase() + 1);
            tiros.add(tiro+"");
        }

        for(int i = 0; i < 3; i++) {
            int tiro = (int) (Math.random() * raza.getEnergiaBase() + 1);
            tiros.add(tiro+"");
        }

        for(int i = 0; i < 8; i++) {
            int tiro = (int) (Math.random() * 4 + 1);
            tiros.add(tiro+"");
        }

        mutableTiros.setValue(tiros);
    }

    public void getAviso() {
        mutableAviso.setValue("* Revise que todos los campos esten llenos correctamente");
    }

    public void crearPersonaje(String nombre, Raza raza, Genero genero, Clase clase, int vida1, int vida2, int vida3, int energia1, int energia2, int energia3, int atk, int atm, int def, int dfm, int dex, int eva, int crt, int acc, String descripcion) {
        boolean ok = true;
        String aviso = "";
        int atkT = atk + raza.getBaseAtk();
        int atmT = atm + raza.getBaseAtm();
        int defT = def + raza.getBaseDef();
        int dfmT = dfm + raza.getBaseDfm();
        int dexT = dex + raza.getBaseDex();
        int evaT = eva + raza.getBaseEva();
        int crtT = crt + raza.getBaseCrt();
        int accT = acc + raza.getBaseAcc();

        if(nombre.isEmpty()) {
            aviso += "* Debe agregar un nombre a su personaje.\n";
            ok = false;
        }

        if(vida1 > raza.getVidaBase() || vida2 > raza.getVidaBase() || vida3 > raza.getVidaBase()) {
            aviso += "* La vida no puede ser mayor a la base de la raza.\n";
            ok = false;
        }

        if(atkT > 20 || atmT > 20 || defT > 20 || dfmT > 20 || dexT > 20 || evaT > 20 || crtT > 20 || accT > 20) {
            aviso += "* El total de cada estadistica no puede ser mayor a 20.\n";
            ok = false;
        }

        if(descripcion.equals("")) descripcion = "No me dieron una descripción, estoy triste :(";

        if(ok) {
            int vida = (vida1 + vida2 + vida3) / 3;
            int energia = (energia1 + energia2 + energia3) / 3;
            String token = ApiRetrofit.obtenerToken(context);

            vida += raza.getVidaBase();
            energia += raza.getEnergiaBase();

            Call<Personaje> personajePromesa = ApiRetrofit.getServiceApi().crearPersonaje(nombre, raza.getIdRaza(), genero.getIdGenero(), clase.getIdClase(), 1, 0, vida, energia, atkT, atmT, defT, dfmT, dexT, evaT, crtT, accT, descripcion, 1, true, token);
            personajePromesa.enqueue(new Callback<Personaje>() {
                @Override
                public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Personaje creado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Personaje> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAviso.setValue(aviso);
        }
    }
}