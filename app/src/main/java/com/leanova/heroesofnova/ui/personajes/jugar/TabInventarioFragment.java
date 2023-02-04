package com.leanova.heroesofnova.ui.personajes.jugar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabInventarioBinding;

public class TabInventarioFragment extends Fragment {
    private FragmentTabInventarioBinding binding;
    private TabInventarioViewModel tabInventarioVM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabInventarioVM = new ViewModelProvider(this).get(TabInventarioViewModel.class);

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {

    }

}