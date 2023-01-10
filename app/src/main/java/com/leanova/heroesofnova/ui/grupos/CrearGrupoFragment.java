package com.leanova.heroesofnova.ui.grupos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentCrearGrupoBinding;
import com.leanova.heroesofnova.modelos.Grupo;

public class CrearGrupoFragment extends Fragment {
    private FragmentCrearGrupoBinding binding;
    private CrearGrupoViewModel cgvm;

    private TextView textNuevoActualizar_CG, tvAviso_CG;
    private EditText etNombre_CG, etDescripcion_CG, etContrasenia_CG;
    private CheckBox cbDisponible_CG;
    private Button btCrearActualizar_CG;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearGrupoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cgvm = new ViewModelProvider(this).get(CrearGrupoViewModel.class);

        cgvm.getMutableGrupo().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo grupo) {
                textNuevoActualizar_CG.setText("Actualizar grupo");
                etNombre_CG.setText(grupo.getNombre());
                etDescripcion_CG.setText(grupo.getDescripcion());
                etContrasenia_CG.setText(grupo.getPass());
                cbDisponible_CG.setChecked(grupo.getDisponible());

                btCrearActualizar_CG.setText("Actualizar");
                btCrearActualizar_CG.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombre = etNombre_CG.getText().toString();
                        String descripcion = etDescripcion_CG.getText().toString();
                        String pass = etContrasenia_CG.getText().toString();

                        Boolean disponible = cbDisponible_CG.isChecked();

                        cgvm.editarGrupo(grupo.getIdGrupo(), nombre, descripcion, pass, disponible);
                    }
                });
            }
        });
        cgvm.obtenerGrupo(getArguments());

        inicializarVista(root);
        cgvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_CG.setText(s);
            }
        });
        cgvm.getMutableClean().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CG.setText(s);
                etDescripcion_CG.setText(s);
                etContrasenia_CG.setText(s);
            }
        });
        return root;
    }

    private void inicializarVista(View v) {
        this.textNuevoActualizar_CG = v.findViewById(R.id.textNuevoActualizar_CG);
        this.etNombre_CG = v.findViewById(R.id.etNombre_CG);
        this.etDescripcion_CG = v.findViewById(R.id.etDescripcion_CG);
        this.etContrasenia_CG = v.findViewById(R.id.etContrasenia_CG);
        this.cbDisponible_CG = v.findViewById(R.id.cbDisponible_CG);
        this.tvAviso_CG = v.findViewById(R.id.tvAviso_CG);

        this.btCrearActualizar_CG = v.findViewById(R.id.btCrearActualizar_CG);
        this.btCrearActualizar_CG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre_CG.getText().toString();
                String descripcion = etDescripcion_CG.getText().toString();
                String pass = etContrasenia_CG.getText().toString();

                Boolean disponible = cbDisponible_CG.isChecked();

                cgvm.crearGrupo(nombre, descripcion, pass, disponible);
            }
        });
    }
}