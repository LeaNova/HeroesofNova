package com.leanova.heroesofnova.ui.mochilas;

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
import com.leanova.heroesofnova.databinding.FragmentMochilaBinding;
import com.leanova.heroesofnova.modelos.Mochila;

import java.util.ArrayList;

public class MochilaFragment extends Fragment {
    private FragmentMochilaBinding binding;
    private MochilaViewModel mvm;

    private ListView lvMochila_M;
    private Button btNuevo_M;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMochilaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mvm = new ViewModelProvider(this).get(MochilaViewModel.class);

        inicializarVista(root);
        mvm.getMutableMochilas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Mochila>>() {
            @Override
            public void onChanged(ArrayList<Mochila> mochilas) {
                MochilaAdapter ma = new MochilaAdapter(getContext(), R.layout.mochila_item, mochilas);
                lvMochila_M.setAdapter(ma);
            }
        });
        mvm.obtenerMochilas();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvMochila_M = v.findViewById(R.id.lvMochila_M);
        this.lvMochila_M.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mochila mSeleccionado = mvm.getListaMochilas().get(i);

                Bundle bMochila = new Bundle();
                bMochila.putSerializable("mochila", mSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleMochilaFragment, bMochila);
            }
        });

        this.btNuevo_M = v.findViewById(R.id.btNuevo_M);
        this.btNuevo_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearMochilaFragment);
            }
        });
    }
}