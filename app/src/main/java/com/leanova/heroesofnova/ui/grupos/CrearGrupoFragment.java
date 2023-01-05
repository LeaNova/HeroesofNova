package com.leanova.heroesofnova.ui.grupos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanova.heroesofnova.R;

public class CrearGrupoFragment extends Fragment {

    private CrearGrupoViewModel mViewModel;

    public static CrearGrupoFragment newInstance() {
        return new CrearGrupoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_grupo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CrearGrupoViewModel.class);
        // TODO: Use the ViewModel
    }
}