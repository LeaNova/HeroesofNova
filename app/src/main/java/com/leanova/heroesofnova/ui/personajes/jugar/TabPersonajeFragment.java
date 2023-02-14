package com.leanova.heroesofnova.ui.personajes.jugar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabPersonajeBinding;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.modelos.Personaje;

import java.util.Timer;
import java.util.TimerTask;

public class TabPersonajeFragment extends Fragment {
    private FragmentTabPersonajeBinding binding;
    private TabPersonajeViewModel tabPersonajeVM;

    private Personaje personaje;
    private Arma arma;

    private TextView tvPersonaje_JP, tvVida_JP, tvVidaMax_JP, tvEnergia_JP, tvEnergiaMax_JP, tvExp_JP, tvExpT_JP, tvArma_JP, tvResultado_JP;
    private ProgressBar pbVida_JP, pbEnergia_JP;
    private ImageView ivTip_JP;
    private EditText etRecuperar_JP, etDanio_JP, etExp_JP, etAtaque_JP;
    private RadioGroup rgRecuperar_JP, rgDanio_JP, rgAtaque_JP;
    private RadioButton rbOpcion;
    private Button btDanio_JP, btExp_JP, btFinalizar_JP, btAtaque_JP; //btCurar_JP
    private ImageButton btCurar_JP, btTirar_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabPersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabPersonajeVM = new ViewModelProvider(this).get(TabPersonajeViewModel.class);
        tabPersonajeVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Jugar>() {
            @Override
            public void onChanged(Jugar jugar) {
                personaje = jugar.getPersonaje();
                tvPersonaje_JP.setText(personaje.getNombre() + " - Nivel " + personaje.getNivel());
                tvVida_JP.setText(personaje.getVidaAct()+"");
                tvVidaMax_JP.setText(jugar.getVida()+"");
                tvEnergia_JP.setText(personaje.getEnergiaAct()+"");
                tvEnergiaMax_JP.setText(jugar.getEnergia()+"");
                tvExp_JP.setText(personaje.getExperiencia()+"");
                tvExpT_JP.setText(personaje.getNextExp()+"");

                pbVida_JP.setMax(jugar.getVida());
                pbVida_JP.setProgress(personaje.getVidaAct());
                pbEnergia_JP.setMax(jugar.getEnergia());
                pbEnergia_JP.setProgress(personaje.getEnergiaAct());

                arma = jugar.getArma();
                tvArma_JP.setText(arma.getNombre());
            }
        });

        inicializarVista(root);
        tabPersonajeVM.obtenerPersonaje();
        tabPersonajeVM.getMutableTip().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(getContext())
                        .setTitle("TIP: Ataque")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //NADA
                            }
                        }).show();
            }
        });
        tabPersonajeVM.getMutableDado().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                etAtaque_JP.setText("0, " + integer + ", no");
            }
        });
        tabPersonajeVM.getMutableResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvResultado_JP.setText(s);
            }
        });
        tabPersonajeVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Error en formato")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //NADA
                            }
                        }).show();
            }
        });
        tabPersonajeVM.getMutableClean().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRecuperar_JP.setText(s);
                etDanio_JP.setText(s);
                etExp_JP.setText(s);
                etDanio_JP.setText(s);
            }
        });

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
        this.tvVidaMax_JP = v.findViewById(R.id.tvVidaMax_JP);
        this.tvEnergia_JP = v.findViewById(R.id.tvEnergia_JP);
        this.tvEnergiaMax_JP = v.findViewById(R.id.tvEnergiaMax_JP);
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
                } catch (Exception ignored) { }
            }
        });

        this.etDanio_JP = v.findViewById(R.id.etDanio_JP);
        this.rgDanio_JP = v.findViewById(R.id.rgDanio_JP);
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
                } catch (Exception ignored) { }
            }
        });

        this.etExp_JP = v.findViewById(R.id.etExp_JP);
        this.btExp_JP = v.findViewById(R.id.btExp_JP);
        this.btExp_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int exp = Integer.parseInt(etExp_JP.getText().toString());
                    tabPersonajeVM.getExp(exp, personaje.getNextExp() - personaje.getExperiencia());
                } catch (Exception ignored) { }
            }
        });

        this.tvArma_JP = v.findViewById(R.id.tvArma_JP);
        this.ivTip_JP = v.findViewById(R.id.ivTip_JP);
        this.ivTip_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabPersonajeVM.getTip();
            }
        });
        this.etAtaque_JP = v.findViewById(R.id.etAtaque_JP);
        this.rgAtaque_JP = v.findViewById(R.id.rgAtaque_JP);
        this.btAtaque_JP = v.findViewById(R.id.btAtaque_JP);
        this.btAtaque_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ataque = etAtaque_JP.getText().toString();

                int rbOpcionID = rgAtaque_JP.getCheckedRadioButtonId();
                rbOpcion = v.findViewById(rbOpcionID);
                String opcion = rbOpcion.getText().toString();

                tabPersonajeVM.hacerAtaque(ataque, opcion);
            }
        });
        this.btTirar_JP = v.findViewById(R.id.btTirar_JP);
        this.btTirar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabPersonajeVM.hacerTiro(arma.getDanioArma());
            }
        });
        this.tvResultado_JP = v.findViewById(R.id.tvResultado_JP);
        this.tvResultado_JP.setText("");

        this.btFinalizar_JP = v.findViewById(R.id.btFinalizar_JP);
        this.btFinalizar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabPersonajeVM.finalizar(view);
            }
        });
    }
}