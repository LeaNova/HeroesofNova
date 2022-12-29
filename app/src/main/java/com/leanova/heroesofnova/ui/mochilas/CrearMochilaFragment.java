package com.leanova.heroesofnova.ui.mochilas;

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
import com.leanova.heroesofnova.databinding.FragmentCrearMochilaBinding;

public class CrearMochilaFragment extends Fragment {
    private FragmentCrearMochilaBinding binding;
    private CrearMochilaViewModel cmvm;

    private EditText etNombre_CM, etDescripcion_CM, etPesoMax_CM;
    private TextView tvAviso_CM;
    private Button btCrear_CM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearMochilaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cmvm = new ViewModelProvider(this).get(CrearMochilaViewModel.class);

        inicializarVista(root);

        cmvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_CM.setText(s);
            }
        });
        cmvm.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CM.setText(s);
                etDescripcion_CM.setText(s);
                etPesoMax_CM.setText(s);
                tvAviso_CM.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_CM = v.findViewById(R.id.etNombre_CM);
        this.etDescripcion_CM = v.findViewById(R.id.etDescripcion_CM);
        this.etPesoMax_CM = v.findViewById(R.id.etPesoMax_CM);
        this.tvAviso_CM = v.findViewById(R.id.tvAviso_CM);

        this.btCrear_CM = v.findViewById(R.id.btCrear_CM);
        this.btCrear_CM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = etNombre_CM.getText().toString();
                    String descripcion = etDescripcion_CM.getText().toString();

                    int pesoMax = Integer.parseInt(etPesoMax_CM.getText().toString());

                    cmvm.crearMochila(nombre, descripcion, pesoMax);
                } catch (NumberFormatException ex) {
                    cmvm.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}