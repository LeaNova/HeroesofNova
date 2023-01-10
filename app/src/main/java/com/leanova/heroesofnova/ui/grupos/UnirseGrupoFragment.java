package com.leanova.heroesofnova.ui.grupos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentUnirseGrupoBinding;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnirseGrupoFragment extends Fragment {
    private FragmentUnirseGrupoBinding binding;
    private UnirseGrupoViewModel ugvm;

    private Grupo grupo;

    private TextView tvNombre_UG, tvMaster_UG, tvAviso_UG;
    private EditText etContrasenia_UG;
    private Spinner spPersonajes_UG;
    private Button btUnirse_UC;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUnirseGrupoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ugvm = new ViewModelProvider(this).get(UnirseGrupoViewModel.class);
        ugvm.getMutableGrupo().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo g) {
                grupo = g;
                tvNombre_UG.setText(grupo.getNombre());
                tvMaster_UG.setText(grupo.getMaster().getUsuario());
            }
        });
        ugvm.obtenerGrupo(getArguments());

        ugvm.getMutablePersonajes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Personaje>>() {
            @Override
            public void onChanged(ArrayList<Personaje> personajes) {
                ArrayAdapter<Personaje> spinnerPersonaje = new ArrayAdapter<>(getContext(), R.layout.style_sp_c1, personajes);
                spPersonajes_UG.setAdapter(spinnerPersonaje);
            }
        });
        ugvm.obtenerPersonajes();

        inicializarVista(root);
        ugvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_UG.setText(s);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_UG = v.findViewById(R.id.tvNombre_UG);
        this.tvMaster_UG = v.findViewById(R.id.tvMaster_UG);
        this.tvAviso_UG = v.findViewById(R.id.tvAviso_UG);
        this.etContrasenia_UG = v.findViewById(R.id.etContrasenia_UG);
        this.spPersonajes_UG = v.findViewById(R.id.spPersonajes_UG);

        this.btUnirse_UC = v.findViewById(R.id.btUnirse_UC);
        this.btUnirse_UC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = etContrasenia_UG.getText().toString();
                Personaje p = (Personaje) spPersonajes_UG.getSelectedItem();

                new AlertDialog.Builder(getContext())
                        .setTitle("Unirse")
                        .setMessage("Confirmar unirse al grupo " + grupo.getNombre())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ugvm.unirseGrupo(grupo, pass, p.getIdPersonaje(), v);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Nada
                            }
                        }).show();
            }
        });
    }
}