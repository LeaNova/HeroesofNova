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
        View view = lInflater.inflate(R.layout.item_personaje, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Personaje p = lista.get(position);

        Raza raza = p.getRaza();
        Clase clase = p.getClase();

        holder.tvNombre_PI.setText(p.getNombre());
        holder.tvRaza_PI.setText(raza.getNombre() + " (" +  p.getGenero().getNombre() + ")");
        holder.tvClase_PI.setText(clase.getNombre());
        holder.tvVida_PI.setText(p.getVida()+"");
        holder.tvNivel_PI.setText(p.getNivel()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bPersonaje = new Bundle();
                bPersonaje.putSerializable("personaje", p);
                Navigation.findNavController(view).navigate(R.id.detalleFragment, bPersonaje);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre_PI, tvRaza_PI, tvClase_PI, tvVida_PI, tvNivel_PI;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvNombre_PI = itemView.findViewById(R.id.tvNombre_PI);
            this.tvRaza_PI = itemView.findViewById(R.id.tvRaza_PI);
            this.tvClase_PI = itemView.findViewById(R.id.tvClase_PI);
            this.tvVida_PI = itemView.findViewById(R.id.tvVida_PI);
            this.tvNivel_PI = itemView.findViewById(R.id.tvNivel_PI);
        }
    }
}
