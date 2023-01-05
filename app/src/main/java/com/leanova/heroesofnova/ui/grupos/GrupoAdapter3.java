package com.leanova.heroesofnova.ui.grupos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Grupo;

import java.util.List;

public class GrupoAdapter3 extends ArrayAdapter<Grupo> {
    private LayoutInflater lInflater;
    private List<Grupo> listaGrupo;

    public GrupoAdapter3(@NonNull Context context, int resource, @NonNull List<Grupo> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaGrupo = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.grupo_item_3, parent, false);
        }

        Grupo g = listaGrupo.get(position);

        TextView tvNombre_GI3 = itemView.findViewById(R.id.tvNombre_GI3);
        tvNombre_GI3.setText(g.getNombre());

        return itemView;
    }
}
