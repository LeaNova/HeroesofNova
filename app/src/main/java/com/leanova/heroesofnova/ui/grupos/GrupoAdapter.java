package com.leanova.heroesofnova.ui.grupos;

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
import com.leanova.heroesofnova.modelos.Grupo;

import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater lInflater;
    private List<Grupo> lista;

    public GrupoAdapter(Context context, LayoutInflater lInflater, List<Grupo> lista) {
        this.context = context;
        this.lInflater = lInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.item_grupo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grupo g = lista.get(position);

        holder.tvDisponible_GI.setText(g.getDisponible() ? "Activo" : "No activo");
        holder.tvNombre_GI.setText(g.getNombre());
        holder.tvDescripcion_GI.setText(g.getDescripcion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bGrupo = new Bundle();
                bGrupo.putSerializable("grupo", g);
                Navigation.findNavController(view).navigate(R.id.detalleGrupoFragment, bGrupo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDisponible_GI, tvNombre_GI, tvDescripcion_GI;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDisponible_GI = itemView.findViewById(R.id.tvDisponible_GI);
            this.tvNombre_GI = itemView.findViewById(R.id.tvNombre_GI);
            this.tvDescripcion_GI = itemView.findViewById(R.id.tvDescripcion_GI);
        }
    }
}
