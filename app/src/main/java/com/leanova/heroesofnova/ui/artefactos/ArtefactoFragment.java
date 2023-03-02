package com.leanova.heroesofnova.ui.artefactos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentArtefactoBinding;
import com.leanova.heroesofnova.modelos.Artefacto;

import java.util.ArrayList;

public class ArtefactoFragment extends Fragment {
    private FragmentArtefactoBinding binding;
    private ArtefactoViewModel artefactoVM;

    private Button btNuevo_Artefacto;
    private EditText etBuscar_Artefacto;
    private ListView lvArtefacto;
    private TextView tvAviso_Artefacto;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArtefactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        artefactoVM = new ViewModelProvider(this).get(ArtefactoViewModel.class);
        artefactoVM.getMutableResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_Artefacto.setText(s);
            }
        });

        inicializarVista(root);
        artefactoVM.getMutableArtefactos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Artefacto>>() {
            @Override
            public void onChanged(ArrayList<Artefacto> artefactos) {
                ArtefactoAdapter adapter = new ArtefactoAdapter(getContext(), R.layout.item_artefacto, artefactos);
                lvArtefacto.setAdapter(adapter);

                etBuscar_Artefacto.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) { }
                });
            }
        });
        artefactoVM.obtenerArtefactos();
        artefactoVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_Artefacto.setVisibility(integer);
            }
        });
        artefactoVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.btNuevo_Artefacto = v.findViewById(R.id.btNuevo_Artefacto);
        this.btNuevo_Artefacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearArtefactoFragment);
            }
        });

        this.etBuscar_Artefacto = v.findViewById(R.id.etBuscar_Artefacto);
        this.lvArtefacto = v.findViewById(R.id.lvArtefacto);
        this.lvArtefacto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artefacto aSeleccionado = artefactoVM.getListaArtefactos().get(i);

                Bundle bArtefacto = new Bundle();
                bArtefacto.putSerializable("artefacto", aSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleArtefactoFragment, bArtefacto);
            }
        });

        this.tvAviso_Artefacto = v.findViewById(R.id.tvAviso_Artefacto);
        this.tvAviso_Artefacto.setText("");
    }
}