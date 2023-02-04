package com.leanova.heroesofnova.ui.armas;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentCrearArmaBinding;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Categoria;

import java.util.ArrayList;

public class CrearArmaFragment extends Fragment {
    private FragmentCrearArmaBinding binding;
    private CrearArmaViewModel crearArnaVM;

    private TextView tvNuevaEditar_CArma;
    private EditText etNombre_CArma, etDanioArma_CArma, etBonoArma_CArma, etBonoAtk_CArma, etBonoAtm_CArma, etBonoDef_CArma, etBonoDfm_CArma, etBonoCrt_CArma, etBonoAcc_CArma, etModAtk_CArma, etModAtm_CArma, etModDef_CArma, etModDfm_CArma, etPrecio_CArma, etPeso_CArma, etDescripcion_CArma;
    private Spinner spCategoria_CArma;
    private Button btCrearActualizar_CArma;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearArmaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crearArnaVM = new ViewModelProvider(this).get(CrearArmaViewModel.class);
        crearArnaVM.getMutableCategorias().observe(getViewLifecycleOwner(), new Observer<ArrayList<Categoria>>() {
            @Override
            public void onChanged(ArrayList<Categoria> categorias) {
                ArrayAdapter<Categoria> spinnerCategoria = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, categorias);
                spCategoria_CArma.setAdapter(spinnerCategoria);
            }
        });
        crearArnaVM.obtenerCategorias();
        crearArnaVM.getMutableArma().observe(getViewLifecycleOwner(), new Observer<Arma>() {
            @Override
            public void onChanged(Arma arma) {
                etNombre_CArma.setText(arma.getNombre());
                etDanioArma_CArma.setText(arma.getDanioArma()+"");
                etBonoArma_CArma.setText(arma.getBonoArma()+"");
                etBonoAtk_CArma.setText(arma.getBonoAtk()+"");
                etBonoAtm_CArma.setText(arma.getBonoAtm()+"");
                etBonoDef_CArma.setText(arma.getBonoDef()+"");
                etBonoDfm_CArma.setText(arma.getBonoDfm()+"");
                etBonoCrt_CArma.setText(arma.getBonoCrt()+"");
                etBonoAcc_CArma.setText(arma.getBonoAcc()+"");
                etModAtk_CArma.setText(arma.getModAtk()+"");
                etModAtm_CArma.setText(arma.getModAtm()+"");
                etModDef_CArma.setText(arma.getModDef()+"");
                etModDfm_CArma.setText(arma.getModDef()+"");
                etPrecio_CArma.setText(arma.getPrecio()+"");
                etPeso_CArma.setText(arma.getPeso()+"");
                etDescripcion_CArma.setText(arma.getDescripcion());

                tvNuevaEditar_CArma.setText("Editar Arma");
                btCrearActualizar_CArma.setText("Actualizar");
            }
        });
        crearArnaVM.getArma(getArguments());

        inicializarVista(root);
        crearArnaVM.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CArma.setText(s);
                etDanioArma_CArma.setText(s);
                etBonoArma_CArma.setText(s);
                etBonoAtk_CArma.setText(s);
                etBonoAtm_CArma.setText(s);
                etBonoDef_CArma.setText(s);
                etBonoDfm_CArma.setText(s);
                etBonoCrt_CArma.setText(s);
                etBonoAcc_CArma.setText(s);
                etModAtk_CArma.setText(s);
                etModAtm_CArma.setText(s);
                etModDef_CArma.setText(s);
                etModDfm_CArma.setText(s);
                etPrecio_CArma.setText(s);
                etPeso_CArma.setText(s);
                etDescripcion_CArma.setText(s);
            }
        });
        crearArnaVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        this.tvNuevaEditar_CArma = v.findViewById(R.id.tvNuevaEditar_CArma);
        this.etNombre_CArma = v.findViewById(R.id.etNombre_CArma);
        this.spCategoria_CArma = v.findViewById(R.id.spCategoria_CArma);
        this.etDanioArma_CArma = v.findViewById(R.id.etDanioArma_CArma);
        this.etBonoArma_CArma = v.findViewById(R.id.etBonoArma_CArma);
        this.etBonoAtk_CArma = v.findViewById(R.id.etBonoAtk_CArma);
        this.etBonoAtm_CArma = v.findViewById(R.id.etBonoAtm_CArma);
        this.etBonoDef_CArma = v.findViewById(R.id.etBonoDef_CArma);
        this.etBonoDfm_CArma = v.findViewById(R.id.etBonoDfm_CArma);
        this.etBonoCrt_CArma = v.findViewById(R.id.etBonoCrt_CArma);
        this.etBonoAcc_CArma = v.findViewById(R.id.etBonoAcc_CArma);
        this.etModAtk_CArma = v.findViewById(R.id.etModAtk_CArma);
        this.etModAtm_CArma = v.findViewById(R.id.etModAtm_CArma);
        this.etModDef_CArma = v.findViewById(R.id.etModDef_CArma);
        this.etModDfm_CArma = v.findViewById(R.id.etModDfm_CArma);
        this.etPrecio_CArma = v.findViewById(R.id.etPrecio_CArma);
        this.etPeso_CArma = v.findViewById(R.id.etPeso_CArma);
        this.etDescripcion_CArma = v.findViewById(R.id.etDescripcion_CArma);

        this.btCrearActualizar_CArma = v.findViewById(R.id.btCrearActualizar_CArma);
        this.btCrearActualizar_CArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String action = btCrearActualizar_CArma.getText().toString();

                    String nombre = etNombre_CArma.getText().toString();
                    Categoria categoria = (Categoria) spCategoria_CArma.getSelectedItem();
                    int danio = Integer.parseInt(etDanioArma_CArma.getText().toString());
                    int base = Integer.parseInt(etBonoArma_CArma.getText().toString());
                    int bonoAtk = Integer.parseInt(etBonoAtk_CArma.getText().toString());
                    int bonoAtm = Integer.parseInt(etBonoAtm_CArma.getText().toString());
                    int bonoDef = Integer.parseInt(etBonoDef_CArma.getText().toString());
                    int bonoDfm = Integer.parseInt(etBonoDfm_CArma.getText().toString());
                    int bonoCrt = Integer.parseInt(etBonoCrt_CArma.getText().toString());
                    int bonoAcc = Integer.parseInt(etBonoAcc_CArma.getText().toString());
                    float modAtk = Float.parseFloat(etModAtk_CArma.getText().toString());
                    float modAtm = Float.parseFloat(etModAtm_CArma.getText().toString());
                    float modDef = Float.parseFloat(etModDef_CArma.getText().toString());
                    float modDfm = Float.parseFloat(etModDfm_CArma.getText().toString());
                    int precio = Integer.parseInt(etPrecio_CArma.getText().toString());
                    float peso = Float.parseFloat(etPeso_CArma.getText().toString());
                    String descripcion = etDescripcion_CArma.getText().toString();

                    crearArnaVM.tomarAccion(action, nombre, categoria, danio, base, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoCrt, bonoAcc, modAtk, modAtm, modDef, modDfm, precio, peso, descripcion);
                } catch (NumberFormatException ex) {
                    crearArnaVM.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}