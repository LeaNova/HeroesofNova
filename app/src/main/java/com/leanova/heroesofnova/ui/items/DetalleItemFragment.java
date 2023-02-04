package com.leanova.heroesofnova.ui.items;

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
import com.leanova.heroesofnova.databinding.FragmentDetalleItemBinding;
import com.leanova.heroesofnova.modelos.Item;

public class DetalleItemFragment extends Fragment {
    private FragmentDetalleItemBinding binding;
    private DetalleItemViewModel detalleItemVM;

    private Item item;
    private TextView tvNombre_DItem, tvTipo_DItem, tvVida_DItem, tvEnergia_DItem, tvAtk_DItem, tvAtm_DItem, tvDef_DItem, tvDfm_DItem, tvDex_DItem, tvEva_DItem, tvCrt_DItem, tvAcc_DItem, tvPrecio_DItem, tvPeso_DItem, tvDetalle_DItem;
    private Button btEditar_DItem, btBorrar_DItem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleItemVM = new ViewModelProvider(this).get(DetalleItemViewModel.class);
        detalleItemVM.getMutableItem().observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item i) {
                item = i;
                tvNombre_DItem.setText(item.getNombre());
                tvTipo_DItem.setText(item.getTipo().getNombre());
                tvVida_DItem.setText("+" + item.getBonoVida());
                tvEnergia_DItem.setText("+" + item.getBonoEnergia());
                tvAtk_DItem.setText("+" + item.getBonoAtk());
                tvAtm_DItem.setText("+" + item.getBonoAtm());
                tvDef_DItem.setText("+" + item.getBonoDef());
                tvDfm_DItem.setText("+" + item.getBonoDfm());
                tvDex_DItem.setText("+" + item.getBonoDex());
                tvEva_DItem.setText("+" + item.getBonoEva());
                tvCrt_DItem.setText("+" + item.getBonoCrt());
                tvAcc_DItem.setText("+" + item.getBonoAcc());
                tvPrecio_DItem.setText(item.getPrecio()+"");
                tvPeso_DItem.setText(item.getPeso() + "Kg.");
                tvDetalle_DItem.setText(item.getDescripcion());
            }
        });
        detalleItemVM.getItem(getArguments());

        inicializarVista(root);
        detalleItemVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btEditar_DItem.setVisibility(integer);
                btBorrar_DItem.setVisibility(integer);
            }
        });
        detalleItemVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DItem = v.findViewById(R.id.tvNombre_DItem);
        this.tvTipo_DItem = v.findViewById(R.id.tvTipo_DItem);
        this.tvVida_DItem = v.findViewById(R.id.tvVida_DItem);
        this.tvEnergia_DItem = v.findViewById(R.id.tvEnergia_DItem);
        this.tvAtk_DItem = v.findViewById(R.id.tvAtk_DItem);
        this.tvAtm_DItem = v.findViewById(R.id.tvAtm_DItem);
        this.tvDef_DItem = v.findViewById(R.id.tvDef_DItem);
        this.tvDfm_DItem = v.findViewById(R.id.tvDfm_DItem);
        this.tvDex_DItem = v.findViewById(R.id.tvDex_DItem);
        this.tvEva_DItem = v.findViewById(R.id.tvEva_DItem);
        this.tvCrt_DItem = v.findViewById(R.id.tvCrt_DItem);
        this.tvAcc_DItem = v.findViewById(R.id.tvAcc_DItem);
        this.tvPrecio_DItem = v.findViewById(R.id.tvPrecio_DItem);
        this.tvPeso_DItem = v.findViewById(R.id.tvPeso_DItem);
        this.tvDetalle_DItem = v.findViewById(R.id.tvDetalle_DItem);

        this.btEditar_DItem = v.findViewById(R.id.btEditar_DItem);
        this.btEditar_DItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bItem = new Bundle();
                bItem.putSerializable("itemEdit", item);
                Navigation.findNavController(view).navigate(R.id.crearItemFragment, bItem);
            }
        });

        this.btBorrar_DItem = v.findViewById(R.id.btBorrar_DItem);
        this.btBorrar_DItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar item")
                        .setMessage("¿Seguro que quiere borrar el item " + tvNombre_DItem.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                detalleItemVM.borrarItem(item.getIdItem(), view);
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