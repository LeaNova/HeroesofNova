package com.leanova.heroesofnova.ui.personajes.detalle;

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
import android.widget.ListView;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabDetallePersonajeBinding;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Personaje;

import java.util.ArrayList;

public class TabDetallePersonajeFragment extends Fragment {
    private FragmentTabDetallePersonajeBinding binding;
    private TabDetallePersonajeViewModel detallePersonajeVM;

    private Personaje personaje;
    private TextView tvNombre_DP, tvRaza_DP, tvClase_DP, tvVida_DP, tvEnergia_DP, tvNivel_DP, tvExp_DP, tvAtk_DP, tvAtm_DP, tvDef_DP, tvDfm_DP, tvDex_DP, tvEva_DP, tvCrt_DP, tvAcc_DP, tvDescripcion_DP;
    private Button btJugar_DP, btBorrar_DP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabDetallePersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detallePersonajeVM = new ViewModelProvider(this).get(TabDetallePersonajeViewModel.class);
        detallePersonajeVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje p) {
                personaje = p;
                personaje.setGame();

                tvNombre_DP.setText(personaje.getNombre());
                tvRaza_DP.setText(personaje.getRaza().getNombre() + " (" +personaje.getGenero().getNombre() + ")");
                tvClase_DP.setText(personaje.getClase().getNombre());
                tvVida_DP.setText(personaje.getVida()+"");
                tvEnergia_DP.setText(personaje.getEnergia()+"");
                tvNivel_DP.setText(personaje.getNivel()+"");
                tvExp_DP.setText(personaje.getExperiencia()+"/"+personaje.getNextExp());

                tvAtk_DP.setText(personaje.getAtaque()+"");
                tvAtm_DP.setText(personaje.getAtkMagico()+"");
                tvDef_DP.setText(personaje.getDefensa()+"");
                tvDfm_DP.setText(personaje.getDefMagico()+"");
                tvDex_DP.setText(personaje.getAgilidad()+"");
                tvEva_DP.setText(personaje.getEvasion()+"%");
                tvCrt_DP.setText(personaje.getCritico()+"%");
                tvAcc_DP.setText(personaje.getPrecision()+"%");

                tvDescripcion_DP.setText(personaje.getDescripcion());
            }
        });

        inicializarVista(root);
        detallePersonajeVM.getPersonaje();

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DP = v.findViewById(R.id.tvNombre_DP);
        this.tvRaza_DP = v.findViewById(R.id.tvRaza_DP);
        this.tvClase_DP = v.findViewById(R.id.tvClase_DP);
        this.tvVida_DP = v.findViewById(R.id.tvVida_DP);
        this.tvEnergia_DP = v.findViewById(R.id.tvEnergia_DP);
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
        this.tvDescripcion_DP = v.findViewById(R.id.tvDescripcion_DP);

        this.btJugar_DP = v.findViewById(R.id.btJugar_DP);
        this.btJugar_DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bPersonaje = new Bundle();
                bPersonaje.putSerializable("jugar", personaje);
                Navigation.findNavController(view).navigate(R.id.jugarFragment, bPersonaje);
            }
        });

        this.btBorrar_DP = v.findViewById(R.id.btBorrar_DP);
        this.btBorrar_DP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar personaje")
                        .setMessage("¿Seguro que quiere borrar su personaje " + tvNombre_DP.getText().toString() + "?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                detallePersonajeVM.borrarPersonaje(personaje.getIdPersonaje(), view);
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