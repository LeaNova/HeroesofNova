package com.leanova.heroesofnova.ui.personajes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.leanova.heroesofnova.databinding.FragmentJugarBinding;
import com.leanova.heroesofnova.modelos.Personaje;

public class JugarFragment extends Fragment {
    private FragmentJugarBinding binding;
    private JugarViewModel jvm;

    private TextView tvPersonaje_JP, tvVida_JP, tvVidaT_JP, tvEnergia_JP, tvEnergiaT_JP, tvExp_JP, tvExpT_JP;
    private EditText etRecuperar_JP, etDanio_JP, etExp_JP;
    private Button btDanio_JP, btCurar_JP, btExp_JP;
    private RadioGroup rgRecuperar_JP, rgDanio_JP, rgAtaque_JP;
    private RadioButton rbOpcion;
    private ProgressBar pbVida_JP, pbEnergia_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentJugarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jvm = new ViewModelProvider(this).get(JugarViewModel.class);
        jvm.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje personaje) {
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
        jvm.getMutableClean().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etDanio_JP.setText(s);
                etExp_JP.setText(s);
            }
        });

        inicializarVista(root);

        jvm.obtenerPersonaje(getArguments());

        return root;
    }

    private void inicializarVista(View v) {
        this.tvPersonaje_JP = v.findViewById(R.id.tvPersonaje_JP);
        this.pbVida_JP = v.findViewById(R.id.pbVida_JP);
        this.tvVida_JP = v.findViewById(R.id.tvVida_JP);
        this.tvVidaT_JP = v.findViewById(R.id.tvVidaT_JP);
        this.pbEnergia_JP = v.findViewById(R.id.pbEnergia_JP);
        this.tvEnergia_JP = v.findViewById(R.id.tvEnergia_JP);
        this.tvEnergiaT_JP = v.findViewById(R.id.tvEnergiaT_JP);
        this.tvExp_JP = v.findViewById(R.id.tvExp_JP);
        this.tvExpT_JP = v.findViewById(R.id.tvExpT_JP);

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

                    jvm.recuperar(total, opcion);
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

                    jvm.recibirDanio(danio, opcion);
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
                    jvm.getExp(exp);
                } catch (NumberFormatException ex) {
                    //Nada
                }
            }
        });

    }
}