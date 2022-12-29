package com.leanova.heroesofnova.ui.mochilas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Raza;

import java.util.List;

public class MochilaAdapter extends ArrayAdapter<Mochila> {
    private LayoutInflater lInflater;
    private List<Mochila> listaMochilas;

    public MochilaAdapter(@NonNull Context context, int resource, @NonNull List<Mochila> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaMochilas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.mochila_item, parent, false);
        }

        Mochila m = listaMochilas.get(position);

        TextView tvNombre_MI = itemView.findViewById(R.id.tvNombre_MI);
        tvNombre_MI.setText(m.getNombre());

        return itemView;
    }
}
