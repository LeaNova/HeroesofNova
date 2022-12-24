package com.leanova.heroesofnova.ui.personajes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentPersonajeBinding;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

public class PersonajeFragment extends Fragment {
    private FragmentPersonajeBinding binding;
    private PersonajeViewModel pvm;
    private PersonajeValues pv;
    private RecyclerView rv;
    private TextView tvAviso_PJ;
    private Button btAgregar_PJ;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pv = new PersonajeValues(getContext());
        pvm = new ViewModelProvider(this).get(PersonajeViewModel.class);

        inicializarVista(root);
        pvm.getMutablePersonajes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Personaje>>() {
            @Override
            public void onChanged(ArrayList<Personaje> personajes) {
                PersonajeAdapter pa = new PersonajeAdapter(getContext(), getLayoutInflater(), personajes);
                rv.setAdapter(pa);
            }
        });

        pvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_PJ.setVisibility(View.VISIBLE);
                tvAviso_PJ.setText(s);
            }
        });
        pvm.obtenerPersonajes();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void inicializarVista(View v) {
        this.rv = v.findViewById(R.id.rvPersonajes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        this.rv.setLayoutManager(gridLayoutManager);

        this.tvAviso_PJ = v.findViewById(R.id.tvAviso_PJ);
        this.tvAviso_PJ.setText("");
        this.tvAviso_PJ.setVisibility(View.GONE);

        this.btAgregar_PJ = v.findViewById(R.id.btAgregar_PJ);
        this.btAgregar_PJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearPersonajeFragment);
            }
        });
    }
}