package com.leanova.heroesofnova.ui.razas;

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
import com.leanova.heroesofnova.databinding.FragmentRazaBinding;
import com.leanova.heroesofnova.modelos.Raza;

import java.util.ArrayList;

public class RazaFragment extends Fragment {
    private FragmentRazaBinding binding;
    private RazaViewModel rvm;

    private ListView lvRaza_R;
    private Button btNuevo_R;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rvm = new ViewModelProvider(this).get(RazaViewModel.class);

        inicializarVista(root);
        rvm.getMutableRazas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Raza>>() {
            @Override
            public void onChanged(ArrayList<Raza> razas) {
                RazaAdapter ra = new RazaAdapter(getContext(), R.layout.item_raza, razas);
                lvRaza_R.setAdapter(ra);
            }
        });

        rvm.obtenerRazas();
        rvm.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_R.setVisibility(integer);
            }
        });
        rvm.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvRaza_R = v.findViewById(R.id.lvRaza_R);
        this.lvRaza_R.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Raza rSeleccionado = rvm.getListaRazas().get(i);

                Bundle bRaza = new Bundle();
                bRaza.putSerializable("raza", rSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleRazaFragment, bRaza);
            }
        });

        this.btNuevo_R = v.findViewById(R.id.btNuevo_R);
        this.btNuevo_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearRazaFragment);
            }
        });
    }
}