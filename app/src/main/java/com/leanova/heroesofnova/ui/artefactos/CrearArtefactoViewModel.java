package com.leanova.heroesofnova.ui.artefactos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Seccion;
import com.leanova.heroesofnova.modelos.Tipo;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearArtefactoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Artefacto> mutableArtefacto;
    private MutableLiveData<ArrayList<Seccion>> mutableSecciones;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearArtefactoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Artefacto> getMutableArtefacto() {
        if(mutableArtefacto == null) {
            mutableArtefacto = new MutableLiveData<>();
        }
        return mutableArtefacto;
    }

    public LiveData<ArrayList<Seccion>> getMutableSecciones() {
        if(mutableSecciones == null) {
            mutableSecciones = new MutableLiveData<>();
        }
        return mutableSecciones;
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
    public void getArtefacto(Bundle bArtefacto) {
        if(bArtefacto != null) {
            Artefacto a = (Artefacto) bArtefacto.getSerializable("artefactoEdit");
            mutableArtefacto.setValue(a);
        }
    }

    public void obtenerSecciones() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Seccion>> seccionesPromesa = ApiRetrofit.getServiceApi().obtenerSecciones(token);
        seccionesPromesa.enqueue(new Callback<ArrayList<Seccion>>() {
            @Override
            public void onResponse(Call<ArrayList<Seccion>> call, Response<ArrayList<Seccion>> response) {
                if(response.isSuccessful()) {
                    mutableSecciones.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Seccion>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void getAviso() {
        mutableAviso.setValue("Revise que todos los campos esten llenos.");
    }

    public void tomarAccion(String accion, String nombre, Seccion seccion, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if(nombre.equals("")) {
            ok = false;
            aviso += "* El artefacto debe tener un nombre.\n";
        }

        if(bonoVida + bonoEnergia + bonoAtk + bonoAtm + bonoDef + bonoDfm + bonoDex + bonoEva + bonoCrt + bonoAcc < 0) {
            ok = false;
            aviso += "* Al menos un bono debe ser mas que 0.\n";
        }

        if(descripcion.equals("")) {
            ok = false;
            aviso = "El artefacto debe tener una descripción.";
        }

        if(ok && accion.equals("Crear")) {
            crearArtefacto(nombre, seccion, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarArtefacto(nombre, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearArtefacto(String nombre, Seccion seccion, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Artefacto> artefactoPromesa = ApiRetrofit.getServiceApi().crearArtefacto(nombre, seccion.getIdSeccion(), bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion, token);
        artefactoPromesa.enqueue(new Callback<Artefacto>() {
            @Override
            public void onResponse(Call<Artefacto> call, Response<Artefacto> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Artefacto creado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Artefacto> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarArtefacto(String nombre, int bonoVida, int bonoEnergia, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoDex, int bonoEva, int bonoCrt, int bonoAcc, int precio, float peso, String descripcion) {
        Artefacto a = mutableArtefacto.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Artefacto> artefactoPromesa = ApiRetrofit.getServiceApi().editarArtefacto(a.getIdArtefacto(), nombre, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion, token);
        artefactoPromesa.enqueue(new Callback<Artefacto>() {
            @Override
            public void onResponse(Call<Artefacto> call, Response<Artefacto> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Artefacto actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Artefacto> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}