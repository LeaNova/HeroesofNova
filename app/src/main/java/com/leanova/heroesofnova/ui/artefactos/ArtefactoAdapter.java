package com.leanova.heroesofnova.ui.artefactos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Artefacto;

import java.util.List;

public class ArtefactoAdapter extends ArrayAdapter<Artefacto> {
    private LayoutInflater lInflater;
    private List<Artefacto> listaArtefactos;

    public ArtefactoAdapter(@NonNull Context context, int resource, @NonNull List<Artefacto> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaArtefactos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_artefacto, parent, false);
        }

        Artefacto a = listaArtefactos.get(position);

        TextView tvNombre_IArtefacto = itemView.findViewById(R.id.tvNombre_IArtefacto);
        tvNombre_IArtefacto.setText(a.getNombre());

        return itemView;
    }
}
