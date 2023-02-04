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
import com.leanova.heroesofnova.modelos.InvArma;

import java.util.List;

public class InvArmaAdapter extends ArrayAdapter<InvArma> {
    private LayoutInflater lInflater;
    private List<InvArma> listaInvArmas;

    public InvArmaAdapter(@NonNull Context context, int resource, @NonNull List<InvArma> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaInvArmas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_inv_arma, parent, false);
        }

        InvArma invArma = listaInvArmas.get(position);

        TextView tvNombre_InvArma = itemView.findViewById(R.id.tvNombre_InvArma);
        tvNombre_InvArma.setText(invArma.getArma().getNombre() + " x" + invArma.getCantidad());

        return itemView;
    }
}
