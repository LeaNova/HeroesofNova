package com.leanova.heroesofnova.ui.armas;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Categoria;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearArmaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Arma> mutableArma;
    private MutableLiveData<ArrayList<Categoria>> mutableCategorias;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<String> mutableClean;

    public CrearArmaViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Arma> getMutableArma() {
        if(mutableArma == null) {
            mutableArma = new MutableLiveData<>();
        }
        return mutableArma;
    }

    public LiveData<ArrayList<Categoria>> getMutableCategorias() {
        if(mutableCategorias == null) {
            mutableCategorias = new MutableLiveData<>();
        }
        return mutableCategorias;
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
    public void getArma(Bundle bArma) {
        if(bArma != null) {
            Arma a = (Arma) bArma.getSerializable("armaEdit");
            mutableArma.setValue(a);
        }
    }

    public void obtenerCategorias() {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Categoria>> categoriasPromesa = ApiRetrofit.getServiceApi().obtenerCategorias(token);
        categoriasPromesa.enqueue(new Callback<ArrayList<Categoria>>() {
            @Override
            public void onResponse(Call<ArrayList<Categoria>> call, Response<ArrayList<Categoria>> response) {
                if(response.isSuccessful()) {
                    mutableCategorias.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void getAviso() {
        mutableAviso.setValue("Revise que todos los campos esten llenos.");
    }

    public void tomarAccion(String accion, String nombre, Categoria categoria, int danio, int base, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoCrt, int bonoAcc, float modAtk, float modAtm, float modDef, float modDfm, int precio, float peso, String descripcion) {
        boolean ok = true;
        String aviso = "";

        if(nombre.equals("")) {
            ok = false;
            aviso += "* El arma debe tener un nombre.\n";
        }

        if(danio%2!=0 && (base < 4 || base > 12)) {
            ok = false;
            aviso += "* El daño base debe ser 4, 6, 8, 10 o 12.\n";
        }

        if(bonoAtk == 0 && bonoAtm == 0 && bonoDef == 0 && bonoDfm == 0 && bonoCrt == 0 && bonoAcc == 0) {
            ok = false;
            aviso += "* Al menos un bono debe ser mas que 0.\n";
        }

        if((modAtk < 1f || modAtk > 2f) || (modAtm < 1f || modAtm > 2f) || (modDef < 1f || modDef > 2f) || (modDfm < 1f || modDef > 2f)) {
            ok = false;
            aviso += "* Los modificadores deben estar entre 1 y 2.\n";
        }

        if(modAtk < 1f && modAtm < 1f && modDef < 1f && modDfm < 1f) {
            ok = false;
            aviso += "* El mínimo de los modificadores tiene que ser 1.\n";
        }

        if(descripcion.equals("")) {
            descripcion = "Arma sin descripción.\n";
        }

        if(ok && accion.equals("Crear")) {
            crearArma(nombre, categoria, danio, base, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoCrt, bonoAcc, modAtk, modAtm, modDef, modDfm, precio, peso, descripcion);
        }

        if(ok && accion.equals("Actualizar")) {
            editarArma(nombre, danio, base, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoCrt, bonoAcc, modAtk, modAtm, modDef, modDfm, precio, peso, descripcion);
        }

        if(!aviso.equals("")) mutableAviso.setValue(aviso);
    }

    private void crearArma(String nombre, Categoria categoria, int danio, int base, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoCrt, int bonoAcc, float modAtk, float modAtm, float modDef, float modDfm, int precio, float peso, String descripcion) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Arma> armaPromesa = ApiRetrofit.getServiceApi().crearArma(nombre, categoria.getIdCategoria(), danio, base, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoCrt, bonoAcc, modAtk, modAtm, modDef, modDfm, precio, peso, descripcion, token);
        armaPromesa.enqueue(new Callback<Arma>() {
            @Override
            public void onResponse(Call<Arma> call, Response<Arma> response) {
                if(response.isSuccessful()) {
                    mutableClean.postValue("");
                    Toast.makeText(context, "Arma creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en crear", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Arma> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void editarArma(String nombre, int danio, int base, int bonoAtk, int bonoAtm, int bonoDef, int bonoDfm, int bonoCrt, int bonoAcc, float modAtk, float modAtm, float modDef, float modDfm, int precio, float peso, String descripcion) {
        Arma a = mutableArma.getValue();
        String token = ApiRetrofit.obtenerToken(context);

        Call<Arma> armaPromesa = ApiRetrofit.getServiceApi().editarArma(a.getIdArma(), nombre, danio, base, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoCrt, bonoAcc, modAtk, modAtm, modDef, modDfm, precio, peso, descripcion, token);
        armaPromesa.enqueue(new Callback<Arma>() {
            @Override
            public void onResponse(Call<Arma> call, Response<Arma> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Arma actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Arma> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}