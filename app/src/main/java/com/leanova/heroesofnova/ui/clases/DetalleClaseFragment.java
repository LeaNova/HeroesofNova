package com.leanova.heroesofnova.ui.clases;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetalleClaseBinding;
import com.leanova.heroesofnova.modelos.Clase;

public class DetalleClaseFragment extends Fragment {
    private FragmentDetalleClaseBinding binding;
    private DetalleClaseViewModel dcvm;

    private int id;
    private TextView tvNombre_DC, tvModVida_DC, tvDetalle_DC, tvModAtk_DC, tvModAtm_DC, tvModDef_DC, tvModDfm_DC, tvModDex_DC, tvModEva_DC, tvModCrt_DC, tvModAcc_DC;
    private Button btEditar_DC, btBorrar_DC;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleClaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dcvm = new ViewModelProvider(this).get(DetalleClaseViewModel.class);
        dcvm.getMutableClase().observe(getViewLifecycleOwner(), new Observer<Clase>() {
            @Override
            public void onChanged(Clase clase) {
                id = clase.getIdClase();
                tvNombre_DC.setText(clase.getNombre());
                tvDetalle_DC.setText(clase.getDescripcion());
                tvModVida_DC.setText(" x" + clase.getModVida());
                tvModAtk_DC.setText(" x" + clase.getModAtk());
                tvModAtm_DC.setText(" x" + clase.getModAtm());
                tvModDef_DC.setText(" x" + clase.getModDef());
                tvModDfm_DC.setText(" x" + clase.getModDfm());
                tvModDex_DC.setText(" x" + clase.getModDex());
                tvModEva_DC.setText(" x" + clase.getModEva());
                tvModCrt_DC.setText(" x" + clase.getModCrt());
                tvModAcc_DC.setText(" x" + clase.getModAcc());
            }
        });
        dcvm.obtenerClase(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DC = v.findViewById(R.id.tvNombre_DC);
        this.tvDetalle_DC = v.findViewById(R.id.tvDetalle_DC);
        this.tvModVida_DC = v.findViewById(R.id.tvModVida_DC);
        this.tvModAtk_DC = v.findViewById(R.id.tvModAtk_DC);
        this.tvModAtm_DC = v.findViewById(R.id.tvModAtm_DC);
        this.tvModDef_DC = v.findViewById(R.id.tvModDef_DC);
        this.tvModDfm_DC = v.findViewById(R.id.tvModDfm_DC);
        this.tvModDex_DC = v.findViewById(R.id.tvModDex_DC);
        this.tvModEva_DC = v.findViewById(R.id.tvModEva_DC);
        this.tvModCrt_DC = v.findViewById(R.id.tvModCrt_DC);
        this.tvModAcc_DC = v.findViewById(R.id.tvModAcc_DC);

        this.btBorrar_DC = v.findViewById(R.id.btBorrar_DC);
        this.btBorrar_DC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar clase")
                        .setMessage("¿Seguro que quiere borrar la clase " + tvNombre_DC.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dcvm.borrarClase(id, view);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Nada
                            }
                        }).show();
            }
        });
    }
}