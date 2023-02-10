package com.leanova.heroesofnova.ui.personajes.detalle;

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

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabInventarioPersonajeBinding;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvArtefacto;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Personaje;

import java.util.ArrayList;

public class TabInventarioPersonajeFragment extends Fragment {
    private FragmentTabInventarioPersonajeBinding binding;
    private TabInventarioPersonajeViewModel tabInventarioPersonajeVM;

    private Personaje personaje;
    private ListView lvMochilaArma_DP, lvMochilaArmadura_DP, lvMochilaItem_DP, lvMochilaArtefactos_DP;
    private Button btObtener_DP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabInventarioPersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabInventarioPersonajeVM = new ViewModelProvider(this).get(TabInventarioPersonajeViewModel.class);
        tabInventarioPersonajeVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje p) {
                personaje = p;
            }
        });
        tabInventarioPersonajeVM.getMutableArmas().observe(getViewLifecycleOwner(), new Observer<ArrayList<InvArma>>() {
            @Override
            public void onChanged(ArrayList<InvArma> invArmas) {
                InvArmaAdapter iaa = new InvArmaAdapter(getContext(), R.layout.item_inv_arma, invArmas);
                lvMochilaArma_DP.setAdapter(iaa);
            }
        });
        tabInventarioPersonajeVM.getMutableArmaduras().observe(getViewLifecycleOwner(), new Observer<ArrayList<InvArmadura>>() {
            @Override
            public void onChanged(ArrayList<InvArmadura> invArmaduras) {
                InvArmaduraAdapter iaa = new InvArmaduraAdapter(getContext(), R.layout.item_inv_armadura, invArmaduras);
                lvMochilaArmadura_DP.setAdapter(iaa);
            }
        });
        tabInventarioPersonajeVM.getMutableItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<InvItem>>() {
            @Override
            public void onChanged(ArrayList<InvItem> invItems) {
                InvItemAdapter iia = new InvItemAdapter(getContext(), R.layout.item_inv_item, invItems);
                lvMochilaItem_DP.setAdapter(iia);
            }
        });
        tabInventarioPersonajeVM.getMutableArtefactos().observe(getViewLifecycleOwner(), new Observer<ArrayList<InvArtefacto>>() {
            @Override
            public void onChanged(ArrayList<InvArtefacto> invArtefactos) {
                InvArtefactoAdapter iaa = new InvArtefactoAdapter(getContext(), R.layout.item_inv_artefacto, invArtefactos);
                lvMochilaArtefactos_DP.setAdapter(iaa);
            }
        });

        inicializarVista(root);
        tabInventarioPersonajeVM.getPersonaje();

        return root;
    }

    private void inicializarVista(View v) {
        this.lvMochilaArma_DP = v.findViewById(R.id.lvMochilaArma_DP);
        this.lvMochilaArmadura_DP = v.findViewById(R.id.lvMochilaArmadura_DP);
        this.lvMochilaItem_DP = v.findViewById(R.id.lvMochilaItem_DP);
        this.lvMochilaArtefactos_DP = v.findViewById(R.id.lvMochilaArtefactos_DP);
        this.btObtener_DP = v.findViewById(R.id.btObtener_DP);
        this.btObtener_DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.agregarInventarioFragment);
            }
        });
    }
}