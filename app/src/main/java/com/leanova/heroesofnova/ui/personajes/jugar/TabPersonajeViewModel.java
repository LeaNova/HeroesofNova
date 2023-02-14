package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabPersonajeViewModel extends AndroidViewModel {
    private Context context;
    private Jugar jugar;
    private MutableLiveData<Jugar> mutablePersonaje;
    private MutableLiveData<Integer> mutableDado;
    private MutableLiveData<String> mutableResultado;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableTip;
    private MutableLiveData<String> mutableClean;

    public TabPersonajeViewModel(@NonNull Application application) {
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

    public LiveData<Integer> getMutableDado() {
        if(mutableDado == null) {
            mutableDado = new MutableLiveData<>();
        }
        return mutableDado;
    }

    public LiveData<String> getMutableResultado() {
        if(mutableResultado == null) {
            mutableResultado = new MutableLiveData<>();
        }
        return mutableResultado;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<String> getMutableTip() {
        if(mutableTip == null) {
            mutableTip = new MutableLiveData<>();
        }
        return mutableTip;
    }

    public LiveData<String> getMutableClean() {
        if (mutableClean == null) {
            mutableClean = new MutableLiveData<>();
        }
        return mutableClean;
    }

    //FUNCIONES
    public void obtenerPersonaje() {
        jugar = PersonajeValues.getJugar();
        mutablePersonaje.setValue(jugar);
    }

    public void recuperar(int total, String opcion) {
        jugar.recuperar(opcion, total);
        actualizar();
    }

    public void recibirDanio(int danio, String opcion) {
        jugar.recibirDanio(opcion, danio);
        actualizar();
    }

    public void hacerAtaque(String danio, String opcion) {
        try {
            danio = danio.replace(",", "");
            String[] accion = danio.split(" ");

            int nivel = Integer.parseInt(accion[0]);
            int dado = Integer.parseInt(accion[1]);
            boolean especial = accion[2].equals("si");

            mutableResultado.setValue("Daño total: " + jugar.atacar(opcion, nivel, dado, especial));
            if(opcion.equals("Hechizo") || accion[2].equals("si")) actualizar();
        } catch (Exception ex) {
            mutableAviso.setValue("El formato para atacar debe estar separado con espacios o comas.\nEjemplo: 1, 5, no");
            Log.d("error", ex.getMessage());
        }
    }

    public void hacerTiro(int dadoArma) {
        int resultado = (int) (Math.random()*dadoArma + 1);
        mutableDado.setValue(resultado);
    }

    public void getTip() {
        mutableTip.setValue(
                "Para declarar una ataque se debe declarar \"nivel, total, especial?\":\n" +
                "Nivel: 0 para ataque con el arma. 1-9 para declarar nivel del hechizo.\n" +
                "Total: el resultado del dado.\n" +
                "Especial?: \"si\" o \"no\" para declarar si es espacial o no.");
    }

    public void getExp(int exp, int necesaria) {
        jugar.getPersonaje().subirExp(exp);
        if(exp >= necesaria) jugar.setGame();
        actualizar();
    }

    public void actualizar() {
        PersonajeValues.setJugar(jugar);
        mutablePersonaje.setValue(jugar);
        mutableClean.setValue("");
    }

    public void finalizar(View view) {
        String token = ApiRetrofit.obtenerToken(context);
        Personaje personaje = jugar.getPersonaje();

        Call<Personaje> finalizarPromesa = ApiRetrofit.getServiceApi().finalizar(personaje.getIdPersonaje(), personaje.getNivel(), personaje.getExperiencia(), personaje.getVida(), personaje.getEnergia(), personaje.getAtaque(), personaje.getAtkMagico(), personaje.getDefensa(), personaje.getDefMagico(), personaje.getAgilidad(), personaje.getEvasion(), personaje.getCritico(), personaje.getPrecision(), personaje.getMochilaId(), token);
        finalizarPromesa.enqueue(new Callback<Personaje>() {
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Personaje actualizado", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                }
            }

            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}