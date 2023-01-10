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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetalleGrupoBinding;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;

import java.util.ArrayList;

public class DetalleGrupoFragment extends Fragment {
    private FragmentDetalleGrupoBinding binding;
    private DetalleGrupoViewModel dgvm;

    private Grupo grupo;
    private TextView tvNombre_DG, tvDetalle_DG, textPassMaster_DG, tvPassMaster_DG, tvAviso_DG;
    private CheckBox cbDisponible_DG;
    private ListView lvJugadores_DG;
    private Button btUnirse_DG, btSalir_DG, btEditar_DG, btBorrar_DG;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleGrupoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dgvm = new ViewModelProvider(this).get(DetalleGrupoViewModel.class);
        dgvm.getMutableGrupo().observe(getViewLifecycleOwner(), new Observer<Grupo>() {
            @Override
            public void onChanged(Grupo g) {
                grupo = g;
                tvNombre_DG.setText(grupo.getNombre());
                tvDetalle_DG.setText(grupo.getDescripcion());
                cbDisponible_DG.setChecked(grupo.getDisponible());
            }
        });
        dgvm.getMutableViewData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvPassMaster_DG.setText(s);
            }
        });
        dgvm.getMutableAccess().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btUnirse_DG.setVisibility(8 - integer);
                btSalir_DG.setVisibility(8 - integer);
                btEditar_DG.setVisibility(integer);
                btBorrar_DG.setVisibility(integer);
                cbDisponible_DG.setVisibility(integer);
            }
        });
        dgvm.getGrupo(getArguments());

        inicializarVista(root);
        dgvm.getMutableView().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textPassMaster_DG.setText(s);
            }
        });
        dgvm.setAccess();
        dgvm.getMutableAviso().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso_DG.setText(s);
            }
        });
        dgvm.getMutableLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Participante>>() {
            @Override
            public void onChanged(ArrayList<Participante> participantes) {
                ParticipanteAdapter pa = new ParticipanteAdapter(getContext(), R.layout.item_participante, participantes);
                lvJugadores_DG.setAdapter(pa);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.tvNombre_DG = v.findViewById(R.id.tvNombre_DG);
        this.cbDisponible_DG = v.findViewById(R.id.cbDisponible_DG);
        this.cbDisponible_DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dgvm.cambiarDisponibilidad(grupo.getIdGrupo());
            }
        });

        this.tvDetalle_DG = v.findViewById(R.id.tvDetalle_DG);
        this.textPassMaster_DG = v.findViewById(R.id.textPassMaster_DG);
        this.tvPassMaster_DG = v.findViewById(R.id.tvPassMaster_DG);
        this.tvAviso_DG = v.findViewById(R.id.tvAviso_DG);
        this.lvJugadores_DG = v.findViewById(R.id.lvJugadores_DG);

        this.btUnirse_DG = v.findViewById(R.id.btUnirse_DG);
        this.btUnirse_DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bGrupo = new Bundle();
                bGrupo.putSerializable("grupoUnirse", grupo);
                Navigation.findNavController(view).navigate(R.id.unirseGrupoFragment, bGrupo);
            }
        });

        this.btSalir_DG = v.findViewById(R.id.btSalir_DG);
        this.btSalir_DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Salir")
                        .setMessage("Desea salir del grupo" + tvNombre_DG.getText().toString())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dgvm.salirGrupo(grupo, v);
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

        this.btEditar_DG = v.findViewById(R.id.btEditar_DG);
        this.btEditar_DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bGrupo = new Bundle();
                bGrupo.putSerializable("grupoEdit", grupo);
                Navigation.findNavController(view).navigate(R.id.crearGrupoFragment, bGrupo);
            }
        });

        this.btBorrar_DG = v.findViewById(R.id.btBorrar_DG);
        this.btBorrar_DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pass = new EditText(getContext());
                pass.setHint("Contraseña");
                new AlertDialog.Builder(getContext())
                        .setTitle("¿Borrar grupo " + tvNombre_DG.getText().toString() + "?")
                        .setMessage("Intruzca la contraseña del grupo abajo para confirmar.")
                        .setView(pass)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String contrasenia = pass.getText().toString();
                                dgvm.borrarGrupo(grupo, contrasenia, v);
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