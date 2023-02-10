package com.leanova.heroesofnova.ui.artefactos;

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
import android.widget.ListView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentArtefactoBinding;
import com.leanova.heroesofnova.modelos.Artefacto;

import java.util.ArrayList;

public class ArtefactoFragment extends Fragment {
    private FragmentArtefactoBinding binding;
    private ArtefactoViewModel artefactoVM;

    private ListView lvArtefacto;
    private Button btNuevo_Artefacto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArtefactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        artefactoVM = new ViewModelProvider(this).get(ArtefactoViewModel.class);

        inicializarVista(root);
        artefactoVM.getMutableArtefactos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArtefactoAdapter aa = new ArtefactoAdapter(getContext(), R.layout.item_artefacto, artefactos);
                lvArtefacto.setAdapter(aa);
            }
        });
        artefactoVM.obtenerArtefactos();
        artefactoVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_Artefacto.setVisibility(integer);
            }
        });
        artefactoVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvArtefacto = v.findViewById(R.id.lvArtefacto);
        this.lvArtefacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto aSeleccionado = artefactoVM.getListaArtefactos().get(i);

                Bundle bArtefacto = new Bundle();
                bArtefacto.putSerializable("artefacto", aSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleArtefactoFragment, bArtefacto);
            }
        });

        this.btNuevo_Artefacto = v.findViewById(R.id.btNuevo_Artefacto);
        this.btNuevo_Artefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearArtefactoFragment);
            }
        });
    }
}