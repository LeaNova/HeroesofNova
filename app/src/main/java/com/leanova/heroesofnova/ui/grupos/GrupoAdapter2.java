package com.leanova.heroesofnova.ui.grupos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.modelos.Personaje;

import java.util.List;

public class GrupoAdapter2 extends RecyclerView.Adapter<GrupoAdapter2.ViewHolder> {
    private Context context;
    private LayoutInflater lInflater;
    private List<Participante> lista;

    public GrupoAdapter2(Context context, LayoutInflater lInflater, List<Participante> lista) {
        this.context = context;
        this.lInflater = lInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public GrupoAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.grupo_item_2, parent, false);

        return new GrupoAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrupoAdapter2.ViewHolder holder, int position) {
        Participante p = lista.get(position);

        Grupo g = p.getGrupo();
        Personaje pj = p.getPersonaje();

        holder.tvGrupo_GI2.setText(g.getNombre());
        holder.tvMaster_GI2.setText(g.getMaster().getUsuario());
        holder.tvPersonaje_GI2.setText(pj.getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGrupo_GI2, tvMaster_GI2, tvPersonaje_GI2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvGrupo_GI2 = itemView.findViewById(R.id.tvGrupo_GI2);
            this.tvMaster_GI2 = itemView.findViewById(R.id.tvMaster_GI2);
            this.tvPersonaje_GI2 = itemView.findViewById(R.id.tvPersonaje_GI2);
        }
    }
}
