package com.leanova.heroesofnova.ui.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Rol;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {
    private SigninViewModel svm;
    private EditText etSNombre, etSApellido, etSMail, etSPass1, etSPass2;
    private Spinner spSRoles;
    private Button btSSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        svm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SigninViewModel.class);

        inicializarVista();
    }

    private void inicializarVista() {
        this.etSNombre = findViewById(R.id.etSNombre);
        this.etSApellido = findViewById(R.id.etSApellido);
        this.etSMail = findViewById(R.id.etSMail);
        this.etSPass1 = findViewById(R.id.etSPass1);
        this.etSPass2 = findViewById(R.id.etSPass2);

        this.spSRoles = findViewById(R.id.spSRoles);
        ArrayList<String> listaRoles = svm.getRoles();
        ArrayAdapter<String> adapterRoles = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaRoles);
        this.spSRoles.setAdapter(adapterRoles);

        this.btSSignin = findViewById(R.id.btSSignin);
        this.btSSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etSNombre.getText().toString();
                String apellido = etSApellido.getText().toString();
                String mail = etSMail.getText().toString();
                String pass1 = etSPass1.getText().toString();
                String pass2 = etSPass2.getText().toString();
                int rol = spSRoles.getSelectedItemPosition() +1;

                svm.registrarUsuario(nombre, apellido, mail, pass1, pass2, rol);
            }
        });
    }
}