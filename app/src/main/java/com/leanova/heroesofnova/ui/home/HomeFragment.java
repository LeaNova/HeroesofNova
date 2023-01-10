package com.leanova.heroesofnova.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentHomeBinding;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.Date;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel hvm;
    private TextView tvNombre_H, tvApellido_H, tvMail_H, tvRol_H, tvFecha_H;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        hvm = new ViewModelProvider(this).get(HomeViewModel.class);

        hvm.getMutableUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
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
        hvm.getUsuario();

        inicializarVista(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void inicializarVista(View view) {
        this.tvNombre_H = view.findViewById(R.id.tvNombre_H);
        this.tvApellido_H = view.findViewById(R.id.tvApellido_H);
        this.tvMail_H = view.findViewById(R.id.tvMail_H);
        this.tvRol_H = view.findViewById(R.id.tvRol_H);
        this.tvFecha_H = view.findViewById(R.id.tvFecha_H);
    }
}