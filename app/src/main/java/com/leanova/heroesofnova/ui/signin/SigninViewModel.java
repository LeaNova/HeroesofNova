package com.leanova.heroesofnova.ui.signin;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Usuario;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<Rol> listaRoles;
    private MutableLiveData<String> mutableAvisoMail;
    private MutableLiveData<String> mutableAvisoUsuario;
    private MutableLiveData<String> mutableAvisoPass;

    public SigninViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        if(listaRoles == null) {
            this.listaRoles = new ArrayList<>();
        }
    }

    public LiveData<String> getMutableAvisoMail() {
        if(mutableAvisoMail == null) {
            mutableAvisoMail = new MutableLiveData<>();
        }
        return mutableAvisoMail;
    }

    public LiveData<String> getMutableAvisoUsuario() {
        if(mutableAvisoUsuario == null) {
            mutableAvisoUsuario = new MutableLiveData<>();
        }
        return mutableAvisoUsuario;
    }

    public LiveData<String> getMutableAvisoPass() {
        if(mutableAvisoPass == null) {
            mutableAvisoPass = new MutableLiveData<>();
        }
        return mutableAvisoPass;
    }

    public void registrarUsuario(String nombre, String apellido, String mail, String usuario, String pass1, String pass2, int rol) {
        if(pass1.equals(pass2)) {
            String token = ApiRetrofit.obtenerToken(context);
            Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().signin(nombre, apellido, mail, usuario, pass1, rol, token);
            usuarioPromesa.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show();
                        System.exit(0);
                    } else {
                        Toast.makeText(context, "Error en registrar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAvisoPass.setValue("Las contraseñas no coinciden");
        }
    }

    public void checkMail(String mail) {
        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtenerMail(mail);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    mutableAvisoMail.postValue("*Mail ya registrado");
                } else {
                    mutableAvisoMail.postValue("");
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
            Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtenerUsuario(usuario);
            usuarioPromesa.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        mutableAvisoUsuario.postValue("*Nombre de usuario no disponible");
                    } else {
                        mutableAvisoUsuario.postValue("");
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("APIerror", t.getMessage());
                }
            });
        } else {
            mutableAvisoUsuario.setValue("*Usuario debe tener 5 o mas carácteres");
        }
    }
}
