package com.leanova.heroesofnova.ui.artefactos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetalleArtefactoBinding;
import com.leanova.heroesofnova.modelos.Artefacto;

public class DetalleArtefactoFragment extends Fragment {
    private FragmentDetalleArtefactoBinding binding;
    private DetalleArtefactoViewModel detalleArtefactoVM;

    private Artefacto artefacto;
    private TextView tvNombre_DArtefacto, tvSeccion_DArtefacto, tvVida_DArtefacto, tvEnergia_DArtefacto, tvAtk_DArtefacto, tvAtm_DArtefacto, tvDef_DArtefacto, tvDfm_DArtefacto, tvDex_DArtefacto, tvEva_DArtefacto, tvCrt_DArtefacto, tvAcc_DArtefacto, tvPrecio_DArtefacto, tvPeso_DArtefacto, tvDetalle_DArtefacto;
    private CheckBox cbDisponible_DArtefacto;
    private Button btEditar_DArtefacto, btBorrar_DArtefacto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleArtefactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleArtefactoVM = new ViewModelProvider(this).get(DetalleArtefactoViewModel.class);
        detalleArtefactoVM.getMutableArtefacto().observe(getViewLifecycleOwner(), new Observer<Artefacto>() {
            @Override
            public void onChanged(Artefacto a) {
                artefacto = a;
                tvNombre_DArtefacto.setText(artefacto.getNombre());
                cbDisponible_DArtefacto.setChecked(a.isDisponible());
                tvSeccion_DArtefacto.setText(artefacto.getSeccion().getNombre());
                tvVida_DArtefacto.setText("+ " + artefacto.getBonoVida());
                tvEnergia_DArtefacto.setText("+ " + artefacto.getBonoEnergia());
                tvAtk_DArtefacto.setText("+" + artefacto.getBonoAtk());
                tvAtm_DArtefacto.setText("+" + artefacto.getBonoAtm());
                tvDef_DArtefacto.setText("+" + artefacto.getBonoDef());
                tvDfm_DArtefacto.setText("+" + artefacto.getBonoDfm());
                tvDex_DArtefacto.setText("+" + artefacto.getBonoDex());
                tvEva_DArtefacto.setText("+" + artefacto.getBonoEva());
                tvCrt_DArtefacto.setText("+" + artefacto.getBonoCrt());
                tvAcc_DArtefacto.setText("+" + artefacto.getBonoAcc());
                tvPrecio_DArtefacto.setText(artefacto.getPrecio()+"");
                tvPeso_DArtefacto.setText(artefacto.getPeso() + "kg.");
                tvDetalle_DArtefacto.setText(artefacto.getDescripcion());
            }
        });
        detalleArtefactoVM.getArtefacto(getArguments());

        inicializarVista(root);
        detalleArtefactoVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                cbDisponible_DArtefacto.setVisibility(integer);
                btEditar_DArtefacto.setVisibility(integer);
                btBorrar_DArtefacto.setVisibility(integer);
            }
        });
        detalleArtefactoVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DArtefacto = v.findViewById(R.id.tvNombre_DArtefacto);
        this.tvSeccion_DArtefacto = v.findViewById(R.id.tvSeccion_DArtefacto);
        this.tvVida_DArtefacto = v.findViewById(R.id.tvVida_DArtefacto);
        this.tvEnergia_DArtefacto = v.findViewById(R.id.tvEnergia_DArtefacto);
        this.tvAtk_DArtefacto = v.findViewById(R.id.tvAtk_DArtefacto);
        this.tvAtm_DArtefacto = v.findViewById(R.id.tvAtm_DArtefacto);
        this.tvDef_DArtefacto = v.findViewById(R.id.tvDef_DArtefacto);
        this.tvDfm_DArtefacto = v.findViewById(R.id.tvDfm_DArtefacto);
        this.tvDex_DArtefacto = v.findViewById(R.id.tvDex_DArtefacto);
        this.tvEva_DArtefacto = v.findViewById(R.id.tvEva_DArtefacto);
        this.tvCrt_DArtefacto = v.findViewById(R.id.tvCrt_DArtefacto);
        this.tvAcc_DArtefacto = v.findViewById(R.id.tvAcc_DArtefacto);
        this.tvPrecio_DArtefacto = v.findViewById(R.id.tvPrecio_DArtefacto);
        this.tvPeso_DArtefacto = v.findViewById(R.id.tvPeso_DArtefacto);
        this.tvDetalle_DArtefacto = v.findViewById(R.id.tvDetalle_DArtefacto);
        this.cbDisponible_DArtefacto = v.findViewById(R.id.cbDisponible_DArtefacto);
        this.cbDisponible_DArtefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleArtefactoVM.cambiarDisponibilidad(artefacto.getIdArtefacto());
            }
        });

        this.btEditar_DArtefacto = v.findViewById(R.id.btEditar_DArtefacto);
        this.btEditar_DArtefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bArtefacto = new Bundle();
                bArtefacto.putSerializable("artefactoEdit", artefacto);
                Navigation.findNavController(view).navigate(R.id.crearArtefactoFragment, bArtefacto);
            }
        });

        this.btBorrar_DArtefacto = v.findViewById(R.id.btBorrar_DArtefacto);
        this.btBorrar_DArtefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar artefacto")
                        .setMessage("¿Seguro que quiere borrar el artefacto " + tvNombre_DArtefacto.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                detalleArtefactoVM.borrarArtefacto(artefacto.getIdArtefacto(), view);
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