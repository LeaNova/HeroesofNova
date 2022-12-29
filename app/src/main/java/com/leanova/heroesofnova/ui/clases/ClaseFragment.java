package com.leanova.heroesofnova.ui.clases;

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
import com.leanova.heroesofnova.databinding.FragmentClasesBinding;
import com.leanova.heroesofnova.modelos.Clase;

import java.util.ArrayList;

public class ClaseFragment extends Fragment {
    private FragmentClasesBinding binding;
    private ClaseViewModel cvm;

    private ListView lvClase_C;
    private Button btNuevo_C;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClasesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cvm = new ViewModelProvider(this).get(ClaseViewModel.class);

        inicializarVista(root);
        cvm.getMutableClases().observe(getViewLifecycleOwner(), new Observer<ArrayList<Clase>>() {
            @Override
            public void onChanged(ArrayList<Clase> clases) {
                ClaseAdapter ca = new ClaseAdapter(getContext(), R.layout.clase_item, clases);
                lvClase_C.setAdapter(ca);
            }
        });

        cvm.obtenerClases();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvClase_C = v.findViewById(R.id.lvClase_C);
        this.lvClase_C.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Clase cSeleccionado = cvm.getListaClases().get(i);

                Bundle bClase = new Bundle();
                bClase.putSerializable("clase", cSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleClaseFragment, bClase);
            }
        });

        this.btNuevo_C = v.findViewById(R.id.btNuevo_C);
        this.btNuevo_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearClaseFragment);
            }
        });
    }
}