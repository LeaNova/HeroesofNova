package com.leanova.heroesofnova.ui.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Rol;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {
    private SigninViewModel svm;

    private EditText etSNombre, etSApellido, etSMail, etSUsuario, etSPass1, etSPass2;
    private TextView tvSAvisoMail, tvSAvisoUsuario, tvSAvisoPass;
    private Button btSSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        svm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SigninViewModel.class);
        svm.getMutableAvisoMail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvSAvisoMail.setText(s);
            }
        });
        svm.getMutableAvisoUsuario().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvSAvisoUsuario.setText(s);
            }
        });
        svm.getMutableAvisoPass().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvSAvisoPass.setText(s);
            }
        });

        inicializarVista();
    }

    private void inicializarVista() {
        this.etSNombre = findViewById(R.id.etSNombre);
        this.etSApellido = findViewById(R.id.etSApellido);
        this.etSMail = findViewById(R.id.etSMail);
        this.etSMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String mail = etSMail.getText().toString();
                svm.checkMail(mail);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        this.tvSAvisoMail = findViewById(R.id.tvSAvisoMail);
        this.etSUsuario = findViewById(R.id.etSUsuario);
        this.etSUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String usuario = etSUsuario.getText().toString();
                svm.checUsuario(usuario);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        this.tvSAvisoUsuario = findViewById(R.id.tvSAvisoUsuario);
        this.etSPass1 = findViewById(R.id.etSPass1);
        this.etSPass2 = findViewById(R.id.etSPass2);
        this.tvSAvisoPass = findViewById(R.id.tvSAvisoPass);

        this.btSSignin = findViewById(R.id.btSSignin);
        this.btSSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etSNombre.getText().toString();
                String apellido = etSApellido.getText().toString();
                String mail = etSMail.getText().toString();
                String usuario = etSUsuario.getText().toString();
                String pass1 = etSPass1.getText().toString();
                String pass2 = etSPass2.getText().toString();

                svm.registrarUsuario(nombre, apellido, mail, usuario, pass1, pass2, 3);
            }
        });
    }
}