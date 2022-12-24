package com.leanova.heroesofnova.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;
import com.leanova.heroesofnova.ui.signin.SigninActivity;
import com.leanova.heroesofnova.ui.signin.SigninViewModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private DefaultValues dv;
    private LoginViewModel lvm;
    private EditText etIP, etLMail, etLPass;
    private Button btSetIP, btLLogin, btLSignin;
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
        this.etIP = findViewById(R.id.etIP);
        this.btSetIP = findViewById(R.id.btSetIP);
        /*
        this.btSetIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRetrofit.setIP(etIP.getText().toString());
                dv = new DefaultValues();
            }
        });*/

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