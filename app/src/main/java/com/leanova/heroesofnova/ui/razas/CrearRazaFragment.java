package com.leanova.heroesofnova.ui.razas;

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
import com.leanova.heroesofnova.databinding.FragmentCrearRazaBinding;

public class CrearRazaFragment extends Fragment {
    private FragmentCrearRazaBinding binding;
    private CrearRazaViewModel crvm;

    private EditText etNombre_CR, etDescripcion_CR, etVida_CR, etAtk_CR, etAtm_CR, etDef_CR, etDfm_CR, etDex_CR, etEva_CR, etCrt_CR, etAcc_CR;
    private TextView tvAviso_CR;
    private Button btCrear_CR;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearRazaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crvm = new ViewModelProvider(this).get(CrearRazaViewModel.class);

        inicializarVista(root);
        crvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_CR.setText(s);
            }
        });
        crvm.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CR.setText(s);
                etDescripcion_CR.setText(s);
                etVida_CR .setText(s);
                etAtk_CR.setText(s);
                etAtm_CR.setText(s);
                etDef_CR.setText(s);
                etDfm_CR.setText(s);
                etDex_CR.setText(s);
                etEva_CR.setText(s);
                etCrt_CR.setText(s);
                etAcc_CR.setText(s);
                tvAviso_CR.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CR = v.findViewById(R.id.etNombre_CR);
        this.etDescripcion_CR = v.findViewById(R.id.etDescripcion_CR);
        this.etVida_CR = v.findViewById(R.id.etVida_CR);
        this.etAtk_CR = v.findViewById(R.id.etAtk_CR);
        this.etAtm_CR = v.findViewById(R.id.etAtm_CR);
        this.etDef_CR = v.findViewById(R.id.etDef_CR);
        this.etDfm_CR = v.findViewById(R.id.etDfm_CR);
        this.etDex_CR = v.findViewById(R.id.etDex_CR);
        this.etEva_CR = v.findViewById(R.id.etEva_CR);
        this.etCrt_CR = v.findViewById(R.id.etCrt_CR);
        this.etAcc_CR = v.findViewById(R.id.etAcc_CR);
        this.tvAviso_CR = v.findViewById(R.id.tvAviso_CR);

        this.btCrear_CR = v.findViewById(R.id.btCrear_CR);
        this.btCrear_CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = etNombre_CR.getText().toString();
                    String descripcion = etDescripcion_CR.getText().toString();

                    int vida = Integer.parseInt(etVida_CR.getText().toString());
                    int atk = Integer.parseInt(etAtk_CR.getText().toString());
                    int atm = Integer.parseInt(etAtm_CR.getText().toString());
                    int def = Integer.parseInt(etDef_CR.getText().toString());
                    int dfm = Integer.parseInt(etDfm_CR.getText().toString());
                    int dex = Integer.parseInt(etDex_CR.getText().toString());
                    int eva = Integer.parseInt(etEva_CR.getText().toString());
                    int crt = Integer.parseInt(etCrt_CR.getText().toString());
                    int acc = Integer.parseInt(etAcc_CR.getText().toString());

                    crvm.crearRaza(nombre, descripcion, vida, atk, atm, def, dfm, dex, eva, crt, acc);
                } catch (NumberFormatException ex) {
                    crvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}