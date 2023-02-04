package com.leanova.heroesofnova.ui.armas;

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
import com.leanova.heroesofnova.databinding.FragmentArmaBinding;
import com.leanova.heroesofnova.modelos.Arma;

import java.util.ArrayList;

public class ArmaFragment extends Fragment {
    private FragmentArmaBinding binding;
    private ArmaViewModel armaVM;

    private ListView lvArma;
    private Button btNuevo_Arma;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArmaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        armaVM = new ViewModelProvider(this).get(ArmaViewModel.class);

        inicializarVista(root);
        armaVM.getMutableArmas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Arma>>() {
            @Override
            public void onChanged(ArrayList<Arma> armas) {
                ArmaAdapter aa = new ArmaAdapter(getContext(), R.layout.item_arma, armas);
                lvArma.setAdapter(aa);
            }
        });
        armaVM.obtenerArmas();
        armaVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_Arma.setVisibility(integer);
            }
        });
        armaVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvArma = v.findViewById(R.id.lvArma);
        this.lvArma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Arma aSeleccionado = armaVM.getListaArmas().get(i);

                Bundle bArma = new Bundle();
                bArma.putSerializable("arma", aSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleArmaFragment, bArma);
            }
        });

        this.btNuevo_Arma = v.findViewById(R.id.btNuevo_Arma);
        this.btNuevo_Arma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearArnaFragment);
            }
        });
    }
}