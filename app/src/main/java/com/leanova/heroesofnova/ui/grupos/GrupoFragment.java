package com.leanova.heroesofnova.ui.grupos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentGrupoBinding;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.ArrayList;

public class GrupoFragment extends Fragment {
    private FragmentGrupoBinding binding;
    private GrupoViewModel gvm;

    private RecyclerView rvGrupos_G;
    private TextView tvAviso_G;
    private Button btNuevoBuscar_G;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGrupoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gvm = new ViewModelProvider(this).get(GrupoViewModel.class);

        inicializarVista(root);
        gvm.getMutableAction().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btNuevoBuscar_G.setText(s);
            }
        });
        gvm.setAction();
        gvm.getMutableGrupos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Grupo>>() {
            @Override
            public void onChanged(ArrayList<Grupo> grupos) {
                GrupoAdapter ga = new GrupoAdapter(getContext(), getLayoutInflater(), grupos);
                rvGrupos_G.setAdapter(ga);
            }
        });
        gvm.getMutableMisGrupos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Participante>>() {
            @Override
            public void onChanged(ArrayList<Participante> participantes) {
                GrupoAdapter2 ga = new GrupoAdapter2(getContext(), getLayoutInflater(), participantes);
                rvGrupos_G.setAdapter(ga);
            }
        });
        gvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_G.setVisibility(View.VISIBLE);
                tvAviso_G.setText(s);
            }
        });
        gvm.obtenerGrupos();

        return root;
    }

    private void inicializarVista(View v) {
        this.rvGrupos_G = v.findViewById(R.id.rvGrupos_G);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        this.rvGrupos_G.setLayoutManager(gridLayoutManager);

        this.tvAviso_G = v.findViewById(R.id.tvAviso_G);
        this.tvAviso_G.setText("");
        this.tvAviso_G.setVisibility(View.GONE);

        this.btNuevoBuscar_G = v.findViewById(R.id.btNuevoBuscar_G);
        this.btNuevoBuscar_G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accion = btNuevoBuscar_G.getText().toString();
                gvm.navegar(accion, view);
            }
        });
    }
}