package com.leanova.heroesofnova.ui.personajes.detalle;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentAgregarInventarioBinding;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Item;

import java.util.ArrayList;
import java.util.Collections;

public class AgregarInventarioFragment extends Fragment {
    private FragmentAgregarInventarioBinding binding;
    private AgregarInventarioViewModel agregarInventarioVM;

    private EditText etBuscar_AInv;
    private Button btBuscar_AInv;
    private RadioGroup rgSeleccion_AInv;
    private RadioButton rbOpcion;
    private ListView lvResultados_AInv;
    private TextView tvAviso_AInv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAgregarInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        agregarInventarioVM = new ViewModelProvider(this).get(AgregarInventarioViewModel.class);

        inicializarVista(root);
        agregarInventarioVM.getMutableShow().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvAviso_AInv.setVisibility(integer);
            }
        });
        agregarInventarioVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_AInv.setText(s);
            }
        });
        agregarInventarioVM.getMutableInventario().observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                InventarioAdapter ia = new InventarioAdapter(getContext(), R.layout.item_inventario, objects);
                lvResultados_AInv.setAdapter(ia);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etBuscar_AInv = v.findViewById(R.id.etBuscar_AInv);

        this.btBuscar_AInv = v.findViewById(R.id.btBuscar_AInv);
        this.btBuscar_AInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rbOpcionID = rgSeleccion_AInv.getCheckedRadioButtonId();
                rbOpcion = v.findViewById(rbOpcionID);
                String opcion = rbOpcion.getText().toString();

                String nombre = etBuscar_AInv.getText().toString();

                agregarInventarioVM.buscarObjeto(opcion, nombre);
            }
        });

        this.rgSeleccion_AInv = v.findViewById(R.id.rgSeleccion_AInv);
        this.lvResultados_AInv = v.findViewById(R.id.lvResultados_AInv);
        this.tvAviso_AInv = v.findViewById(R.id.tvAviso_AInv);
        this.tvAviso_AInv.setText("Introduce el nombre del objeto y su categoría, seguido preciona \"Buscar\".");
    }
}