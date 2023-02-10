package com.leanova.heroesofnova.ui.items;

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
import com.leanova.heroesofnova.databinding.FragmentCrearItemBinding;
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Tipo;

import java.util.ArrayList;

public class CrearItemFragment extends Fragment {
    private FragmentCrearItemBinding binding;
    private CrearItemViewModel crearItemVM;

    private TextView tvNuevaEditar_CItem, tvTipo_CTipo;
    private EditText etNombre_CItem, etBonoVida_CItem, etBonoEnergia_CItem, etBonoAtk_CItem, etBonoAtm_CItem, etBonoDef_CItem, etBonoDfm_CItem, etBonoDex_CItem, etBonoEva_CItem, etBonoCrt_CItem, etBonoAcc_CItem, etPrecio_CItem, etPeso_CItem, etDescripcion_CItem;
    private Spinner spTipo_CItem;
    private Button btCrearActualizar_CItem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        crearItemVM = new ViewModelProvider(this).get(CrearItemViewModel.class);
        crearItemVM.getMutableTipos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tipo>>() {
            @Override
            public void onChanged(ArrayList<Tipo> tipos) {
                ArrayAdapter<Tipo> spinnerTipo = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, tipos);
                spTipo_CItem.setAdapter(spinnerTipo);
            }
        });
        crearItemVM.obtenerTipos();
        crearItemVM.getMutableItem().observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                etNombre_CItem.setText(item.getNombre());
                spTipo_CItem.setVisibility(View.GONE);
                tvTipo_CTipo.setText(item.getTipo().getNombre());
                tvTipo_CTipo.setVisibility(View.VISIBLE);
                etBonoVida_CItem.setText(item.getBonoVida()+"");
                etBonoEnergia_CItem.setText(item.getBonoEnergia()+"");
                etBonoAtk_CItem.setText(item.getBonoAtk()+"");
                etBonoAtm_CItem.setText(item.getBonoAtm()+"");
                etBonoDef_CItem.setText(item.getBonoDef()+"");
                etBonoDfm_CItem.setText(item.getBonoDfm()+"");
                etBonoDex_CItem.setText(item.getBonoDex()+"");
                etBonoEva_CItem.setText(item.getBonoEva()+"");
                etBonoCrt_CItem.setText(item.getBonoCrt()+"");
                etBonoAcc_CItem.setText(item.getBonoAcc()+"");
                etPrecio_CItem.setText(item.getPrecio()+"");
                etPeso_CItem.setText(item.getPeso()+"");
                etDescripcion_CItem.setText(item.getDescripcion());

                tvNuevaEditar_CItem.setText("Editar Item");
                btCrearActualizar_CItem.setText("Actualizar");
            }
        });
        crearItemVM.getItem(getArguments());

        inicializarVista(root);
        crearItemVM.getMutableClear().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre_CItem.setText(s);
                etBonoVida_CItem.setText(s);
                etBonoEnergia_CItem.setText(s);
                etBonoAtk_CItem.setText(s);
                etBonoAtm_CItem.setText(s);
                etBonoDef_CItem.setText(s);
                etBonoDfm_CItem.setText(s);
                etBonoDex_CItem.setText(s);
                etBonoEva_CItem.setText(s);
                etBonoCrt_CItem.setText(s);
                etBonoAcc_CItem.setText(s);
                etPrecio_CItem.setText(s);
                etPeso_CItem.setText(s);
                etDescripcion_CItem.setText(s);
            }
        });
        crearItemVM.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        this.tvNuevaEditar_CItem = v.findViewById(R.id.tvNuevaEditar_CItem);
        this.etNombre_CItem = v.findViewById(R.id.etNombre_CItem);
        this.spTipo_CItem = v.findViewById(R.id.spTipo_CItem);
        this.tvTipo_CTipo = v.findViewById(R.id.tvTipo_CTipo);
        this.tvTipo_CTipo.setText("");
        this.tvTipo_CTipo.setVisibility(View.GONE);
        this.etBonoVida_CItem = v.findViewById(R.id.etBonoVida_CItem);
        this.etBonoEnergia_CItem = v.findViewById(R.id.etBonoEnergia_CItem);
        this.etBonoAtk_CItem = v.findViewById(R.id.etBonoAtk_CItem);
        this.etBonoAtm_CItem = v.findViewById(R.id.etBonoAtm_CItem);
        this.etBonoDef_CItem = v.findViewById(R.id.etBonoDef_CItem);
        this.etBonoDfm_CItem = v.findViewById(R.id.etBonoDfm_CItem);
        this.etBonoDex_CItem = v.findViewById(R.id.etBonoDex_CItem);
        this.etBonoEva_CItem = v.findViewById(R.id.etBonoEva_CItem);
        this.etBonoCrt_CItem = v.findViewById(R.id.etBonoCrt_CItem);
        this.etBonoAcc_CItem = v.findViewById(R.id.etBonoAcc_CItem);
        this.etPrecio_CItem = v.findViewById(R.id.etPrecio_CItem);
        this.etPeso_CItem = v.findViewById(R.id.etPeso_CItem);
        this.etDescripcion_CItem = v.findViewById(R.id.etDescripcion_CItem);

        this.btCrearActualizar_CItem = v.findViewById(R.id.btCrearActualizar_CItem);
        this.btCrearActualizar_CItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String action = btCrearActualizar_CItem.getText().toString();

                    String nombre = etNombre_CItem.getText().toString();
                    Tipo tipo = (Tipo) spTipo_CItem.getSelectedItem();
                    int bonoVida = Integer.parseInt(etBonoVida_CItem.getText().toString());
                    int bonoEnergia = Integer.parseInt(etBonoEnergia_CItem.getText().toString());
                    int bonoAtk = Integer.parseInt(etBonoAtk_CItem.getText().toString());
                    int bonoAtm = Integer.parseInt(etBonoAtm_CItem.getText().toString());
                    int bonoDef = Integer.parseInt(etBonoDef_CItem.getText().toString());
                    int bonoDfm = Integer.parseInt(etBonoDfm_CItem.getText().toString());
                    int bonoDex = Integer.parseInt(etBonoDex_CItem.getText().toString());
                    int bonoEva = Integer.parseInt(etBonoEva_CItem.getText().toString());
                    int bonoCrt = Integer.parseInt(etBonoCrt_CItem.getText().toString());
                    int bonoAcc = Integer.parseInt(etBonoAcc_CItem.getText().toString());
                    int precio = Integer.parseInt(etPrecio_CItem.getText().toString());
                    float peso = Float.parseFloat(etPeso_CItem.getText().toString());
                    String descripcion = etDescripcion_CItem.getText().toString();

                    crearItemVM.tomarAccion(action, nombre, tipo, bonoVida, bonoEnergia, bonoAtk, bonoAtm, bonoDef, bonoDfm, bonoDex, bonoEva, bonoCrt, bonoAcc, precio, peso, descripcion);
                } catch (NumberFormatException ex) {
                    crearItemVM.getAviso();
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });
    }
}