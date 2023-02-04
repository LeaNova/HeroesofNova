package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabArsenalViewModel extends AndroidViewModel {
    private Context context;
    private Jugar jugar, aux;
    private MutableLiveData<Jugar> mutablePersonaje;
    private MutableLiveData<ArrayList<String>> mutableAdd;
    private MutableLiveData<ArrayList<String>> mutableColor;
    private MutableLiveData<ArrayList<Armadura>> mutableArmaduras;
    private MutableLiveData<ArrayList<Arma>> mutableArmas;

    public TabArsenalViewModel(@NonNull Application application) {
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

    public LiveData<ArrayList<String>> getMutableAdd() {
        if(mutableAdd == null) {
            mutableAdd = new MutableLiveData<>();
        }
        return mutableAdd;
    }

    public LiveData<ArrayList<String>> getMutableColor() {
        if(mutableColor == null) {
            mutableColor = new MutableLiveData<>();
        }
        return mutableColor;
    }

    public LiveData<ArrayList<Armadura>> getMutableArmaduras() {
        if(mutableArmaduras == null) {
            mutableArmaduras = new MutableLiveData<>();
        }
        return mutableArmaduras;
    }

    public LiveData<ArrayList<Arma>> getMutableArmas() {
        if(mutableArmas == null) {
            mutableArmas = new MutableLiveData<>();
        }
        return mutableArmas;
    }

    //FUNCIONES
    public void obtenerPersonaje() {
        jugar = PersonajeValues.getJugar();
        aux = new Jugar(jugar.getPersonaje(), jugar.getArma(), jugar.getArmadura());
        mutablePersonaje.setValue(jugar);
    }

    public void obtenerTodo() {
        String token = ApiRetrofit.obtenerToken(context);
        int id = jugar.getPersonaje().getIdPersonaje();

        obtenerArmaduras(token, id);
        obtenerArmas(token, id);
    }

    private void obtenerArmaduras(String token, int id) {
        Call<ArrayList<InvArmadura>> armadurasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmaduras(id, token);
        armadurasPromesa.enqueue(new Callback<ArrayList<InvArmadura>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArmadura>> call, Response<ArrayList<InvArmadura>> response) {
                if(response.isSuccessful()) {
                    parseArmadura(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArmadura>> call, Throwable t) {

            }
        });
    }

    private void obtenerArmas(String token, int id) {
        Call<ArrayList<InvArma>> armasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmas(id, token);
        armasPromesa.enqueue(new Callback<ArrayList<InvArma>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArma>> call, Response<ArrayList<InvArma>> response) {
                if(response.isSuccessful()) {
                    if(response.isSuccessful()) {
                        parseArma(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArma>> call, Throwable t) {

            }
        });
    }

    private void parseArmadura(ArrayList<InvArmadura> body) {
        ArrayList<Armadura> listaArmaduras = new ArrayList<>();
        for(InvArmadura x: body) {
            listaArmaduras.add(x.getArmadura());
        }
        mutableArmaduras.setValue(listaArmaduras);
    }

    private void parseArma(ArrayList<InvArma> body) {
        ArrayList<Arma> listaArmas = new ArrayList<>();
        for(InvArma x: body) {
            listaArmas.add(x.getArma());
        }
        mutableArmas.setValue(listaArmas);
    }

    public void setArmadura(Armadura armadura) {
        aux.setArmadura(armadura);
        ver();
    }

    public void setArma(Arma arma) {
        aux.setArma(arma);
        ver();
    }

    public void equipar(Armadura armadura, Arma arma) {
        jugar.setArmadura(armadura);
        jugar.setArma(arma);
        actualizar();
    }

    public void ver() {
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        ArrayList<String> listaStats = new ArrayList<>();
        ArrayList<String> listaColor = new ArrayList<>();

        listaNumeros.add(aux.getAtaque() - jugar.getAtaque());
        listaNumeros.add(aux.getAtkMagico() - jugar.getAtkMagico());
        listaNumeros.add(aux.getDefensa() - jugar.getDefensa());
        listaNumeros.add(aux.getDefMagico() - jugar.getDefMagico());
        listaNumeros.add(aux.getAgilidad() - jugar.getAgilidad());
        listaNumeros.add(aux.getEvasion() - jugar.getEvasion());
        listaNumeros.add(aux.getCritico() - jugar.getCritico());
        listaNumeros.add(aux.getPrecision() - jugar.getPrecision());

        for(int stat: listaNumeros) {
            listaStats.add(stat < 0 ? " " + stat : " +" + stat);
        }

        for(int stat: listaNumeros) {
            listaColor.add(stat < 0 ?"#E74C3C" : "#2ECC71");
        }

        mutableAdd.setValue(listaStats);
        mutableColor.setValue(listaColor);
    }

    public void actualizar() {
        aux = new Jugar(jugar.getPersonaje(), jugar.getArma(), jugar.getArmadura());
        PersonajeValues.setJugar(jugar);
        mutablePersonaje.setValue(jugar);
        ver();
    }
}