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

        holder.tvAtk_PI.setText(pj.getAtaque()+"");
        holder.tvAtm_PI.setText(pj.getAtkMagico()+"");
        holder.tvDef_PI.setText(pj.getDefensa()+"");
        holder.tvDfm_PI.setText(pj.getDefMagico()+"");
        holder.tvDex_PI.setText(pj.getDefensa()+"");
        holder.tvEva_PI.setText(pj.getEvasion()+"");
        holder.tvCrt_PI.setText(pj.getCritico()+"");
        holder.tvAcc_PI.setText(pj.getPrecision()+"");

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
        private TextView tvVida_PI, tvNivel_PI, tvNombre_PI, tvRaza_PI, tvClase_PI, tvAtk_PI, tvAtm_PI, tvDef_PI, tvDfm_PI, tvDex_PI, tvEva_PI, tvCrt_PI, tvAcc_PI;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvVida_PI = itemView.findViewById(R.id.tvVida_PI);
            this.tvNivel_PI = itemView.findViewById(R.id.tvNivel_PI);
            this.tvNombre_PI = itemView.findViewById(R.id.tvNombre_PI);
            this.tvRaza_PI = itemView.findViewById(R.id.tvRaza_PI);
            this.tvClase_PI = itemView.findViewById(R.id.tvClase_PI);

            this.tvAtk_PI = itemView.findViewById(R.id.tvAtk_PI);
            this.tvAtm_PI = itemView.findViewById(R.id.tvAtm_PI);
            this.tvDef_PI = itemView.findViewById(R.id.tvDef_PI);
            this.tvDfm_PI = itemView.findViewById(R.id.tvDfm_PI);
            this.tvDex_PI = itemView.findViewById(R.id.tvDex_PI);
            this.tvEva_PI = itemView.findViewById(R.id.tvEva_PI);
            this.tvCrt_PI = itemView.findViewById(R.id.tvCrt_PI);
            this.tvAcc_PI = itemView.findViewById(R.id.tvAcc_PI);
        }
    }
}
