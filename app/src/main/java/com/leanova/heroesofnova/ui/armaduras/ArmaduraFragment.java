package com.leanova.heroesofnova.ui.armaduras;

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
import com.leanova.heroesofnova.databinding.FragmentArmaduraBinding;
import com.leanova.heroesofnova.modelos.Armadura;

import java.util.ArrayList;

public class ArmaduraFragment extends Fragment {
    private FragmentArmaduraBinding binding;
    private ArmaduraViewModel armaduraVM;

    private Button btNuevo_Armadura;
    private EditText etBuscar_Armadura;
    private ListView lvArmaduras;
    private TextView tvAviso_Armadura;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArmaduraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        armaduraVM = new ViewModelProvider(this).get(ArmaduraViewModel.class);
        armaduraVM.getMutableResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_Armadura.setText(s);
            }
        });

        inicializarVista(root);
        armaduraVM.getMutableArmaduras().observe(getViewLifecycleOwner(), new Observer<ArrayList<Armadura>>() {
            @Override
            public void onChanged(ArrayList<Armadura> armaduras) {
                ArmaduraAdapter aa = new ArmaduraAdapter(getContext(), R.layout.item_armadura, armaduras);
                lvArmaduras.setAdapter(aa);
            }
        });
        armaduraVM.obtenerArmaduras();
        armaduraVM.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btNuevo_Armadura.setVisibility(integer);
            }
        });
        armaduraVM.setAccess();

        return root;
    }

    private void inicializarVista(View v) {
        this.btNuevo_Armadura = v.findViewById(R.id.btNuevo_Armadura);
        this.btNuevo_Armadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.crearArmaduraFragment);
            }
        });

        this.etBuscar_Armadura = v.findViewById(R.id.etBuscar_Armadura);
        this.etBuscar_Armadura.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nombre = etBuscar_Armadura.getText().toString();
                armaduraVM.obtenerPorNombre(nombre);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        this.lvArmaduras = v.findViewById(R.id.lvArmadura);
        this.lvArmaduras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Armadura aSeleccionado = armaduraVM.getListaArmaduras().get(i);

                Bundle bArmadura = new Bundle();
                bArmadura.putSerializable("armadura", aSeleccionado);
                Navigation.findNavController(v).navigate(R.id.detalleArmaduraFragment, bArmadura);
            }
        });

        this.tvAviso_Armadura = v.findViewById(R.id.tvAviso_Armadura);
        this.tvAviso_Armadura.setText("");
    }
}