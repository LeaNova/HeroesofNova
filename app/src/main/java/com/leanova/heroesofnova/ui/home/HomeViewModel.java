package com.leanova.heroesofnova.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leanova.heroesofnova.modelos.Usuario;
import com.leanova.heroesofnova.request.DefaultValues;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mutableUsuario;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMutableUsuario() {
        if(mutableUsuario == null) {
            mutableUsuario = new MutableLiveData<>();
        }
        return mutableUsuario;
    }

    public void getUsuario() {
        Usuario u = DefaultValues.getUsuario();

        mutableUsuario.setValue(u);
    }
}