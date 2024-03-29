package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvArtefacto;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabArsenalViewModel extends AndroidViewModel {
    private Context context;
    private Jugar jugar, aux;

    private ArrayList<Armadura> listaArmaduras = new ArrayList<>();
    private ArrayList<Arma> listaArmas = new ArrayList<>();
    private ArrayList<Artefacto> listaCoronas = new ArrayList<>();
    private ArrayList<Artefacto> listaIzquierdas = new ArrayList<>();
    private ArrayList<Artefacto> listaDerechas = new ArrayList<>();
    private ArrayList<Artefacto> listaAdornos = new ArrayList<>();

    private MutableLiveData<Jugar> mutablePersonaje;
    private MutableLiveData<ArrayList<String>> mutableCambio;
    private MutableLiveData<ArrayList<String>> mutableAdd;
    private MutableLiveData<ArrayList<String>> mutableColor;
    private MutableLiveData<ArrayList<Armadura>> mutableArmaduras;
    private MutableLiveData<ArrayList<Arma>> mutableArmas;
    private MutableLiveData<ArrayList<Artefacto>> mutableCoronas;
    private MutableLiveData<ArrayList<Artefacto>> mutableIzquierdas;
    private MutableLiveData<ArrayList<Artefacto>> mutableDerechas;
    private MutableLiveData<ArrayList<Artefacto>> mutableAdornos;

    public TabArsenalViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.listaArmaduras.add(new Armadura().getArmadura());
        this.listaArmas.add(new Arma().getArma());
        this.listaCoronas.add(new Artefacto().getCorona());
        this.listaIzquierdas.add(new Artefacto().getIzquierda());
        this.listaDerechas.add(new Artefacto().getDerecha());
        this.listaAdornos.add(new Artefacto().getAdorno());
    }

    //MUTABLES
    public LiveData<Jugar> getMutablePersonaje() {
        if (mutablePersonaje == null) {
            mutablePersonaje = new MutableLiveData<>();
        }
        return mutablePersonaje;
    }

    public LiveData<ArrayList<String>> getMutableCambio() {
        if(mutableCambio == null) {
            mutableCambio = new MutableLiveData<>();
        }
        return mutableCambio;
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

    public LiveData<ArrayList<Artefacto>> getMutableCoronas() {
        if(mutableCoronas == null) {
            mutableCoronas = new MutableLiveData<>();
        }
        return mutableCoronas;
    }

    public LiveData<ArrayList<Artefacto>> getMutableIzquierdas() {
        if(mutableIzquierdas == null) {
            mutableIzquierdas = new MutableLiveData<>();
        }
        return mutableIzquierdas;
    }

    public LiveData<ArrayList<Artefacto>> getMutableDerechas() {
        if(mutableDerechas == null) {
            mutableDerechas = new MutableLiveData<>();
        }
        return mutableDerechas;
    }

    public LiveData<ArrayList<Artefacto>> getMutableAdornos() {
        if(mutableAdornos == null) {
            mutableAdornos = new MutableLiveData<>();
        }
        return mutableAdornos;
    }

    //FUNCIONES
    public void obtenerPersonaje() {
        jugar = PersonajeValues.getJugar();
        aux = new Jugar(jugar.getPersonaje(), jugar.getArma(), jugar.getArmadura(), jugar.getCorona(), jugar.getIzquierda(), jugar.getDerecha(), jugar.getAdorno());

        mutablePersonaje.setValue(jugar);
    }

    private void modificarVista(Personaje personaje) {
        ArrayList<String> cambio = new ArrayList<>();

        cambio.add(personaje.getAuxAtaque() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxAtkMagico() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxDefensa() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxDefMagico() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxAgilidad() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxEvasion() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxCritico() > 0 ? "#cbcf00" : "#9b9b9b");
        cambio.add(personaje.getAuxPrecision() > 0 ? "#cbcf00" : "#9b9b9b");

        mutableCambio.setValue(cambio);
    }

    public void obtenerTodo() {
        String token = ApiRetrofit.obtenerToken(context);
        Personaje personaje = jugar.getPersonaje();

        obtenerArmaduras(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
        obtenerArmas(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
        obtenerCoronas(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
        obtenerIzquierdas(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
        obtenerDerechas(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
        obtenerAdornos(personaje.getMochilaId(), personaje.getIdPersonaje(),token);
    }

    private void obtenerArmaduras(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArmadura>> armadurasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmaduras(mochilaId, personajeId, token);
        armadurasPromesa.enqueue(new Callback<ArrayList<InvArmadura>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArmadura>> call, Response<ArrayList<InvArmadura>> response) {
                if(response.isSuccessful()) {
                    parseArmadura(response.body());
                } else {
                    mutableArmaduras.postValue(listaArmaduras);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArmadura>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void obtenerArmas(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArma>> armasPromesa = ApiRetrofit.getServiceApi().obtenerMisArmas(mochilaId, personajeId, token);
        armasPromesa.enqueue(new Callback<ArrayList<InvArma>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArma>> call, Response<ArrayList<InvArma>> response) {
                if(response.isSuccessful()) {
                    parseArma(response.body());
                } else {
                    mutableArmas.postValue(listaArmas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArma>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void obtenerCoronas(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArtefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerCoronas(mochilaId, personajeId, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<InvArtefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArtefacto>> call, Response<ArrayList<InvArtefacto>> response) {
                if(response.isSuccessful()) {
                    parseCorona(response.body());
                } else {
                    mutableCoronas.postValue(listaCoronas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArtefacto>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void obtenerIzquierdas(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArtefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerIzquierda(mochilaId, personajeId, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<InvArtefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArtefacto>> call, Response<ArrayList<InvArtefacto>> response) {
                if(response.isSuccessful()) {
                    parseIzquierda(response.body());
                } else {
                    mutableIzquierdas.postValue(listaIzquierdas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArtefacto>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void obtenerDerechas(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArtefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerDerechas(mochilaId, personajeId, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<InvArtefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArtefacto>> call, Response<ArrayList<InvArtefacto>> response) {
                if(response.isSuccessful()) {
                    parseDerecha(response.body());
                } else {
                    mutableDerechas.postValue(listaDerechas);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArtefacto>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void obtenerAdornos(int mochilaId, int personajeId, String token) {
        Call<ArrayList<InvArtefacto>> artefactosPromesa = ApiRetrofit.getServiceApi().obtenerAdornos(mochilaId, personajeId, token);
        artefactosPromesa.enqueue(new Callback<ArrayList<InvArtefacto>>() {
            @Override
            public void onResponse(Call<ArrayList<InvArtefacto>> call, Response<ArrayList<InvArtefacto>> response) {
                if(response.isSuccessful()) {
                    parseAdorno(response.body());
                } else {
                    mutableAdornos.postValue(listaAdornos);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InvArtefacto>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void parseArmadura(ArrayList<InvArmadura> body) {
        for(InvArmadura x: body) {
            listaArmaduras.add(x.getArmadura());
        }
        mutableArmaduras.setValue(listaArmaduras);
    }

    private void parseArma(ArrayList<InvArma> body) {
        for(InvArma x: body) {
            listaArmas.add(x.getArma());
        }
        mutableArmas.setValue(listaArmas);
    }

    private void parseCorona(ArrayList<InvArtefacto> body) {
        for(InvArtefacto x: body) {
            listaCoronas.add(x.getArtefacto());
        }
        mutableCoronas.setValue(listaCoronas);
    }

    private void parseIzquierda(ArrayList<InvArtefacto> body) {
        for(InvArtefacto x: body) {
            listaIzquierdas.add(x.getArtefacto());
        }
        mutableIzquierdas.setValue(listaIzquierdas);
    }

    private void parseDerecha(ArrayList<InvArtefacto> body) {
        for(InvArtefacto x: body) {
            listaDerechas.add(x.getArtefacto());
        }
        mutableDerechas.setValue(listaDerechas);
    }

    private void parseAdorno(ArrayList<InvArtefacto> body) {
        for(InvArtefacto x: body) {
            listaAdornos.add(x.getArtefacto());
        }
        mutableAdornos.setValue(listaAdornos);
    }

    public void setArmadura(Armadura armadura) {
        aux.setArmadura(armadura);
        ver();
    }

    public void setArma(Arma arma) {
        aux.setArma(arma);
        ver();
    }

    public void setCorona(Artefacto artefacto) {
        aux.setCorona(artefacto);
        ver();
    }

    public void setIzquierda(Artefacto artefacto) {
        aux.setIzquierda(artefacto);
        ver();
    }

    public void setDerecha(Artefacto artefacto) {
        aux.setDerecha(artefacto);
        ver();
    }

    public void setAdorno(Artefacto artefacto) {
        aux.setAdorno(artefacto);
        ver();
    }

    public void equipar(Armadura armadura, Arma arma, Artefacto corona, Artefacto izquierda, Artefacto derecha, Artefacto adorno) {
        jugar.setArmadura(armadura);
        jugar.setArma(arma);
        jugar.setCorona(corona);
        jugar.setIzquierda(izquierda);
        jugar.setDerecha(derecha);
        jugar.setAdorno(adorno);
        jugar.setGame();
        actualizar();
    }

    public void terminarEfecto() {
        jugar.getPersonaje().quitarExtras();
        Toast.makeText(context, "Efecto terminado", Toast.LENGTH_SHORT).show();
        actualizar();
    }

    public void ver() {
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        ArrayList<String> listaStats = new ArrayList<>();
        ArrayList<String> listaColor = new ArrayList<>();

        listaNumeros.add(aux.getVida() - jugar.getVida());
        listaNumeros.add(aux.getEnergia() - jugar.getEnergia());
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
            listaColor.add(stat < 0 ? "#E74C3C" : "#2ECC71");
        }

        mutableAdd.setValue(listaStats);
        mutableColor.setValue(listaColor);
    }

    public void actualizar() {
        aux = new Jugar(jugar.getPersonaje(), jugar.getArma(), jugar.getArmadura(), jugar.getCorona(), jugar.getIzquierda(), jugar.getDerecha(), jugar.getAdorno());
        PersonajeValues.setJugar(jugar);
        mutablePersonaje.setValue(jugar);
        modificarVista(jugar.getPersonaje());
        ver();
    }
}