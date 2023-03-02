package com.leanova.heroesofnova.ui.armaduras;

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
import com.leanova.heroesofnova.databinding.FragmentCrearArmaduraBinding;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Rareza;

import java.util.ArrayList;

public class CrearArmaduraFragment extends Fragment {
    private FragmentCrearArmaduraBinding binding;
    private CrearArmaduraViewModel crearArmaduraVM;

    private TextView tvNuevaEditar_CArmadura;
    private EditText etNombre_CArmadura, etBonoDef_CArmadura, etBonoDfm_CArmadura, etBonoDex_CArmadura, etBonoEva_CArmadura, etModDef_CArmadura, etModDfm_CArmadura, etPrecio_CArmadura, etPeso_CArmadura, etDescripcion_CArmadura;
    private Spinner spRareza_CArmadura;
    private Button btCrearActualizar_CArmadura;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearArmaduraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crearArmaduraVM = new ViewModelProvider(this).get(CrearArmaduraViewModel.class);
        crearArmaduraVM.getMutableRarezas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Rareza>>() {
            @Override
            public void onChanged(ArrayList<Rareza> rarezas) {
                ArrayAdapter<Rareza> spinnerRareza = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, rarezas);
                spRareza_CArmadura.setAdapter(spinnerRareza);
            }
        });
        crearArmaduraVM.obtenerRarezas();
        crearArmaduraVM.getMutableArmadura().observe(getViewLifecycleOwner(), new Observer<Armadura>() {
            @Override
            public void onChanged(Armadura armadura) {
                etNombre_CArmadura.setText(armadura.getNombre()+"");
                spRareza_CArmadura.setSelection(armadura.getRarezaId()-1);
                etBonoDef_CArmadura.setText(armadura.getBonoDef()+"");
                etBonoDfm_CArmadura.setText(armadura.getBonoDfm()+"");
                etBonoDex_CArmadura.setText(armadura.getBonoDex()+"");
                etBonoEva_CArmadura.setText(armadura.getBonoEva()+"");
                etModDef_CArmadura.setText(armadura.getModDef()+"");
                etModDfm_CArmadura.setText(armadura.getModDfm()+"");
                etPrecio_CArmadura.setText(armadura.getPrecio()+"");
                etPeso_CArmadura.setText(armadura.getPeso()+"");
                etDescripcion_CArmadura.setText(armadura.getDescripcion());

                tvNuevaEditar_CArmadura.setText("Editar Armadura");
                btCrearActualizar_CArmadura.setText("Actualizar");
            }
        });
        crearArmaduraVM.getArmadura(getArguments());

        inicializarVista(root);
        crearArmaduraVM.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CArmadura.setText(s);
                etBonoDef_CArmadura.setText(s);
                etBonoDfm_CArmadura.setText(s);
                etBonoDex_CArmadura.setText(s);
                etBonoEva_CArmadura.setText(s);
                etModDef_CArmadura.setText(s);
                etModDfm_CArmadura.setText(s);
                etPrecio_CArmadura.setText(s);
                etPeso_CArmadura.setText(s);
                etDescripcion_CArmadura.setText(s);
            }
        });
        crearArmaduraVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        this.tvNuevaEditar_CArmadura = v.findViewById(R.id.tvNuevaEditar_CArmadura);
        this.etNombre_CArmadura = v.findViewById(R.id.etNombre_CArmadura);
        this.spRareza_CArmadura = v.findViewById(R.id.spRareza_CArmadura);
        this.etBonoDef_CArmadura = v.findViewById(R.id.etBonoDef_CArmadura);
        this.etBonoDfm_CArmadura = v.findViewById(R.id.etBonoDfm_CArmadura);
        this.etBonoDex_CArmadura = v.findViewById(R.id.etBonoDex_CArmadura);
        this.etBonoEva_CArmadura = v.findViewById(R.id.etBonoEva_CArmadura);
        this.etModDef_CArmadura = v.findViewById(R.id.etModDef_CArmadura);
        this.etModDfm_CArmadura = v.findViewById(R.id.etModDfm_CArmadura);
        this.etPrecio_CArmadura = v.findViewById(R.id.etPrecio_CArmadura);
        this.etPeso_CArmadura = v.findViewById(R.id.etPeso_CArmadura);
        this.etDescripcion_CArmadura = v.findViewById(R.id.etDescripcion_CArmadura);

        this.btCrearActualizar_CArmadura = v.findViewById(R.id.btCrearActualizar_CArmadura);
        this.btCrearActualizar_CArmadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String action = btCrearActualizar_CArmadura.getText().toString();

                    String nombre = etNombre_CArmadura.getText().toString();
                    Rareza rareza = (Rareza) spRareza_CArmadura.getSelectedItem();
                    int bonoDef = Integer.parseInt(etBonoDef_CArmadura.getText().toString());
                    int bonoDfm = Integer.parseInt(etBonoDfm_CArmadura.getText().toString());
                    int bonoDex = Integer.parseInt(etBonoDex_CArmadura.getText().toString());
                    int bonoEva = Integer.parseInt(etBonoEva_CArmadura.getText().toString());
                    float modDef = Float.parseFloat(etModDef_CArmadura.getText().toString());
                    float modDfm = Float.parseFloat(etModDfm_CArmadura.getText().toString());
                    int precio = Integer.parseInt(etPrecio_CArmadura.getText().toString());
                    float peso = Float.parseFloat(etPeso_CArmadura.getText().toString());
                    String descripcion = etDescripcion_CArmadura.getText().toString();

                    crearArmaduraVM.tomarAccion(action, nombre, rareza, bonoDef, bonoDfm, bonoDex, bonoEva, modDef, modDfm, precio, peso, descripcion);
                } catch (NumberFormatException ex) {
                    crearArmaduraVM.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}