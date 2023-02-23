package com.leanova.heroesofnova.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentEditarPerfilBinding;
import com.leanova.heroesofnova.modelos.Usuario;

public class EditarPerfilFragment extends Fragment {
    private FragmentEditarPerfilBinding binding;
    private EditarPerfilViewModel editarPerfilVM;

    private EditText etNombre_EPerfil, etApellido_EPerfil, etUsuario_EPerfil, etContraseniaAct_EPerfil, etContraseniaNueva_EPerfil;
    private TextView tvAvisoUsuario_EPerfil;
    private Button btActualizar_EPerfil, btCambiarContra_EPerfil;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editarPerfilVM = new ViewModelProvider(this).get(EditarPerfilViewModel.class);
        editarPerfilVM.getMutableUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etNombre_EPerfil.setText(usuario.getNombre());
                etApellido_EPerfil.setText(usuario.getApellido());
                etUsuario_EPerfil.setText(usuario.getUsuario());
            }
        });

        inicializarVista(root);
        editarPerfilVM.obtenerUsuario(getArguments());
        editarPerfilVM.getMutableAvisoUsuario().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAvisoUsuario_EPerfil.setText(s);
            }
        });
        editarPerfilVM.getMutableAvisoUsuarioColor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAvisoUsuario_EPerfil.setTextColor(Color.parseColor(s));
            }
        });
        editarPerfilVM.getMutableEnable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                btActualizar_EPerfil.setEnabled(aBoolean);
            }
        });

        return root;
    }

    private void inicializarVista(View v) {
        this.etNombre_EPerfil = v.findViewById(R.id.etNombre_EPerfil);
        this.etApellido_EPerfil = v.findViewById(R.id.etApellido_EPerfil);
        this.etUsuario_EPerfil = v.findViewById(R.id.etUsuario_EPerfil);
        this.etUsuario_EPerfil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String usuario = etUsuario_EPerfil.getText().toString();
                editarPerfilVM.checUsuario(usuario);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        this.etContraseniaAct_EPerfil = v.findViewById(R.id.etContraseniaAct_EPerfil);
        this.etContraseniaNueva_EPerfil = v.findViewById(R.id.etContraseniaNueva_EPerfil);

        this.tvAvisoUsuario_EPerfil = v.findViewById(R.id.tvAvisoUsuario_EPerfil);

        this.btActualizar_EPerfil = v.findViewById(R.id.btActualizar_EPerfil);
        this.btActualizar_EPerfil.setEnabled(false);
        this.btActualizar_EPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre_EPerfil.getText().toString();
                String apellido = etApellido_EPerfil.getText().toString();
                String usuario = etUsuario_EPerfil.getText().toString();

                editarPerfilVM.actualizarPerfil(nombre, apellido, usuario);
            }
        });

        this.btCambiarContra_EPerfil = v.findViewById(R.id.btCambiarContra_EPerfil);
        this.btCambiarContra_EPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contraAct = etContraseniaAct_EPerfil.getText().toString();
                String contraNueva = etContraseniaNueva_EPerfil.getText().toString();

                editarPerfilVM.cambiarContrasenia(contraAct, contraNueva);
            }
        });
    }
}