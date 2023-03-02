package com.leanova.heroesofnova.ui.items;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentItemBinding;
import com.leanova.heroesofnova.modelos.Item;

import java.util.ArrayList;

public class ItemFragment extends Fragment {
    private FragmentItemBinding binding;
    private ItemViewModel itemVM;

    private Button btNuevo_Item;
    private EditText etBuscar_Item;
    private ListView lvItem;
    private TextView tvAviso_Item;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        itemVM = new ViewModelProvider(this).get(ItemViewModel.class);
        itemVM.getMutableResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_Item.setText(s);
            }
        });

        inicializarVista(root);
        itemVM.getMutableItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                ItemAdapter adapter = new ItemAdapter(getContext(), R.layout.item_item, items);
                lvItem.setAdapter(adapter);
                etBuscar_Item.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) { }
                });
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
        this.btNuevo_Item = v.findViewById(R.id.btNuevo_Item);
        this.btNuevo_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearItemFragment);
            }
        });

        this.etBuscar_Item = v.findViewById(R.id.etBuscar_Item);
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

        this.tvAviso_Item = v.findViewById(R.id.tvAviso_Item);
        this.tvAviso_Item.setText("");
    }
}