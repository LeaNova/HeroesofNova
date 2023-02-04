package com.leanova.heroesofnova.ui.armaduras;

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
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetalleArmaduraBinding;
import com.leanova.heroesofnova.modelos.Armadura;

public class DetalleArmaduraFragment extends Fragment {
    private FragmentDetalleArmaduraBinding binding;
    private DetalleArmaduraViewModel detalleArmaduraVM;

    private Armadura armadura;
    private TextView tvNombre_DArmadura, tvDef_DArmadura, tvDfm_DArmadura, tvDex_DArmadura, tvEva_DArmadura, tvModDef_DArmadura, tvModDfm_DArmadura, tvPrecio_DArmadura, tvPeso_DArmadura, tvDetalle_DArmadura;
    private Button btEditar_DArmadura, btBorrar_DArmadura;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleArmaduraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleArmaduraVM = new ViewModelProvider(this).get(DetalleArmaduraViewModel.class);
        detalleArmaduraVM.getMutableArmadura().observe(getViewLifecycleOwner(), new Observer<Armadura>() {
            @Override
            public void onChanged(Armadura a) {
                armadura = a;
                tvNombre_DArmadura.setText(armadura.getNombre());
                tvDef_DArmadura.setText("+" + armadura.getBonoDef());
                tvDfm_DArmadura.setText("+" + armadura.getBonoDfm());
                tvDex_DArmadura.setText("+" + armadura.getBonoDex());
                tvEva_DArmadura.setText("+" + armadura.getBonoEva());
                tvModDef_DArmadura.setText("x" + armadura.getModDef());
                tvModDfm_DArmadura.setText("x" + armadura.getModDef());
                tvPrecio_DArmadura.setText(armadura.getPrecio()+"");
                tvPeso_DArmadura.setText(armadura.getPeso() + "Kg.");
                tvDetalle_DArmadura.setText(armadura.getDescripcion());
            }
        });
        detalleArmaduraVM.getArmadura(getArguments());

        inicializarVista(root);
        detalleArmaduraVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btEditar_DArmadura.setVisibility(integer);
                btBorrar_DArmadura.setVisibility(integer);
            }
        });
        detalleArmaduraVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DArmadura = v.findViewById(R.id.tvNombre_DArmadura);
        this.tvDef_DArmadura = v.findViewById(R.id.tvDef_DArmadura);
        this.tvDfm_DArmadura = v.findViewById(R.id.tvDfm_DArmadura);
        this.tvDex_DArmadura = v.findViewById(R.id.tvDex_DArmadura);
        this.tvEva_DArmadura = v.findViewById(R.id.tvEva_DArmadura);
        this.tvModDef_DArmadura = v.findViewById(R.id.tvModDef_DArmadura);
        this.tvModDfm_DArmadura = v.findViewById(R.id.tvModDfm_DArmadura);
        this.tvPrecio_DArmadura = v.findViewById(R.id.tvPrecio_DArmadura);
        this.tvPeso_DArmadura = v.findViewById(R.id.tvPeso_DArmadura);
        this.tvDetalle_DArmadura = v.findViewById(R.id.tvDetalle_DArmadura);

        this.btEditar_DArmadura = v.findViewById(R.id.btEditar_DArmadura);
        this.btEditar_DArmadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bArmadura = new Bundle();
                bArmadura.putSerializable("armaduraEdit", armadura);
                Navigation.findNavController(view).navigate(R.id.crearArmaduraFragment, bArmadura);
            }
        });

        this.btBorrar_DArmadura = v.findViewById(R.id.btBorrar_DArmadura);
        this.btBorrar_DArmadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar armadura")
                        .setMessage("¿Seguro que quiere borrar la armadura " + tvNombre_DArmadura.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                detalleArmaduraVM.borrarArmadura(armadura.getIdArmadura(), view);
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