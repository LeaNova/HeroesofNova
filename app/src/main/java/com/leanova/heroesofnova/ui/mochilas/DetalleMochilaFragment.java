package com.leanova.heroesofnova.ui.mochilas;

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
import com.leanova.heroesofnova.databinding.FragmentDetalleMochilaBinding;
import com.leanova.heroesofnova.modelos.Mochila;

public class DetalleMochilaFragment extends Fragment {
    private FragmentDetalleMochilaBinding binding;
    private DetalleMochilaViewModel dmvm;

    private Mochila mochila;
    private TextView tvNombre_DM, tvDetalle_DM, tvPesoMax_DM;
    private Button btEditar_DM, btBorrar_DM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleMochilaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dmvm = new ViewModelProvider(this).get(DetalleMochilaViewModel.class);
        dmvm.getMutableMochila().observe(getViewLifecycleOwner(), new Observer<Mochila>() {
            @Override
            public void onChanged(Mochila m) {
                mochila = m;
                tvNombre_DM.setText(mochila.getNombre());
                tvDetalle_DM.setText(mochila.getDescripcion());
                tvPesoMax_DM.setText(mochila.getPesoMax()+"");
            }
        });
        dmvm.getMochila(getArguments());

        inicializarVista(root);
        dmvm.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btEditar_DM.setVisibility(integer);
                btBorrar_DM.setVisibility(integer);
            }
        });
        dmvm.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DM = v.findViewById(R.id.tvNombre_DM);
        this.tvDetalle_DM = v.findViewById(R.id.tvDetalle_DM);
        this.tvPesoMax_DM = v.findViewById(R.id.tvPesoMax_DM);

        this.btEditar_DM = v.findViewById(R.id.btEditar_DM);
        this.btEditar_DM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bMochila = new Bundle();
                bMochila.putSerializable("mochilaEdit", mochila);
                Navigation.findNavController(view).navigate(R.id.crearMochilaFragment, bMochila);
            }
        });

        this.btBorrar_DM = v.findViewById(R.id.btBorrar_DM);
        this.btBorrar_DM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar mochila")
                        .setMessage("¿Seguro que quiere borrar la mochila " + tvNombre_DM.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dmvm.borrarMochila(mochila.getIdMochila(), view);
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