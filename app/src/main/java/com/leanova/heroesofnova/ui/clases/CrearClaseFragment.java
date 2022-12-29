package com.leanova.heroesofnova.ui.clases;

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
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentCrearClaseBinding;

public class CrearClaseFragment extends Fragment {
    private FragmentCrearClaseBinding binding;
    private CrearClaseViewModel ccvm;

    private EditText etNombre_CC, etDescripcion_CC, etVida_CC, etAtk_CC, etAtm_CC, etDef_CC, etDfm_CC, etDex_CC, etEva_CC, etCrt_CC, etAcc_CC;
    private TextView tvAviso_CC;
    private Button btCrear_CC;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearClaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ccvm = new ViewModelProvider(this).get(CrearClaseViewModel.class);

        inicializarVista(root);

        ccvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_CC.setText(s);
            }
        });
        ccvm.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CC.setText(s);
                etDescripcion_CC.setText(s);
                etVida_CC .setText(s);
                etAtk_CC.setText(s);
                etAtm_CC.setText(s);
                etDef_CC.setText(s);
                etDfm_CC.setText(s);
                etDex_CC.setText(s);
                etEva_CC.setText(s);
                etCrt_CC.setText(s);
                etAcc_CC.setText(s);
                tvAviso_CC.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CC = v.findViewById(R.id.etNombre_CC);
        this.etDescripcion_CC = v.findViewById(R.id.etDescripcion_CC);
        this.etVida_CC = v.findViewById(R.id.etVida_CC);
        this.etAtk_CC = v.findViewById(R.id.etAtk_CC);
        this.etAtm_CC = v.findViewById(R.id.etAtm_CC);
        this.etDef_CC = v.findViewById(R.id.etDef_CC);
        this.etDfm_CC = v.findViewById(R.id.etDfm_CC);
        this.etDex_CC = v.findViewById(R.id.etDex_CC);
        this.etEva_CC = v.findViewById(R.id.etEva_CC);
        this.etCrt_CC = v.findViewById(R.id.etCrt_CC);
        this.etAcc_CC = v.findViewById(R.id.etAcc_CC);
        this.tvAviso_CC = v.findViewById(R.id.tvAviso_CC);

        this.btCrear_CC = v.findViewById(R.id.btCrear_CC);
        this.btCrear_CC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = etNombre_CC.getText().toString();
                    String descripcion = etDescripcion_CC.getText().toString();

                    float vida = Float.parseFloat(etVida_CC.getText().toString());
                    float atk = Float.parseFloat(etAtk_CC.getText().toString());
                    float atm = Float.parseFloat(etAtm_CC.getText().toString());
                    float def = Float.parseFloat(etDef_CC.getText().toString());
                    float dfm = Float.parseFloat(etDfm_CC.getText().toString());
                    float dex = Float.parseFloat(etDex_CC.getText().toString());
                    float eva = Float.parseFloat(etEva_CC.getText().toString());
                    float crt = Float.parseFloat(etCrt_CC.getText().toString());
                    float acc = Float.parseFloat(etAcc_CC.getText().toString());

                    ccvm.crearClase(nombre, descripcion, vida, atk, atm, def, dfm, dex, eva, crt, acc);
                } catch (NumberFormatException ex) {
                    ccvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}