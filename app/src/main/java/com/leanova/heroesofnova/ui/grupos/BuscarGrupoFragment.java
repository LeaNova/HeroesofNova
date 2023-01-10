package com.leanova.heroesofnova.ui.grupos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentBuscarGrupoBinding;
import com.leanova.heroesofnova.modelos.Grupo;

import java.util.ArrayList;

public class BuscarGrupoFragment extends Fragment {
    private FragmentBuscarGrupoBinding binding;
    private BuscarGrupoViewModel bgvm;

    private EditText etBuscar_BG;
    private Button btBuscar_BG;
    private ListView lvGrupos_BG;
    private TextView tvAviso_BG;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuscarGrupoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bgvm = new ViewModelProvider(this).get(BuscarGrupoViewModel.class);

        inicializarVista(root);
        bgvm.getMutableShow().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvAviso_BG.setVisibility(integer);
            }
        });
        bgvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_BG.setText(s);
            }
        });
        bgvm.getMutableGrupos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Grupo>>() {
            @Override
            public void onChanged(ArrayList<Grupo> grupos) {
                GrupoAdapter3 ga = new GrupoAdapter3(getContext(), R.layout.item_grupo_3, grupos);
                lvGrupos_BG.setAdapter(ga);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etBuscar_BG = v.findViewById(R.id.etBuscar_BG);

        this.btBuscar_BG = v.findViewById(R.id.btBuscar_BG);
        this.btBuscar_BG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etBuscar_BG.getText().toString();
                bgvm.obtenerGrupos(nombre);
            }
        });

        this.lvGrupos_BG = v.findViewById(R.id.lvGrupos_BG);
        this.lvGrupos_BG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Grupo gSeleccionado = bgvm.getListaGrupos().get(i);

                Bundle bGrupo = new Bundle();
                bGrupo.putSerializable("grupo", gSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleGrupoFragment, bGrupo);
            }
        });

        this.tvAviso_BG = v.findViewById(R.id.tvAviso_BG);
        this.tvAviso_BG.setText("Introduce el nombre del grupo, seguido preciona \"Buscar\".");
    }
}