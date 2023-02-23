package com.leanova.heroesofnova.ui.home;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Usuario;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mutableUsuario;
    private MutableLiveData<String> mutableAvisoUsuario;
    private MutableLiveData<String> mutableAvisoUsuarioColor;
    private MutableLiveData<Boolean> mutableEnable;
    private MutableLiveData<String> mutableAviso;

    public EditarPerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    //MUTABLES
    public LiveData<Usuario> getMutableUsuario() {
        if(mutableUsuario == null) {
            mutableUsuario = new MutableLiveData<>();
        }
        return mutableUsuario;
    }

    public LiveData<String> getMutableAvisoUsuario() {
        if(mutableAvisoUsuario == null) {
            mutableAvisoUsuario = new MutableLiveData<>();
        }
        return mutableAvisoUsuario;
    }

    public LiveData<String> getMutableAvisoUsuarioColor() {
        if(mutableAvisoUsuarioColor == null) {
            mutableAvisoUsuarioColor = new MutableLiveData<>();
        }
        return mutableAvisoUsuarioColor;
    }

    public LiveData<Boolean> getMutableEnable() {
        if(mutableEnable == null) {
            mutableEnable = new MutableLiveData<>();
        }
        return mutableEnable;
    }

    public LiveData<String> getMuableAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    //FUNCIONES
    public void obtenerUsuario(Bundle bUsuario) {
        Usuario u = (Usuario) bUsuario.getSerializable("usuarioEdit");
        mutableUsuario.setValue(u);
    }

    public void actualizarPerfil(String nombre, String apellido, String usuario) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().actualizarPerfil(nombre, apellido, usuario, token);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    Usuario u = response.body();
                    DefaultValues.setUsuario(u);
                    Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void cambiarContrasenia(String contraAct, String contraNueva) {
        String token = ApiRetrofit.obtenerToken(context);

        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().cambiarContrasenia(contraAct, contraNueva, token);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error en actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void checUsuario(String usuario) {
        if(usuario.length() > 5) {
            String token = ApiRetrofit.obtenerToken(context);

            Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtenerCambio(usuario, token);
            usuarioPromesa.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        mutableAvisoUsuario.postValue("*Nombre de usuario no disponible");
                        mutableAvisoUsuarioColor.postValue("#FF0000");
                        mutableEnable.postValue(false);
                    } else {
                        mutableAvisoUsuario.postValue("*Nombre disponible");
                        mutableAvisoUsuarioColor.postValue("#00FF00");
                        mutableEnable.postValue(true);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAvisoUsuario.setValue("*Usuario debe tener 5 o más caracteres");
            mutableAvisoUsuarioColor.setValue("#FF0000");
            mutableEnable.setValue(false);
        }
    }
}