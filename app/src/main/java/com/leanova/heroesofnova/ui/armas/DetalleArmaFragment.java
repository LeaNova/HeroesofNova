package com.leanova.heroesofnova.ui.armas;

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
import com.leanova.heroesofnova.databinding.FragmentDetalleArmaBinding;
import com.leanova.heroesofnova.modelos.Arma;

public class DetalleArmaFragment extends Fragment {
    private FragmentDetalleArmaBinding binding;
    private DetalleArmaViewModel detalleArmaVM;

    private Arma arma;
    private TextView tvNombre_DArma, tvCategoria_DArma, tvDanio_DArma, tvBono_DArma, tvAtk_DArma, tvAtm_DArma, tvDef_DArma, tvDfm_DArma, tvCrt_DArma, tvAcc_DArma, tvModAtk_DArma, tvModAtm_DArma, tvModDef_DArma, tvModDfm_DArma, tvPrecio_DArma, tvPeso_DArma, tvDetalle_DArma;
    private Button btEditar_DArma, btBorrar_DArma;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleArmaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleArmaVM = new ViewModelProvider(this).get(DetalleArmaViewModel.class);
        detalleArmaVM.getMutableArma().observe(getViewLifecycleOwner(), new Observer<Arma>() {
            @Override
            public void onChanged(Arma a) {
                arma = a;
                tvNombre_DArma.setText(arma.getNombre());
                tvCategoria_DArma.setText(arma.getCategoria().getNombre());
                tvDanio_DArma.setText("1d" + arma.getDanioArma());
                tvBono_DArma.setText("+" + arma.getBonoArma());
                tvAtk_DArma.setText("+" + arma.getBonoAtk());
                tvAtm_DArma.setText("+" + arma.getBonoAtm());
                tvDef_DArma.setText("+" + arma.getBonoDef());
                tvDfm_DArma.setText("+" + arma.getBonoDfm());
                tvCrt_DArma.setText("+" + arma.getBonoCrt());
                tvAcc_DArma.setText("+" + arma.getBonoAcc());
                tvModAtk_DArma.setText("x" + arma.getModAtk());
                tvModAtm_DArma.setText("x" + arma.getModAtm());
                tvModDef_DArma.setText("x" + arma.getModDef());
                tvModDfm_DArma.setText("x" + arma.getModDfm());
                tvPrecio_DArma.setText(arma.getPrecio()+"");
                tvPeso_DArma.setText(arma.getPeso() + "kg.");
                tvDetalle_DArma.setText(arma.getDescripcion());
            }
        });
        detalleArmaVM.getArma(getArguments());

        inicializarVista(root);
        detalleArmaVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btEditar_DArma.setVisibility(integer);
                btBorrar_DArma.setVisibility(integer);
            }
        });
        detalleArmaVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DArma = v.findViewById(R.id.tvNombre_DArma);
        this.tvCategoria_DArma = v.findViewById(R.id.tvCategoria_DArma);
        this.tvDanio_DArma = v.findViewById(R.id.tvDanio_DArma);
        this.tvBono_DArma = v.findViewById(R.id.tvBono_DArma);
        this.tvAtk_DArma = v.findViewById(R.id.tvAtk_DArma);
        this.tvAtm_DArma = v.findViewById(R.id.tvAtm_DArma);
        this.tvDef_DArma = v.findViewById(R.id.tvDef_DArma);
        this.tvDfm_DArma = v.findViewById(R.id.tvDfm_DArma);
        this.tvCrt_DArma = v.findViewById(R.id.tvCrt_DArma);
        this.tvAcc_DArma = v.findViewById(R.id.tvAcc_DArma);
        this.tvModAtk_DArma = v.findViewById(R.id.tvModAtk_DArma);
        this.tvModAtm_DArma = v.findViewById(R.id.tvModAtm_DArma);
        this.tvModDef_DArma = v.findViewById(R.id.tvModDef_DArma);
        this.tvModDfm_DArma = v.findViewById(R.id.tvModDfm_DArma);
        this.tvPrecio_DArma = v.findViewById(R.id.tvPrecio_DArma);
        this.tvPeso_DArma = v.findViewById(R.id.tvPeso_DArma);
        this.tvDetalle_DArma = v.findViewById(R.id.tvDetalle_DArma);

        this.btEditar_DArma = v.findViewById(R.id.btEditar_DArma);
        this.btEditar_DArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bArma = new Bundle();
                bArma.putSerializable("armaEdit", arma);
                Navigation.findNavController(view).navigate(R.id.crearArnaFragment, bArma);
            }
        });

        this.btBorrar_DArma = v.findViewById(R.id.btBorrar_DArma);
        this.btBorrar_DArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar arma")
                        .setMessage("¿Seguro que quiere borrar el arma " + tvNombre_DArma.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                detalleArmaVM.borrarArma(arma.getIdArma(), view);
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