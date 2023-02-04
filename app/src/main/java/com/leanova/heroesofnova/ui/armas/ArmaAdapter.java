package com.leanova.heroesofnova.ui.armas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Arma;

import java.util.List;

public class ArmaAdapter extends ArrayAdapter<Arma> {
    private LayoutInflater lInflater;
    private List<Arma> listaArmas;

    public ArmaAdapter(@NonNull Context context, int resource, @NonNull List<Arma> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaArmas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_arma, parent, false);
        }

        Arma a = listaArmas.get(position);

        TextView tvNombre_IArma = itemView.findViewById(R.id.tvNombre_IArma);
        tvNombre_IArma.setText(a.getNombre());

        return itemView;
    }
}
