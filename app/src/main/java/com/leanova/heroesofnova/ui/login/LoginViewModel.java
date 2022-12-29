package com.leanova.heroesofnova.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.MainActivity;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;
import com.leanova.heroesofnova.ui.signin.SigninActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mutableAviso;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getAviso() {
        if(mutableAviso == null) {
            mutableAviso = new MutableLiveData<>();
        }
        return mutableAviso;
    }

    public void iniciarSesion(String mail, String pass) {
        if(mail.contains(" ")) {
            mail.replace(" ", "");
        }

        Call<String> tokenPromesa = ApiRetrofit.getServiceApi().login(mail, pass);
        tokenPromesa.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    String token = "Bearer " + response.body();

                    SharedPreferences sharedP = context.getSharedPreferences("informacion", 0);
                    SharedPreferences.Editor editor = sharedP.edit();
                    editor.putString("token", token);
                    editor.commit();

                    mutableAviso.setValue("");

                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                    Toast.makeText(context, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                } else {
                    mutableAviso.setValue("Usuario o contraseña incorrecto");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    public void registrarse() {
        Intent i = new Intent(context, SigninActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
