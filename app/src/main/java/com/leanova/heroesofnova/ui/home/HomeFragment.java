package com.leanova.heroesofnova.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentHomeBinding;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.Date;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel homeVM;

    private Usuario usuario;
    private TextView tvNombre_H, tvApellido_H, tvMail_H, tvRol_H, tvFecha_H;
    private Button btEditar_H, btCerrar_H;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeVM = new ViewModelProvider(this).get(HomeViewModel.class);

        homeVM.getMutableUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario u) {
                usuario = u;
                tvNombre_H.setText(usuario.getNombre());
                tvApellido_H.setText(usuario.getApellido());
                tvMail_H.setText(usuario.getMail());
                tvRol_H.setText(usuario.getRol().getNombre());
                Date fecha = usuario.getFechaCreado();
                int dia = fecha.getDate();
                int mes = fecha.getMonth()+1;
                int año = fecha.getYear()+1900;
                tvFecha_H.setText(dia+"/"+mes+"/"+año);
            }
        });
        homeVM.getUsuario();

        inicializarVista(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void inicializarVista(View v) {
        this.tvNombre_H = v.findViewById(R.id.tvNombre_H);
        this.tvApellido_H = v.findViewById(R.id.tvApellido_H);
        this.tvMail_H = v.findViewById(R.id.tvMail_H);
        this.tvRol_H = v.findViewById(R.id.tvRol_H);
        this.tvFecha_H = v.findViewById(R.id.tvFecha_H);
        this.btEditar_H = v.findViewById(R.id.btEditar_H);
        this.btEditar_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bUsuario = new Bundle();
                bUsuario.putSerializable("usuarioEdit", usuario);
                Navigation.findNavController(view).navigate(R.id.editarPerfilFragment, bUsuario);
            }
        });

        this.btCerrar_H = v.findViewById(R.id.btCerrar_H);
        this.btCerrar_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("¿Cerrar sesión?")
                        .setMessage("¿Desea cerrar su sesión?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.exit(0);
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