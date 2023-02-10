package com.leanova.heroesofnova.ui.personajes.jugar;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabItemBinding;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;

import java.util.ArrayList;

public class TabItemFragment extends Fragment {
    private FragmentTabItemBinding binding;
    private TabItemViewModel tabInventarioVM;

    private TextView tvPersonaje_JP, tvVida_JP, tvVidaT_JP, tvEnergia_JP, tvEnergiaT_JP;
    private ListView lvConsumibles_JP;
    private Button btAgregar_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabInventarioVM = new ViewModelProvider(this).get(TabItemViewModel.class);
        tabInventarioVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Jugar>() {
            @Override
            public void onChanged(Jugar jugar) {
                Personaje personaje = jugar.getPersonaje();
                tvPersonaje_JP.setText(jugar.getPersonaje().getNombre() + " - Nivel " + jugar.getPersonaje().getNivel());
                tvVida_JP.setText(personaje.getVidaAct()+"");
                tvVidaT_JP.setText(personaje.getVida()+"");
                tvEnergia_JP.setText(personaje.getEnergiaAct()+"");
                tvEnergiaT_JP.setText(personaje.getEnergia()+"");
            }
        });
        tabInventarioVM.getMutableConsumibles().observe(getViewLifecycleOwner(), new Observer<ArrayList<InvItem>>() {
            @Override
            public void onChanged(ArrayList<InvItem> invItems) {
                TabItemAdapter tia = new TabItemAdapter(getContext(), R.layout.item_tab_item, invItems);
                lvConsumibles_JP.setAdapter(tia);
            }
        });

        inicializarVista(root);
        tabInventarioVM.obtenerPersonaje();
        tabInventarioVM.obtenerConsumibles();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabInventarioVM.obtenerPersonaje();
        tabInventarioVM.refresh();
    }

    private void inicializarVista(View v) {
        this.tvPersonaje_JP  = v.findViewById(R.id.tvPersonaje_JP);
        this.tvVida_JP = v.findViewById(R.id.tvVida_JP);
        this.tvVidaT_JP = v.findViewById(R.id.tvVidaT_JP);
        this.tvEnergia_JP = v.findViewById(R.id.tvEnergia_JP);
        this.tvEnergiaT_JP = v.findViewById(R.id.tvEnergiaT_JP);
        this.lvConsumibles_JP  = v.findViewById(R.id.lvConsumibles_JP);

        this.btAgregar_JP  = v.findViewById(R.id.btAgregar_JP);
        this.btAgregar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.agregarInventarioFragment);
            }
        });
    }
}