package com.leanova.heroesofnova.ui.personajes.detalle;

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
import com.leanova.heroesofnova.modelos.InvArtefacto;

import java.util.List;

public class InvArtefactoAdapter extends ArrayAdapter<InvArtefacto> {
    private LayoutInflater lInflater;
    private List<InvArtefacto> listaArtefactos;

    public InvArtefactoAdapter(@NonNull Context context, int resource, @NonNull List<InvArtefacto> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaArtefactos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_inv_artefacto, parent, false);
        }

        InvArtefacto invArtefacto = listaArtefactos.get(position);
        Artefacto artefacto = invArtefacto.getArtefacto();

        TextView tvSeccion_InvArtefacto = itemView.findViewById(R.id.tvSeccion_InvArtefacto);
        TextView tvNombre_InvArtefacto = itemView.findViewById(R.id.tvNombre_InvArtefacto);

        tvSeccion_InvArtefacto.setText("(" + artefacto.getSeccion().getNombre() + ") ");
        tvNombre_InvArtefacto.setText(artefacto.getNombre() + " x" + invArtefacto.getCantidad());

        return itemView;
    }
}
