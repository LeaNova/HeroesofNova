package com.leanova.heroesofnova.ui.personajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.InvArmadura;

import java.util.List;

public class InvArmaduraAdapter extends ArrayAdapter<InvArmadura> {
    private LayoutInflater lInflater;
    private List<InvArmadura> listaInvArmaduras;

    public InvArmaduraAdapter(@NonNull Context context, int resource, @NonNull List<InvArmadura> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaInvArmaduras = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_inv_armadura, parent, false);
        }

        InvArmadura invArmadura = listaInvArmaduras.get(position);

        TextView tvNombre_InvArmadura = itemView.findViewById(R.id.tvNombre_InvArmadura);
        tvNombre_InvArmadura.setText(invArmadura.getArmadura().getNombre() + " x" + invArmadura.getCantidad());

        return itemView;
    }
}
