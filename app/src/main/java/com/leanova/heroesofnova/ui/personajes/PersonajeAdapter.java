package com.leanova.heroesofnova.ui.personajes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.modelos.Raza;

import java.util.List;

public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater lInflater;
    private List<Personaje> lista;

    public PersonajeAdapter(Context context, LayoutInflater layoutInflater, List<Personaje> lista) {
        this.context = context;
        this.lInflater = layoutInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.personaje_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Personaje pj = lista.get(position);

        Raza raza = pj.getRaza();
        Clase clase = pj.getClase();

        holder.tvVida_PI.setText(pj.getVida()+"");
        holder.tvNivel_PI.setText(pj.getNivel()+"");
        holder.tvNombre_PI.setText(pj.getNombre());
        holder.tvRaza_PI.setText(raza.getNombre());
        holder.tvClase_PI.setText(clase.getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bPersonaje = new Bundle();
                bPersonaje.putSerializable("personaje", pj);
                Navigation.findNavController(view).navigate(R.id.detallePersonajeFragment, bPersonaje);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVida_PI, tvNivel_PI, tvNombre_PI, tvRaza_PI, tvClase_PI;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvVida_PI = itemView.findViewById(R.id.tvVida_PI);
            this.tvNivel_PI = itemView.findViewById(R.id.tvNivel_PI);
            this.tvNombre_PI = itemView.findViewById(R.id.tvNombre_PI);
            this.tvRaza_PI = itemView.findViewById(R.id.tvRaza_PI);
            this.tvClase_PI = itemView.findViewById(R.id.tvClase_PI);
        }
    }
}
