package com.leanova.heroesofnova.ui.personajes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentCrearPersonajeBinding;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;

public class CrearPersonajeFragment extends Fragment {
    private FragmentCrearPersonajeBinding binding;
    private CrearPersonajeViewModel cpvm;

    private Spinner spGenero_CP, spRaza_CP, spClase_CP;
    private TextView textVidaBase, textEnergiaBase, textAtkBase, textAtmBase, textDefBase, textDfmBase, textDexBase, textEvaBase, textCrtBase, textAccBase;
    private EditText etNombre_CP, etVida_CP1, etVida_CP2, etVida_CP3, etEnergia_CP1, etEnergia_CP2, etEnergia_CP3, etAtk_CP, etAtm_CP, etDef_CP, etDfm_CP, etDex_CP, etEva_CP, etCrt_CP, etAcc_CP, etDescripcion_CP;
    private Button btTirar_CP, btGuardar_CP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearPersonajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cpvm = new ViewModelProvider(this).get(CrearPersonajeViewModel.class);

        inicializarVista(root);

        cpvm.getMutableRaza().observe(getViewLifecycleOwner(), new Observer<Raza>() {
            @Override
            public void onChanged(Raza raza) {
                textVidaBase.setText(raza.getVidaBase() + " + ");
                textEnergiaBase.setText(raza.getEnergiaBase() + " + ");
                textAtkBase.setText(raza.getBaseAtk() + " + ");
                textAtmBase.setText(raza.getBaseAtm() + " + ");
                textDefBase.setText(raza.getBaseDef() + " + ");
                textDfmBase.setText(raza.getBaseDfm() + " + ");
                textDexBase.setText(raza.getBaseDex() + " + ");
                textEvaBase.setText(raza.getBaseEva() + " + ");
                textCrtBase.setText(raza.getBaseCrt() + " + ");
                textAccBase.setText(raza.getBaseAcc() + " + ");
            }
        });
        cpvm.getMutableTiros().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> tiros) {
                etVida_CP1.setText(tiros.get(0));
                etVida_CP2.setText(tiros.get(1));
                etVida_CP3.setText(tiros.get(2));
                etEnergia_CP1.setText(tiros.get(3));
                etEnergia_CP2.setText(tiros.get(4));
                etEnergia_CP3.setText(tiros.get(5));
                etAtk_CP.setText(tiros.get(6));
                etAtm_CP.setText(tiros.get(7));
                etDef_CP.setText(tiros.get(8));
                etDfm_CP.setText(tiros.get(9));
                etDex_CP.setText(tiros.get(10));
                etEva_CP.setText(tiros.get(11));
                etCrt_CP.setText(tiros.get(12));
                etAcc_CP.setText(tiros.get(13));
            }
        });
        cpvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Error en crear")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //NADA
                            }
                        }).show();
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CP = v.findViewById(R.id.etNombre_CP);

        this.spGenero_CP = v.findViewById(R.id.spGenero_CP);
        ArrayAdapter<Genero> spinnerGenero = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, PersonajeValues.getGeneros());
        this.spGenero_CP.setAdapter(spinnerGenero);

        this.spRaza_CP = v.findViewById(R.id.spRaza_CP);
        ArrayAdapter<Raza> spinnerRaza = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, PersonajeValues.getRazas());
        this.spRaza_CP.setAdapter(spinnerRaza);
        this.spRaza_CP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Raza r = (Raza) spRaza_CP.getSelectedItem();
                cpvm.setRaza(r);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nada
            }
        });

        this.spClase_CP = v.findViewById(R.id.spClase_CP);
        ArrayAdapter<Clase> spinnerClase = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, PersonajeValues.getClases());
        this.spClase_CP.setAdapter(spinnerClase);

        this.textVidaBase = v.findViewById(R.id.textVidaBase);
        this.etVida_CP1 = v.findViewById(R.id.etVida_CP1);
        this.etVida_CP2 = v.findViewById(R.id.etVida_CP2);
        this.etVida_CP3 = v.findViewById(R.id.etVida_CP3);

        this.textEnergiaBase = v.findViewById(R.id.textEnergiaBase);
        this.etEnergia_CP1 = v.findViewById(R.id.etEnergia_CP1);
        this.etEnergia_CP2 = v.findViewById(R.id.etEnergia_CP2);
        this.etEnergia_CP3 = v.findViewById(R.id.etEnergia_CP3);

        this.textAtkBase = v.findViewById(R.id.textAtkBase);
        this.etAtk_CP = v.findViewById(R.id.etAtk_CP);
        this.textAtmBase = v.findViewById(R.id.textAtmBase);
        this.etAtm_CP = v.findViewById(R.id.etAtm_CP);
        this.textDefBase = v.findViewById(R.id.textDefBase);
        this.etDef_CP = v.findViewById(R.id.etDef_CP);
        this.textDfmBase = v.findViewById(R.id.textDfmBase);
        this.etDfm_CP = v.findViewById(R.id.etDfm_CP);
        this.textDexBase = v.findViewById(R.id.textDexBase);
        this.etDex_CP = v.findViewById(R.id.etDex_CP);
        this.textEvaBase = v.findViewById(R.id.textEvaBase);
        this.etEva_CP = v.findViewById(R.id.etEva_CP);
        this.textCrtBase = v.findViewById(R.id.textCrtBase);
        this.etCrt_CP = v.findViewById(R.id.etCrt_CP);
        this.textAccBase = v.findViewById(R.id.textAccBase);
        this.etAcc_CP = v.findViewById(R.id.etAcc_CP);
        this.etDescripcion_CP = v.findViewById(R.id.etDescripcion_CP);

        this.btTirar_CP = v.findViewById(R.id.btTirar_CP);
        this.btTirar_CP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Raza raza = (Raza) spRaza_CP.getSelectedItem();
                cpvm.hacerTiros(raza);
            }
        });

        this.btGuardar_CP = v.findViewById(R.id.btGuardar_CP);
        this.btGuardar_CP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = etNombre_CP.getText().toString();

                    Genero genero = (Genero) spGenero_CP.getSelectedItem();
                    Raza raza = (Raza) spRaza_CP.getSelectedItem();
                    Clase clase = (Clase) spClase_CP.getSelectedItem();

                    int vida1 = Integer.parseInt(etVida_CP1.getText().toString());
                    int vida2 = Integer.parseInt(etVida_CP2.getText().toString());
                    int vida3 = Integer.parseInt(etVida_CP3.getText().toString());

                    int energia1 = Integer.parseInt(etEnergia_CP1.getText().toString());
                    int energia3 = Integer.parseInt(etEnergia_CP2.getText().toString());
                    int energia2 = Integer.parseInt(etEnergia_CP3.getText().toString());

                    int atk = Integer.parseInt(etAtk_CP.getText().toString());
                    int atm = Integer.parseInt(etAtm_CP.getText().toString());
                    int def = Integer.parseInt(etDef_CP.getText().toString());
                    int dfm = Integer.parseInt(etDfm_CP.getText().toString());
                    int dex = Integer.parseInt(etDex_CP.getText().toString());
                    int eva = Integer.parseInt(etEva_CP.getText().toString());
                    int crt = Integer.parseInt(etCrt_CP.getText().toString());
                    int acc = Integer.parseInt(etAcc_CP.getText().toString());

                    String descripcion = etDescripcion_CP.getText().toString();

                    cpvm.crearPersonaje(nombre, raza, genero, clase, vida1, vida2, vida3, energia1, energia2, energia3, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
                } catch (NumberFormatException ex) {
                    cpvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}