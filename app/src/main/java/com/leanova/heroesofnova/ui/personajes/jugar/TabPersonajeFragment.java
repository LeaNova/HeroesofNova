package com.leanova.heroesofnova.ui.personajes.jugar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabPersonajeBinding;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;

public class TabPersonajeFragment extends Fragment {
    private FragmentTabPersonajeBinding binding;
    private TabPersonajeViewModel tabPersonajeVM;

    private TextView tvPersonaje_JP, tvVida_JP, tvVidaT_JP, tvEnergia_JP, tvEnergiaT_JP, tvExp_JP, tvExpT_JP;
    private ProgressBar pbVida_JP, pbEnergia_JP;
    private EditText etRecuperar_JP, etDanio_JP, etExp_JP;
    private RadioGroup rgRecuperar_JP, rgDanio_JP, rgAtaque_JP;
    private RadioButton rbOpcion;
    private Button btDanio_JP, btCurar_JP, btExp_JP, btFinalizar_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabPersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabPersonajeVM = new ViewModelProvider(this).get(TabPersonajeViewModel.class);
        tabPersonajeVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Jugar>() {
            @Override
            public void onChanged(Jugar jugar) {
                Personaje personaje = jugar.getPersonaje();
                tvPersonaje_JP.setText(personaje.getNombre() + " - Nivel " + personaje.getNivel());
                tvVida_JP.setText(personaje.getVidaAct()+"");
                tvVidaT_JP.setText(personaje.getVida()+"");
                tvEnergia_JP.setText(personaje.getEnergiaAct()+"");
                tvEnergiaT_JP.setText(personaje.getEnergia()+"");
                tvExp_JP.setText(personaje.getExperiencia()+"");
                tvExpT_JP.setText(personaje.getNextExp()+"");

                pbVida_JP.setMax(personaje.getVida());
                pbVida_JP.setProgress(personaje.getVidaAct());
                pbEnergia_JP.setMax(personaje.getEnergia());
                pbEnergia_JP.setProgress(personaje.getEnergiaAct());
            }
        });
        tabPersonajeVM.getMutableClean().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRecuperar_JP.setText(s);
                etDanio_JP.setText(s);
                etExp_JP.setText(s);
            }
        });

        inicializarVista(root);
        tabPersonajeVM.obtenerPersonaje();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabPersonajeVM.actualizar();
    }

    private void inicializarVista(View v) {
        this.tvPersonaje_JP = v.findViewById(R.id.tvPersonaje_JP);
        this.tvVida_JP = v.findViewById(R.id.tvVida_JP);
        this.tvVidaT_JP = v.findViewById(R.id.tvVidaT_JP);
        this.tvEnergia_JP = v.findViewById(R.id.tvEnergia_JP);
        this.tvEnergiaT_JP = v.findViewById(R.id.tvEnergiaT_JP);
        this.tvExp_JP = v.findViewById(R.id.tvExp_JP);
        this.tvExpT_JP = v.findViewById(R.id.tvExpT_JP);
        this.pbVida_JP = v.findViewById(R.id.pbVida_JP);
        this.pbEnergia_JP = v.findViewById(R.id.pbEnergia_JP);

        this.etRecuperar_JP = v.findViewById(R.id.etRecuperar_JP);
        this.rgRecuperar_JP = v.findViewById(R.id.rgRecuperar_JP);
        this.btCurar_JP = v.findViewById(R.id.btCurar_JP);
        this.btCurar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int total = Integer.parseInt(etRecuperar_JP.getText().toString());

                    int rbOpcionID = rgRecuperar_JP.getCheckedRadioButtonId();
                    rbOpcion = v.findViewById(rbOpcionID);
                    String opcion = rbOpcion.getText().toString();

                    tabPersonajeVM.recuperar(total, opcion);
                } catch (NumberFormatException ex) {
                    //Nada
                }
            }
        });

        this.rgDanio_JP = v.findViewById(R.id.rgDanio_JP);
        this.etDanio_JP = v.findViewById(R.id.etDanio_JP);
        this.btDanio_JP = v.findViewById(R.id.btDanio_JP);
        this.btDanio_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int danio = Integer.parseInt(etDanio_JP.getText().toString());

                    int rbOpcionID = rgDanio_JP.getCheckedRadioButtonId();
                    rbOpcion = v.findViewById(rbOpcionID);
                    String opcion = rbOpcion.getText().toString();

                    tabPersonajeVM.recibirDanio(danio, opcion);
                } catch (NumberFormatException ex) {
                    //Nada
                }
            }
        });

        this.etExp_JP = v.findViewById(R.id.etExp_JP);
        this.btExp_JP = v.findViewById(R.id.btExp_JP);
        this.btExp_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int exp = Integer.parseInt(etExp_JP.getText().toString());
                    tabPersonajeVM.getExp(exp);
                } catch (NumberFormatException ex) {
                    //Nada
                }
            }
        });

        this.btFinalizar_JP = v.findViewById(R.id.btFinalizar_JP);
        this.btFinalizar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabPersonajeVM.finalizar(view);
            }
        });
    }
}