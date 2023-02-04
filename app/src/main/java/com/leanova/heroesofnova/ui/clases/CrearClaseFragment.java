package com.leanova.heroesofnova.ui.clases;

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
import com.leanova.heroesofnova.databinding.FragmentCrearClaseBinding;
import com.leanova.heroesofnova.modelos.Clase;

public class CrearClaseFragment extends Fragment {
    private FragmentCrearClaseBinding binding;
    private CrearClaseViewModel ccvm;

    private EditText etNombre_CC, etVida_CC, etEnergia_CC, etAtk_CC, etAtm_CC, etDef_CC, etDfm_CC, etDex_CC, etEva_CC, etCrt_CC, etAcc_CC, etDescripcion_CC;
    private Button btCrearActualizar_CC;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearClaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ccvm = new ViewModelProvider(this).get(CrearClaseViewModel.class);
        ccvm.getMutableClase().observe(getViewLifecycleOwner(), new Observer<Clase>() {
            @Override
            public void onChanged(Clase clase) {
                etNombre_CC.setText(clase.getNombre());
                etVida_CC .setText(clase.getModVida()+"");
                etEnergia_CC.setText(clase.getModEnergia()+"");
                etAtk_CC.setText(clase.getModAtk()+"");
                etAtm_CC.setText(clase.getModAtm()+"");
                etDef_CC.setText(clase.getModDef()+"");
                etDfm_CC.setText(clase.getModDfm()+"");
                etDex_CC.setText(clase.getModDex()+"");
                etEva_CC.setText(clase.getModEva()+"");
                etCrt_CC.setText(clase.getModCrt()+"");
                etAcc_CC.setText(clase.getModAcc()+"");
                etDescripcion_CC.setText(clase.getDescripcion());

                btCrearActualizar_CC.setText("Actualizar");
            }
        });
        ccvm.getClase(getArguments());

        inicializarVista(root);
        ccvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        ccvm.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CC.setText(s);
                etVida_CC .setText(s);
                etEnergia_CC.setText(s);
                etAtk_CC.setText(s);
                etAtm_CC.setText(s);
                etDef_CC.setText(s);
                etDfm_CC.setText(s);
                etDex_CC.setText(s);
                etEva_CC.setText(s);
                etCrt_CC.setText(s);
                etAcc_CC.setText(s);
                etDescripcion_CC.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CC = v.findViewById(R.id.etNombre_CC);
        this.etVida_CC = v.findViewById(R.id.etVida_CC);
        this.etEnergia_CC = v.findViewById(R.id.etEnergia_CC);
        this.etAtk_CC = v.findViewById(R.id.etAtk_CC);
        this.etAtm_CC = v.findViewById(R.id.etAtm_CC);
        this.etDef_CC = v.findViewById(R.id.etDef_CC);
        this.etDfm_CC = v.findViewById(R.id.etDfm_CC);
        this.etDex_CC = v.findViewById(R.id.etDex_CC);
        this.etEva_CC = v.findViewById(R.id.etEva_CC);
        this.etCrt_CC = v.findViewById(R.id.etCrt_CC);
        this.etAcc_CC = v.findViewById(R.id.etAcc_CC);
        this.etDescripcion_CC = v.findViewById(R.id.etDescripcion_CC);

        this.btCrearActualizar_CC = v.findViewById(R.id.btCrearActualizar_CC);
        this.btCrearActualizar_CC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String accion = btCrearActualizar_CC.getText().toString();
                    String nombre = etNombre_CC.getText().toString();
                    String descripcion = etDescripcion_CC.getText().toString();

                    float vida = Float.parseFloat(etVida_CC.getText().toString());
                    float energia = Float.parseFloat(etEnergia_CC.getText().toString());
                    float atk = Float.parseFloat(etAtk_CC.getText().toString());
                    float atm = Float.parseFloat(etAtm_CC.getText().toString());
                    float def = Float.parseFloat(etDef_CC.getText().toString());
                    float dfm = Float.parseFloat(etDfm_CC.getText().toString());
                    float dex = Float.parseFloat(etDex_CC.getText().toString());
                    float eva = Float.parseFloat(etEva_CC.getText().toString());
                    float crt = Float.parseFloat(etCrt_CC.getText().toString());
                    float acc = Float.parseFloat(etAcc_CC.getText().toString());

                    ccvm.tomarAccion(accion, nombre, vida, energia, atk, atm, def, dfm, dex, eva, crt, acc, descripcion);
                } catch (NumberFormatException ex) {
                    ccvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}