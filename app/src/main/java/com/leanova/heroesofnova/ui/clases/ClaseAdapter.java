package com.leanova.heroesofnova.ui.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Clase;

import java.util.List;

public class ClaseAdapter extends ArrayAdapter<Clase> {
    private LayoutInflater lInflater;
    private List<Clase> listaClases;

    public ClaseAdapter(@NonNull Context context, int resource, @NonNull List<Clase> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaClases = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_clase, parent, false);
        }

        Clase c = listaClases.get(position);

        TextView tvNombre_CI = itemView.findViewById(R.id.tvNombre_CI);
        tvNombre_CI.setText(c.getNombre());

        return itemView;
    }


}
