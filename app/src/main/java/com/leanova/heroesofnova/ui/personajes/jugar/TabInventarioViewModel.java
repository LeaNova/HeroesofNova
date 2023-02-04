package com.leanova.heroesofnova.ui.personajes.jugar;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.leanova.heroesofnova.modelos.Personaje;

public class TabInventarioViewModel extends AndroidViewModel {
    private Context context;
    private Personaje personaje;

    public TabInventarioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
}