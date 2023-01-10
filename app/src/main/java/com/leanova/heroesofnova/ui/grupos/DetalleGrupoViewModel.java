package com.leanova.heroesofnova.ui.grupos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleGrupoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Grupo> mutableGrupo;
    private MutableLiveData<String> mutableViewData;
    private MutableLiveData<String> mutableView;
    private MutableLiveData<Integer> mutableAccess;
    private MutableLiveData<String> mutableAviso;
    private MutableLiveData<ArrayList<Participante>> mutableLista;

    public DetalleGrupoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Grupo> getMutableGrupo() {
        if(mutableGrupo == null) {
            mutableGrupo = new MutableLiveData<>();
        }
        return mutableGrupo;
    }

    public LiveData<String> getMutableViewData() {
        if(mutableViewData == null) {
            mutableViewData = new MutableLiveData<>();
        }
        return mutableViewData;
    }

    public LiveData<String> getMutableView() {
        if(mutableView == null) {
            mutableView = new MutableLiveData<>();
        }
        return mutableView;
    }

    public LiveData<Integer> getMutableAccess() {
        if(mutableAccess == null) {
            mutableAccess = new MutableLiveData<>();
        }
        return mutableAccess;
    }

    public LiveData<String> getMutableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public LiveData<ArrayList<Participante>> getMutableLista() {
        if(mutableLista == null) {
            mutableLista = new MutableLiveData<>();
        }
        return mutableLista;
    }

    //FUNCIONES
    public void setAccess() {
        String access = DefaultValues.getAccess();

        if(access.equals("Master")) {
            mutableView.setValue("Pass: ");
            mutableAccess.setValue(0);
        } else {
            mutableView.setValue("Master: ");
            mutableAccess.setValue(8);
        }
    }

    public void getGrupo(Bundle bGrupo) {
        Grupo g = (Grupo) bGrupo.getSerializable("grupo");

        obtenerGrupo(g.getIdGrupo());
        obtenerParticipantes(g.getIdGrupo());
    }

    private void obtenerGrupo(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Grupo> grupoPromesa = ApiRetrofit.getServiceApi().obtenerId(id, token);
        grupoPromesa.enqueue(new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                if(response.isSuccessful()) {
                    Grupo g = response.body();
                    mutableGrupo.postValue(g);
                    setView(g);
                }
            }

            @Override
            public void onFailure(Call<Grupo> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void setView(Grupo g) {
        if(DefaultValues.getAccess().equals("Master")) {
            mutableViewData.setValue(g.getPass());
        } else {
            mutableViewData.setValue(g.getMaster().getUsuario());
        }
    }

    private void obtenerParticipantes(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ArrayList<Participante>> participantesPromesa = ApiRetrofit.getServiceApi().obtenerParticipantes(id, token);
        participantesPromesa.enqueue(new Callback<ArrayList<Participante>>() {
            @Override
            public void onResponse(Call<ArrayList<Participante>> call, Response<ArrayList<Participante>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Participante> lista = response.body();
                    mutableLista.postValue(lista);
                    mutableAviso.postValue("");
                } else {
                    ArrayList<Participante> lista = new ArrayList<>();
                    mutableLista.postValue(lista);
                    mutableAviso.postValue("Sin jugadores");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Participante>> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void cambiarDisponibilidad(int id) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Grupo> grupoPromesa = ApiRetrofit.getServiceApi().bajaGrupo(id, token);
        grupoPromesa.enqueue(new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                if(response.isSuccessful()) {
                    Grupo g = response.body();
                    Toast.makeText(context, g.getDisponible() ? "Activado" : "Desactivado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Grupo> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void borrarGrupo(Grupo grupo, String contrasenia, View view) {
        if(contrasenia.equals(grupo.getPass())) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().borrarGrupo(grupo.getIdGrupo(), token);
            okPromesa.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Grupo borrado", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).popBackStack();
                    } else {
                        Toast.makeText(context, "No se pudo borrar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    public void salirGrupo(Grupo g, View view) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<ResponseBody> okPromesa = ApiRetrofit.getServiceApi().salirGrupo(g.getIdGrupo(), token);
        okPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Grupo abandonado", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                } else {
                    Toast.makeText(context, "No se pudo salir", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}