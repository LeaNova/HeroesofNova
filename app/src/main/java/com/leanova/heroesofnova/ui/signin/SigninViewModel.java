package com.leanova.heroesofnova.ui.signin;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

    public SigninViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        if(listaRoles == null) {
            this.listaRoles = new ArrayList<>();
        }
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> nombreRoles = new ArrayList<>();
        for(Rol item: DefaultValues.getRoles()) {
            nombreRoles.add(item.getNombre());
        }
        return nombreRoles;
    }

    public void registrarUsuario(String nombre, String apellido, String mail, String pass1, String pass2, int rol) {
        String token = ApiRetrofit.obtenerToken(context);
        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().signin(nombre, apellido, mail, pass1, rol, token);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
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
    }
}
