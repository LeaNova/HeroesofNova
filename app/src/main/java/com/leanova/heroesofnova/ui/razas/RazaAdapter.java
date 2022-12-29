package com.leanova.heroesofnova.ui.razas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Raza;

import java.util.List;

public class RazaAdapter extends ArrayAdapter<Raza> {
    private LayoutInflater lInflater;
    private List<Raza> listaRazas;

    public RazaAdapter(@NonNull Context context, int resource, @NonNull List<Raza> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaRazas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.raza_item, parent, false);
        }

        Raza r = listaRazas.get(position);

        TextView tvNombre_RI = itemView.findViewById(R.id.tvNombre_RI);
        tvNombre_RI.setText(r.getNombre());

        return itemView;
    }
}
