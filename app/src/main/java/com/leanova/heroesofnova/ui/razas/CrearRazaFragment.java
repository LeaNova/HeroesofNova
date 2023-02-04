package com.leanova.heroesofnova.ui.razas;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentCrearRazaBinding;
import com.leanova.heroesofnova.modelos.Raza;

public class CrearRazaFragment extends Fragment {
    private FragmentCrearRazaBinding binding;
    private CrearRazaViewModel crvm;

    private EditText etNombre_CR, etVida_CR, etEnergia_CR, etAtk_CR, etAtm_CR, etDef_CR, etDfm_CR, etDex_CR, etEva_CR, etCrt_CR, etAcc_CR, etDescripcion_CR;
    private Button btCrearActualizar_CR;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearRazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crvm = new ViewModelProvider(this).get(CrearRazaViewModel.class);
        crvm.getMutableRaza().observe(getViewLifecycleOwner(), new Observer<Raza>() {
            @Override
            public void onChanged(Raza raza) {
                etNombre_CR.setText(raza.getNombre());
                etVida_CR .setText(raza.getVidaBase()+"");
                etEnergia_CR.setText(raza.getEnergiaBase()+"");
                etAtk_CR.setText(raza.getBaseAtk()+"");
                etAtm_CR.setText(raza.getBaseAtm()+"");
                etDef_CR.setText(raza.getBaseDef()+"");
                etDfm_CR.setText(raza.getBaseDfm()+"");
                etDex_CR.setText(raza.getBaseDex()+"");
                etEva_CR.setText(raza.getBaseEva()+"");
                etCrt_CR.setText(raza.getBaseCrt()+"");
                etAcc_CR.setText(raza.getBaseAcc()+"");
                etDescripcion_CR.setText(raza.getDescripcion());

                btCrearActualizar_CR.setText("Actualizar");
            }
        });
        crvm.getRaza(getArguments());

        inicializarVista(root);
        crvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        crvm.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CR.setText(s);
                etDescripcion_CR.setText(s);
                etVida_CR .setText(s);
                etEnergia_CR.setText(s);
                etAtk_CR.setText(s);
                etAtm_CR.setText(s);
                etDef_CR.setText(s);
                etDfm_CR.setText(s);
                etDex_CR.setText(s);
                etEva_CR.setText(s);
                etCrt_CR.setText(s);
                etAcc_CR.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CR = v.findViewById(R.id.etNombre_CR);
        this.etVida_CR = v.findViewById(R.id.etVida_CR);
        this.etEnergia_CR = v.findViewById(R.id.etEnergia_CR);
        this.etAtk_CR = v.findViewById(R.id.etAtk_CR);
        this.etAtm_CR = v.findViewById(R.id.etAtm_CR);
        this.etDef_CR = v.findViewById(R.id.etDef_CR);
        this.etDfm_CR = v.findViewById(R.id.etDfm_CR);
        this.etDex_CR = v.findViewById(R.id.etDex_CR);
        this.etEva_CR = v.findViewById(R.id.etEva_CR);
        this.etCrt_CR = v.findViewById(R.id.etCrt_CR);
        this.etAcc_CR = v.findViewById(R.id.etAcc_CR);
        this.etDescripcion_CR = v.findViewById(R.id.etDescripcion_CR);

        this.btCrearActualizar_CR = v.findViewById(R.id.btCrearActualizar_CR);
        this.btCrearActualizar_CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String accion = btCrearActualizar_CR.getText().toString();
                    String nombre = etNombre_CR.getText().toString();
                    String descripcion = etDescripcion_CR.getText().toString();

                    int vida = Integer.parseInt(etVida_CR.getText().toString());
                    int energia = Integer.parseInt(etEnergia_CR.getText().toString());
                    int atk = Integer.parseInt(etAtk_CR.getText().toString());
                    int atm = Integer.parseInt(etAtm_CR.getText().toString());
                    int def = Integer.parseInt(etDef_CR.getText().toString());
                    int dfm = Integer.parseInt(etDfm_CR.getText().toString());
                    int dex = Integer.parseInt(etDex_CR.getText().toString());
                    int eva = Integer.parseInt(etEva_CR.getText().toString());
                    int crt = Integer.parseInt(etCrt_CR.getText().toString());
                    int acc = Integer.parseInt(etAcc_CR.getText().toString());

                    crvm.tomarAccion(accion, nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
                } catch (NumberFormatException ex) {
                    crvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}