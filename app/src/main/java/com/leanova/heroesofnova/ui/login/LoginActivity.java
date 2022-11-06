package com.leanova.heroesofnova.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.request.DefaultValues;
import com.leanova.heroesofnova.ui.signin.SigninActivity;
import com.leanova.heroesofnova.ui.signin.SigninViewModel;

public class LoginActivity extends AppCompatActivity {
    private DefaultValues dv;
    private LoginViewModel lvm;
    private EditText etLMail, etLPass;
    private Button btLLogin, btLSignin;
    private TextView textLAviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dv = new DefaultValues();
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        inicializarVista();
        lvm.getAviso().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textLAviso.setText(s);
            }
        });
    }

    private void inicializarVista() {
        this.etLMail = findViewById(R.id.etLMail);
        this.etLPass = findViewById(R.id.etLPass);

        this.btLLogin = findViewById(R.id.btLLogin);
        this.btLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = etLMail.getText().toString();
                String pass = etLPass.getText().toString();
                lvm.iniciarSesion(mail, pass);
            }
        });

        this.btLSignin = findViewById(R.id.btLSignin);
        this.btLSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvm.registrarse();
            }
        });

        this.textLAviso = findViewById(R.id.textLAviso);
    }
}