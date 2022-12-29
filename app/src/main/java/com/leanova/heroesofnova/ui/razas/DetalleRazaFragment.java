package com.leanova.heroesofnova.ui.razas;

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
import com.leanova.heroesofnova.databinding.FragmentDetalleRazaBinding;
import com.leanova.heroesofnova.modelos.Raza;

public class DetalleRazaFragment extends Fragment {
    private FragmentDetalleRazaBinding binding;
    private DetalleRazaViewModel drvm;

    private int id;
    private TextView tvNombre_DR, tvVidaBase_DR, tvDetalle_DR, tvAtkBase_DR, tvAtmBase_DR, tvDefBase_DR, tvDfmBase_DR, tvDexBase_DR, tvEvaBase_DR, tvCrtBase_DR, tvAccBase_DR;
    private Button btEditar_DR, btBorrar_DR;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleRazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        drvm = new ViewModelProvider(this).get(DetalleRazaViewModel.class);
        drvm.getMutableRaza().observe(getViewLifecycleOwner(), new Observer<Raza>() {
            @Override
            public void onChanged(Raza raza) {
                id = raza.getIdRaza();
                tvNombre_DR.setText(raza.getNombre());
                tvDetalle_DR.setText(raza.getDescripcion());
                tvVidaBase_DR.setText(raza.getVidaBase()+"");
                tvAtkBase_DR.setText(raza.getBaseAtk()+"");
                tvAtmBase_DR.setText(raza.getBaseAtm()+"");
                tvDefBase_DR.setText(raza.getBaseDef()+"");
                tvDfmBase_DR.setText(raza.getBaseDfm()+"");
                tvDexBase_DR.setText(raza.getBaseDex()+"");
                tvEvaBase_DR.setText(raza.getBaseEva()+"");
                tvCrtBase_DR.setText(raza.getBaseCrt()+"");
                tvAccBase_DR.setText(raza.getBaseAcc()+"");
            }
        });
        drvm.obtenerRaza(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DR = v.findViewById(R.id.tvNombre_DR);
        this.tvDetalle_DR = v.findViewById(R.id.tvDetalle_DR);
        this.tvVidaBase_DR = v.findViewById(R.id.tvVidaBase_DR);
        this.tvAtkBase_DR = v.findViewById(R.id.tvAtkBase_DR);
        this.tvAtmBase_DR = v.findViewById(R.id.tvAtmBase_DR);
        this.tvDefBase_DR = v.findViewById(R.id.tvDefBase_DR);
        this.tvDfmBase_DR = v.findViewById(R.id.tvDfmBase_DR);
        this.tvDexBase_DR = v.findViewById(R.id.tvDexBase_DR);
        this.tvEvaBase_DR = v.findViewById(R.id.tvEvaBase_DR);
        this.tvCrtBase_DR = v.findViewById(R.id.tvCrtBase_DR);
        this.tvAccBase_DR = v.findViewById(R.id.tvAccBase_DR);

        this.btBorrar_DR = v.findViewById(R.id.btBorrar_DR);
        this.btBorrar_DR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar raza")
                        .setMessage("¿Seguro que quiere borrar la raza " + tvNombre_DR.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                drvm.borrarRaza(id, view);
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