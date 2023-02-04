package com.leanova.heroesofnova.ui.items;

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
import com.leanova.heroesofnova.databinding.FragmentItemBinding;
import com.leanova.heroesofnova.modelos.Item;

import java.util.ArrayList;

public class ItemFragment extends Fragment {
    private FragmentItemBinding binding;
    private ItemViewModel itemVM;

    private ListView lvItem;
    private Button btNuevo_Item;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        itemVM = new ViewModelProvider(this).get(ItemViewModel.class);

        inicializarVista(root);
        itemVM.getMutableItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                ItemAdapter ia = new ItemAdapter(getContext(), R.layout.item_item, items);
                lvItem.setAdapter(ia);
            }
        });
        itemVM.obtenerItems();
        itemVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_Item.setVisibility(integer);
            }
        });
        itemVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvItem = v.findViewById(R.id.lvItem);
        this.lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item iSeleccionado = itemVM.getListaItems().get(i);

                Bundle bItem = new Bundle();
                bItem.putSerializable("item", iSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleItemFragment, bItem);
            }
        });

        this.btNuevo_Item = v.findViewById(R.id.btNuevo_Item);
        this.btNuevo_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearItemFragment);
            }
        });
    }
}