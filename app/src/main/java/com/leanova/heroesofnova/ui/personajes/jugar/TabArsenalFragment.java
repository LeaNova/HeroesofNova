package com.leanova.heroesofnova.ui.personajes.jugar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentTabArsenalBinding;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Jugar;

import java.util.ArrayList;

public class TabArsenalFragment extends Fragment {
    private FragmentTabArsenalBinding binding;
    private TabArsenalViewModel tabArsenalVM;

    private TextView tvPersonaje_JP, tvVida_JP, tvEnergia_JP, tvAtk_JP, tvAtm_JP, tvDef_JP, tvDfm_JP, tvDex_JP, tvEva_JP, tvCrt_JP, tvAcc_JP, tvArmadura_JP, tvArma_JP;
    private TextView tvVidaAdd_JP, tvEnergiaAdd_JP, tvAtkAdd_JP, tvAtmAdd_JP, tvDefAdd_JP, tvDfmAdd_JP, tvDexAdd_JP, tvEvaAdd_JP, tvCrtAdd_JP, tvAccAdd_JP, tvCorona_JP, tvIzquierda_JP, tvDerecha_JP, tvAdorno_JP;
    private Spinner spArmaduras_JP, spArmas_JP, spCoronas_JP, spIzquierda_JP, spDerechas_JP, spAdornos_JP;
    private Button btEquipar_JP, btLimpiar_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTabArsenalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabArsenalVM = new ViewModelProvider(this).get(TabArsenalViewModel.class);
        tabArsenalVM.getMutablePersonaje().observe(getViewLifecycleOwner(), new Observer<Jugar>() {
            @Override
            public void onChanged(Jugar jugar) {
                tvPersonaje_JP.setText(jugar.getPersonaje().getNombre() + " - Nivel " + jugar.getPersonaje().getNivel());
                tvVida_JP.setText(jugar.getVida()+"");
                tvEnergia_JP.setText(jugar.getEnergia()+"");
                tvAtk_JP.setText(jugar.getAtaque()+"");
                tvAtm_JP.setText(jugar.getAtkMagico()+"");
                tvDef_JP.setText(jugar.getDefensa()+"");
                tvDfm_JP.setText(jugar.getDefMagico()+"");
                tvDex_JP.setText(jugar.getAgilidad()+"");
                tvEva_JP.setText(jugar.getEvasion()+"%");
                tvCrt_JP.setText(jugar.getCritico()+"%");
                tvAcc_JP.setText(jugar.getPrecision()+"%");
                tvArmadura_JP.setText(jugar.getArmadura().getNombre());
                tvArma_JP.setText(jugar.getArma().getNombre());
                tvCorona_JP.setText(jugar.getCorona().getNombre());
                tvIzquierda_JP.setText(jugar.getIzquierda().getNombre());
                tvDerecha_JP.setText(jugar.getDerecha().getNombre());
                tvAdorno_JP.setText(jugar.getAdorno().getNombre());
            }
        });
        tabArsenalVM.getMutableAdd().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                tvVidaAdd_JP.setText(strings.get(0));
                tvEnergiaAdd_JP.setText(strings.get(1));
                tvAtkAdd_JP.setText(strings.get(2));
                tvAtmAdd_JP.setText(strings.get(3));
                tvDefAdd_JP.setText(strings.get(4));
                tvDfmAdd_JP.setText(strings.get(5));
                tvDexAdd_JP.setText(strings.get(6));
                tvEvaAdd_JP.setText(strings.get(7));
                tvCrtAdd_JP.setText(strings.get(8));
                tvAccAdd_JP.setText(strings.get(9));
            }
        });
        tabArsenalVM.getMutableCambio().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                tvAtk_JP.setTextColor(Color.parseColor(strings.get(0)));
                tvAtm_JP.setTextColor(Color.parseColor(strings.get(1)));
                tvDef_JP.setTextColor(Color.parseColor(strings.get(2)));
                tvDfm_JP.setTextColor(Color.parseColor(strings.get(3)));
                tvDex_JP.setTextColor(Color.parseColor(strings.get(4)));
                tvEva_JP.setTextColor(Color.parseColor(strings.get(5)));
                tvCrt_JP.setTextColor(Color.parseColor(strings.get(6)));
                tvAcc_JP.setTextColor(Color.parseColor(strings.get(7)));
            }
        });
        tabArsenalVM.getMutableColor().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                tvVidaAdd_JP.setTextColor(Color.parseColor(strings.get(0)));
                tvEnergiaAdd_JP.setTextColor(Color.parseColor(strings.get(1)));
                tvAtkAdd_JP.setTextColor(Color.parseColor(strings.get(2)));
                tvAtmAdd_JP.setTextColor(Color.parseColor(strings.get(3)));
                tvDefAdd_JP.setTextColor(Color.parseColor(strings.get(4)));
                tvDfmAdd_JP.setTextColor(Color.parseColor(strings.get(5)));
                tvDexAdd_JP.setTextColor(Color.parseColor(strings.get(6)));
                tvEvaAdd_JP.setTextColor(Color.parseColor(strings.get(7)));
                tvCrtAdd_JP.setTextColor(Color.parseColor(strings.get(8)));
                tvAccAdd_JP.setTextColor(Color.parseColor(strings.get(9)));
            }
        });
        tabArsenalVM.getMutableArmaduras().observe(getViewLifecycleOwner(), new Observer<ArrayList<Armadura>>() {
            @Override
            public void onChanged(ArrayList<Armadura> armaduras) {
                ArrayAdapter<Armadura> adapterArmadura = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, armaduras);
                spArmaduras_JP.setAdapter(adapterArmadura);
            }
        });
        tabArsenalVM.getMutableArmas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Arma>>() {
            @Override
            public void onChanged(ArrayList<Arma> armas) {
                ArrayAdapter<Arma> adapterArma = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, armas);
                spArmas_JP.setAdapter(adapterArma);
            }
        });
        tabArsenalVM.getMutableCoronas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArrayAdapter<Artefacto> adapterArtefacto = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, artefactos);
                spCoronas_JP.setAdapter(adapterArtefacto);
            }
        });
        tabArsenalVM.getMutableIzquierdas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArrayAdapter<Artefacto> adapterArtefacto = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, artefactos);
                spIzquierda_JP.setAdapter(adapterArtefacto);
            }
        });
        tabArsenalVM.getMutableDerechas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArrayAdapter<Artefacto> adapterArtefacto = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, artefactos);
                spDerechas_JP.setAdapter(adapterArtefacto);
            }
        });
        tabArsenalVM.getMutableAdornos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArrayAdapter<Artefacto> adapterArtefacto = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, artefactos);
                spAdornos_JP.setAdapter(adapterArtefacto);
            }
        });
        inicializarVista(root);
        tabArsenalVM.obtenerPersonaje();
        tabArsenalVM.obtenerTodo();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabArsenalVM.actualizar();
    }

    private void inicializarVista(View v) {
        this.tvPersonaje_JP = v.findViewById(R.id.tvPersonaje_JP);
        this.tvVida_JP = v.findViewById(R.id.tvVida_JP);
        this.tvEnergia_JP = v.findViewById(R.id.tvEnergia_JP);
        this.tvAtk_JP = v.findViewById(R.id.tvAtk_JP);
        this.tvAtm_JP = v.findViewById(R.id.tvAtm_JP);
        this.tvDef_JP = v.findViewById(R.id.tvDef_JP);
        this.tvDfm_JP = v.findViewById(R.id.tvDfm_JP);
        this.tvDex_JP = v.findViewById(R.id.tvDex_JP);
        this.tvEva_JP = v.findViewById(R.id.tvEva_JP);
        this.tvCrt_JP = v.findViewById(R.id.tvCrt_JP);
        this.tvAcc_JP = v.findViewById(R.id.tvAcc_JP);
        this.tvVidaAdd_JP = v.findViewById(R.id.tvVidaAdd_JP);
        this.tvEnergiaAdd_JP = v.findViewById(R.id.tvEnergiaAdd_JP);
        this.tvAtkAdd_JP = v.findViewById(R.id.tvAtkAdd_JP);
        this.tvAtmAdd_JP = v.findViewById(R.id.tvAtmAdd_JP);
        this.tvDefAdd_JP = v.findViewById(R.id.tvDefAdd_JP);
        this.tvDfmAdd_JP = v.findViewById(R.id.tvDfmAdd_JP);
        this.tvDexAdd_JP = v.findViewById(R.id.tvDexAdd_JP);
        this.tvEvaAdd_JP = v.findViewById(R.id.tvEvaAdd_JP);
        this.tvCrtAdd_JP = v.findViewById(R.id.tvCrtAdd_JP);
        this.tvAccAdd_JP = v.findViewById(R.id.tvAccAdd_JP);
        this.tvArmadura_JP = v.findViewById(R.id.tvArmadura_JP);
        this.tvArma_JP = v.findViewById(R.id.tvArma_JP);
        this.tvCorona_JP = v.findViewById(R.id.tvCorona_JP);
        this.tvIzquierda_JP = v.findViewById(R.id.tvIzquierda_JP);
        this.tvDerecha_JP = v.findViewById(R.id.tvDerecha_JP);
        this.tvAdorno_JP = v.findViewById(R.id.tvAdorno_JP);

        this.spArmaduras_JP = v.findViewById(R.id.spArmaduras_JP);
        spArmaduras_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Armadura armadura = (Armadura) spArmaduras_JP.getSelectedItem();
                tabArsenalVM.setArmadura(armadura);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        this.spArmas_JP = v.findViewById(R.id.spArmas_JP);
        spArmas_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Arma arma = (Arma) spArmas_JP.getSelectedItem();
                tabArsenalVM.setArma(arma);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        this.spCoronas_JP = v.findViewById(R.id.spCoronas_JP);
        spCoronas_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto artefacto = (Artefacto) spCoronas_JP.getSelectedItem();
                tabArsenalVM.setCorona(artefacto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        this.spIzquierda_JP = v.findViewById(R.id.spIzquierda_JP);
        spIzquierda_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto artefacto = (Artefacto) spIzquierda_JP.getSelectedItem();
                tabArsenalVM.setIzquierda(artefacto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        this.spDerechas_JP = v.findViewById(R.id.spDerechas_JP);
        spDerechas_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto artefacto = (Artefacto) spDerechas_JP.getSelectedItem();
                tabArsenalVM.setDerecha(artefacto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        this.spAdornos_JP = v.findViewById(R.id.spAdornos_JP);
        spAdornos_JP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto artefacto = (Artefacto) spAdornos_JP.getSelectedItem();
                tabArsenalVM.setAdorno(artefacto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        this.btEquipar_JP = v.findViewById(R.id.btEquipar_JP);
        this.btEquipar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Armadura armadura = (Armadura) spArmaduras_JP.getSelectedItem();
                    Arma arma = (Arma) spArmas_JP.getSelectedItem();
                    Artefacto corona = (Artefacto) spCoronas_JP.getSelectedItem();
                    Artefacto izquierda = (Artefacto) spIzquierda_JP.getSelectedItem();
                    Artefacto derecha = (Artefacto) spDerechas_JP.getSelectedItem();
                    Artefacto adorno = (Artefacto) spAdornos_JP.getSelectedItem();

                    tabArsenalVM.equipar(armadura, arma, corona, izquierda, derecha, adorno);
                } catch (Exception ex) {
                    Log.d("error", ex.getMessage());
                }
            }
        });

        this.btLimpiar_JP = v.findViewById(R.id.btLimpiar_JP);
        this.btLimpiar_JP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabArsenalVM.terminarEfecto();
            }
        });
    }
}