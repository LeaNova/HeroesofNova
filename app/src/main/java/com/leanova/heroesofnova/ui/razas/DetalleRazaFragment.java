package com.leanova.heroesofnova.ui.razas;

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
import com.leanova.heroesofnova.databinding.FragmentDetalleRazaBinding;
import com.leanova.heroesofnova.modelos.Raza;

public class DetalleRazaFragment extends Fragment {
    private FragmentDetalleRazaBinding binding;
    private DetalleRazaViewModel detalleRazaVM;

    private Raza raza;
    private TextView tvNombre_DR, tvVidaBase_DR, tvEnergiaBase_DR, tvAtkBase_DR, tvAtmBase_DR, tvDefBase_DR, tvDfmBase_DR, tvDexBase_DR, tvEvaBase_DR, tvCrtBase_DR, tvAccBase_DR, tvDetalle_DR;
    private CheckBox cbDisponible_DR;
    private Button btEditar_DR, btBorrar_DR;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleRazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleRazaVM = new ViewModelProvider(this).get(DetalleRazaViewModel.class);
        detalleRazaVM.getMutableRaza().observe(getViewLifecycleOwner(), new Observer<Raza>() {
            @Override
            public void onChanged(Raza r) {
                raza = r;
                tvNombre_DR.setText(raza.getNombre());
                cbDisponible_DR.setChecked(r.isDisponible());
                tvVidaBase_DR.setText(raza.getVidaBase()+"");
                tvEnergiaBase_DR.setText(raza.getEnergiaBase()+"");
                tvAtkBase_DR.setText(raza.getBaseAtk()+"");
                tvAtmBase_DR.setText(raza.getBaseAtm()+"");
                tvDefBase_DR.setText(raza.getBaseDef()+"");
                tvDfmBase_DR.setText(raza.getBaseDfm()+"");
                tvDexBase_DR.setText(raza.getBaseDex()+"");
                tvEvaBase_DR.setText(raza.getBaseEva()+"");
                tvCrtBase_DR.setText(raza.getBaseCrt()+"");
                tvAccBase_DR.setText(raza.getBaseAcc()+"");
                tvDetalle_DR.setText(raza.getDescripcion());
            }
        });
        detalleRazaVM.getRaza(getArguments());

        inicializarVista(root);
        detalleRazaVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                cbDisponible_DR.setVisibility(integer);
                btEditar_DR.setVisibility(integer);
                btBorrar_DR.setVisibility(integer);
            }
        });
        detalleRazaVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DR = v.findViewById(R.id.tvNombre_DR);
        this.tvVidaBase_DR = v.findViewById(R.id.tvVidaBase_DR);
        this.tvEnergiaBase_DR = v.findViewById(R.id.tvEnergiaBase_DR);
        this.tvAtkBase_DR = v.findViewById(R.id.tvAtkBase_DR);
        this.tvAtmBase_DR = v.findViewById(R.id.tvAtmBase_DR);
        this.tvDefBase_DR = v.findViewById(R.id.tvDefBase_DR);
        this.tvDfmBase_DR = v.findViewById(R.id.tvDfmBase_DR);
        this.tvDexBase_DR = v.findViewById(R.id.tvDexBase_DR);
        this.tvEvaBase_DR = v.findViewById(R.id.tvEvaBase_DR);
        this.tvCrtBase_DR = v.findViewById(R.id.tvCrtBase_DR);
        this.tvAccBase_DR = v.findViewById(R.id.tvAccBase_DR);
        this.tvDetalle_DR = v.findViewById(R.id.tvDetalle_DR);
        this.cbDisponible_DR = v.findViewById(R.id.cbDisponible_DR);
        this.cbDisponible_DR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detalleRazaVM.cambiarDisponibilidad(raza.getIdRaza());
            }
        });

        this.btEditar_DR = v.findViewById(R.id.btEditar_DR);
        this.btEditar_DR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bRaza = new Bundle();
                bRaza.putSerializable("razaEdit", raza);
                Navigation.findNavController(view).navigate(R.id.crearRazaFragment, bRaza);
            }
        });

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
                                detalleRazaVM.borrarRaza(raza.getIdRaza(), view);
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