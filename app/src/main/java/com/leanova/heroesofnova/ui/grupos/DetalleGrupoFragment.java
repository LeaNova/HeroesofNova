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

public class DetalleGrupoFragment extends Fragment {

    private DetalleGrupoViewModel mViewModel;

    public static DetalleGrupoFragment newInstance() {
        return new DetalleGrupoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_grupo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleGrupoViewModel.class);
        // TODO: Use the ViewModel
    }

}