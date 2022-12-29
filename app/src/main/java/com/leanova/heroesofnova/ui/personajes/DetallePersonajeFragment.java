package com.leanova.heroesofnova.ui.personajes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetallePersonajeBinding;
import com.leanova.heroesofnova.modelos.Personaje;

public class DetallePersonajeFragment extends Fragment {
    private FragmentDetallePersonajeBinding binding;
    private DetallePersonajeViewModel dpvm;
    private TextView tvNivel_DP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallePersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dpvm = new ViewModelProvider(this).get(DetallePersonajeViewModel.class);
        dpvm.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje personaje) {
                personaje.setGame();
                //tvNivel_DP.setText(personaje.getExperiencia() + "/" + personaje.getNextExp());

            }
        });
        dpvm.obtenerPersonaje(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {

    }
}