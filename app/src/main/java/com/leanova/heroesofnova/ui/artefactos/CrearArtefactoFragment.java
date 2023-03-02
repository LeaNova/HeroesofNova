package com.leanova.heroesofnova.ui.artefactos;

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
import com.leanova.heroesofnova.databinding.FragmentCrearArtefactoBinding;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Rareza;
import com.leanova.heroesofnova.modelos.Seccion;

import java.util.ArrayList;

public class CrearArtefactoFragment extends Fragment {
    private FragmentCrearArtefactoBinding binding;
    private CrearArtefactoViewModel crearArtefactoVM;

    private TextView tvNuevaEditar_CArtefacto;
    private EditText etNombre_CArtefacto, etBonoVida_CArtefacto, etBonoEnergia_CArtefacto, etBonoAtk_CArtefacto, etBonoAtm_CArtefacto, etBonoDef_CArtefacto, etBonoDfm_CArtefacto, etBonoDex_CArtefacto, etBonoEva_CArtefacto, etBonoCrt_CArtefacto, etBonoAcc_CArtefacto, etPrecio_CArtefacto, etPeso_CArtefacto, etDescripcion_CArtefacto;
    private Spinner spSeccion_CArtefacto, spRareza_CArtefacto;
    private Button btCrearActualizar_CArtefacto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearArtefactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crearArtefactoVM = new ViewModelProvider(this).get(CrearArtefactoViewModel.class);
        crearArtefactoVM.getMutableSecciones().observe(getViewLifecycleOwner(), new Observer<ArrayList<Seccion>>() {
            @Override
            public void onChanged(ArrayList<Seccion> seccions) {
                ArrayAdapter<Seccion> spinnerSeccion = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, seccions);
                spSeccion_CArtefacto.setAdapter(spinnerSeccion);
            }
        });
        crearArtefactoVM.getMutableRarezas().observe(getViewLifecycleOwner(), new Observer<ArrayList<Rareza>>() {
            @Override
            public void onChanged(ArrayList<Rareza> rarezas) {
                ArrayAdapter<Rareza> spinnerRareza = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, rarezas);
                spRareza_CArtefacto.setAdapter(spinnerRareza);
            }
        });
        crearArtefactoVM.obtenerSeccionesRarezas();
        crearArtefactoVM.getMutableArtefacto().observe(getViewLifecycleOwner(), new Observer<Artefacto>() {
            @Override
            public void onChanged(Artefacto artefacto) {
                etNombre_CArtefacto.setText(artefacto.getNombre());
                etBonoVida_CArtefacto.setText(artefacto.getBonoVida()+"");
                etBonoEnergia_CArtefacto.setText(artefacto.getBonoEnergia()+"");
                etBonoAtk_CArtefacto.setText(artefacto.getBonoAtk()+"");
                etBonoAtm_CArtefacto.setText(artefacto.getBonoAtm()+"");
                etBonoDef_CArtefacto.setText(artefacto.getBonoDef()+"");
                etBonoDfm_CArtefacto.setText(artefacto.getBonoDfm()+"");
                etBonoDex_CArtefacto.setText(artefacto.getBonoDex()+"");
                etBonoEva_CArtefacto.setText(artefacto.getBonoEva()+"");
                etBonoCrt_CArtefacto.setText(artefacto.getBonoCrt()+"");
                etBonoAcc_CArtefacto.setText(artefacto.getBonoAcc()+"");
                etPrecio_CArtefacto.setText(artefacto.getPrecio()+"");
                etPeso_CArtefacto.setText(artefacto.getPeso()+"");
                etDescripcion_CArtefacto.setText(artefacto.getDescripcion());

                tvNuevaEditar_CArtefacto.setText("Editar Artefacto");
                btCrearActualizar_CArtefacto.setText("Actualizar");
            }
        });
        crearArtefactoVM.getArtefacto(getArguments());

        inicializarVista(root);
        crearArtefactoVM.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CArtefacto.setText(s);
                etBonoVida_CArtefacto.setText(s);
                etBonoEnergia_CArtefacto.setText(s);
                etBonoAtk_CArtefacto.setText(s);
                etBonoAtm_CArtefacto.setText(s);
                etBonoDef_CArtefacto.setText(s);
                etBonoDfm_CArtefacto.setText(s);
                etBonoDex_CArtefacto.setText(s);
                etBonoEva_CArtefacto.setText(s);
                etBonoCrt_CArtefacto.setText(s);
                etBonoAcc_CArtefacto.setText(s);
                etPrecio_CArtefacto.setText(s);
                etPeso_CArtefacto.setText(s);
                etDescripcion_CArtefacto.setText(s);
            }
        });
        crearArtefactoVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        this.tvNuevaEditar_CArtefacto = v.findViewById(R.id.tvNuevaEditar_CArtefacto);
        this.etNombre_CArtefacto = v.findViewById(R.id.etNombre_CArtefacto);
        this.spSeccion_CArtefacto = v.findViewById(R.id.spSeccion_CArtefacto);
        this.spRareza_CArtefacto = v.findViewById(R.id.spRareza_CArtefacto);
        this.etBonoVida_CArtefacto = v.findViewById(R.id.etBonoVida_CArtefacto);
        this.etBonoEnergia_CArtefacto = v.findViewById(R.id.etBonoEnergia_CArtefacto);
        this.etBonoAtk_CArtefacto = v.findViewById(R.id.etBonoAtk_CArtefacto);
        this.etBonoAtm_CArtefacto = v.findViewById(R.id.etBonoAtm_CArtefacto);
        this.etBonoDef_CArtefacto = v.findViewById(R.id.etBonoDef_CArtefacto);
        this.etBonoDfm_CArtefacto = v.findViewById(R.id.etBonoDfm_CArtefacto);
        this.etBonoDex_CArtefacto = v.findViewById(R.id.etBonoDex_CArtefacto);
        this.etBonoEva_CArtefacto = v.findViewById(R.id.etBonoEva_CArtefacto);
        this.etBonoCrt_CArtefacto = v.findViewById(R.id.etBonoCrt_CArtefacto);
        this.etBonoAcc_CArtefacto = v.findViewById(R.id.etBonoAcc_CArtefacto);
        this.etPrecio_CArtefacto = v.findViewById(R.id.etPrecio_CArtefacto);
        this.etPeso_CArtefacto = v.findViewById(R.id.etPeso_CArtefacto);
        this.etDescripcion_CArtefacto = v.findViewById(R.id.etDescripcion_CArtefacto);

        this.btCrearActualizar_CArtefacto = v.findViewById(R.id.btCrearActualizar_CArtefacto);
        this.btCrearActualizar_CArtefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String action = btCrearActualizar_CArtefacto.getText().toString();

                    String nombre = etNombre_CArtefacto.getText().toString();
                    Seccion seccion = (Seccion) spSeccion_CArtefacto.getSelectedItem();
                    Rareza rareza = (Rareza) spRareza_CArtefacto.getSelectedItem();
                    int bonoVida = Integer.parseInt(etBonoVida_CArtefacto.getText().toString());
                    int bonoEnergia = Integer.parseInt(etBonoEnergia_CArtefacto.getText().toString());
                    int bonoAtk = Integer.parseInt(etBonoAtk_CArtefacto.getText().toString());
                    int bonoAtm = Integer.parseInt(etBonoAtm_CArtefacto.getText().toString());
                    int bonoDef = Integer.parseInt(etBonoDef_CArtefacto.getText().toString());
                    int bonoDfm = Integer.parseInt(etBonoDfm_CArtefacto.getText().toString());
                    int bonoDex = Integer.parseInt(etBonoDex_CArtefacto.getText().toString());
                    int bonoEva = Integer.parseInt(etBonoEva_CArtefacto.getText().toString());
                    int bonoCrt = Integer.parseInt(etBonoCrt_CArtefacto.getText().toString());
                    int bonoAcc = Integer.parseInt(etBonoAcc_CArtefacto.getText().toString());
                    int precio = Integer.parseInt(etPrecio_CArtefacto.getText().toString());
                    float peso = Float.parseFloat(etPeso_CArtefacto.getText().toString());
                    String descripcion = etDescripcion_CArtefacto.getText().toString();

                    crearArtefactoVM.tomarAccion(action, nombre, seccion, rareza, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
                } catch (NumberFormatException ex) {
                    crearArtefactoVM.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}