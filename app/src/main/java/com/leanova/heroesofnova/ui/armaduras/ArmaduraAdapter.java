package com.leanova.heroesofnova.ui.armaduras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Armadura;

import java.util.List;

public class ArmaduraAdapter extends ArrayAdapter<Armadura> {
    private LayoutInflater lInflater;
    private List<Armadura> listaArmaduras;

    public ArmaduraAdapter(@NonNull Context context, int resource, @NonNull List<Armadura> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaArmaduras = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_armadura, parent, false);
        }

        Armadura a = listaArmaduras.get(position);

        TextView tvNombre_IArmadura = itemView.findViewById(R.id.tvNombre_IArmadura);
        tvNombre_IArmadura.setText(a.getNombre());

        return itemView;
    }
}
