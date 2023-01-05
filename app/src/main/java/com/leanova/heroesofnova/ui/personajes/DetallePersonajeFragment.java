package com.leanova.heroesofnova.ui.personajes;

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
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetallePersonajeBinding;
import com.leanova.heroesofnova.modelos.Personaje;

public class DetallePersonajeFragment extends Fragment {
    private FragmentDetallePersonajeBinding binding;
    private DetallePersonajeViewModel dpvm;

    private Personaje personaje;
    private TextView tvNombre_DP, tvRaza_DP, tvClase_DP, tvVida_DP, tvNivel_DP, tvExp_DP, tvAtk_DP, tvAtm_DP, tvDef_DP, tvDfm_DP, tvDex_DP, tvEva_DP, tvCrt_DP, tvAcc_DP, tvAviso_DP;
    private Button btJugar_DP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallePersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dpvm = new ViewModelProvider(this).get(DetallePersonajeViewModel.class);
        dpvm.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje p) {
                personaje = p;
                personaje.setGame();

                tvNombre_DP.setText(personaje.getNombre());
                tvRaza_DP.setText(personaje.getRaza().getNombre());
                tvClase_DP.setText(personaje.getClase().getNombre());
                tvVida_DP.setText(personaje.getVida()+"");
                tvNivel_DP.setText(personaje.getNivel()+"");
                tvExp_DP.setText(personaje.getExperiencia()+"/"+personaje.getNextExp());

                tvAtk_DP.setText(personaje.getAtaque()+"");
                tvAtm_DP.setText(personaje.getAtkMagico()+"");
                tvDef_DP.setText(personaje.getDefensa()+"");
                tvDfm_DP.setText(personaje.getDefMagico()+"");
                tvDex_DP.setText(personaje.getAgilidad()+"");
                tvEva_DP.setText(personaje.getEvasion()+"");
                tvCrt_DP.setText(personaje.getCritico()+"");
                tvAcc_DP.setText(personaje.getPrecision()+"");
            }
        });
        dpvm.obtenerPersonaje(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DP = v.findViewById(R.id.tvNombre_DP);
        this.tvRaza_DP = v.findViewById(R.id.tvRaza_DP);
        this.tvClase_DP = v.findViewById(R.id.tvClase_DP);
        this.tvVida_DP = v.findViewById(R.id.tvVida_DP);
        this.tvNivel_DP = v.findViewById(R.id.tvNivel_DP);
        this.tvExp_DP = v.findViewById(R.id.tvExp_DP);
        this.tvAtk_DP = v.findViewById(R.id.tvAtk_DP);
        this.tvAtm_DP = v.findViewById(R.id.tvAtm_DP);
        this.tvDef_DP = v.findViewById(R.id.tvDef_DP);
        this.tvDfm_DP = v.findViewById(R.id.tvDfm_DP);
        this.tvDex_DP = v.findViewById(R.id.tvDex_DP);
        this.tvEva_DP = v.findViewById(R.id.tvEva_DP);
        this.tvCrt_DP = v.findViewById(R.id.tvCrt_DP);
        this.tvAcc_DP = v.findViewById(R.id.tvAcc_DP);
        this.tvAviso_DP = v.findViewById(R.id.tvAviso_CP);

        this.btJugar_DP = v.findViewById(R.id.btJugar_DP);
        this.btJugar_DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bPersonaje = new Bundle();
                bPersonaje.putSerializable("jugar", personaje);
                Navigation.findNavController(view).navigate(R.id.jugarFragment, bPersonaje);
            }
        });
    }
}