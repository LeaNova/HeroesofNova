package com.leanova.heroesofnova;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.leanova.heroesofnova.databinding.ActivityMainBinding;
import com.leanova.heroesofnova.modelos.Usuario;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.DefaultValues;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;

        String token = ApiRetrofit.obtenerToken(getApplicationContext());

        Call<Usuario> usuarioPromesa = ApiRetrofit.getServiceApi().obtener(token);
        usuarioPromesa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                DefaultValues.setUsuario(response.body());

                binding = ActivityMainBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

                setSupportActionBar(binding.appBarMain.toolbar);

                DrawerLayout drawer = binding.drawerLayout;
                NavigationView navigationView = binding.navView;

                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_home, R.id.nav_grupos, R.id.nav_personajes, R.id.nav_razas, R.id.nav_clases, R.id.nav_mochilas)
                        .setOpenableLayout(drawer)
                        .build();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                NavigationUI.setupActionBarWithNavController((AppCompatActivity) activity, navController, mAppBarConfiguration);
                NavigationUI.setupWithNavController(navigationView, navController);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationView navigationView = binding.navView;
        inicializarHeader(navigationView);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void inicializarHeader(NavigationView nv) {
        View header = nv.getHeaderView(0);

        TextView tvNombre_H = header.findViewById(R.id.tvNombre_H);
        TextView tvMail_H = header.findViewById(R.id.tvMail_H);

        Usuario u = DefaultValues.getUsuario();

        tvNombre_H.setText(u.getNombre() + " " + u.getApellido());
        tvMail_H.setText(u.getMail());
    }
}