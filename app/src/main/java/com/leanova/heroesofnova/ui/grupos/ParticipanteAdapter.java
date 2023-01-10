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
import com.leanova.heroesofnova.modelos.Participante;

import java.util.List;

public class ParticipanteAdapter extends ArrayAdapter<Participante> {
    private LayoutInflater lInflater;
    private List<Participante> listaParticipantes;

    public ParticipanteAdapter(@NonNull Context context, int resource, @NonNull List<Participante> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaParticipantes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_participante, parent, false);
        }

        Participante p = listaParticipantes.get(position);

        TextView tvNombre_ParI = itemView.findViewById(R.id.tvNombre_ParI);
        TextView tvParsonaje_ParI = itemView.findViewById(R.id.tvParsonaje_ParI);
        tvNombre_ParI.setText(p.getJugador().getUsuario());
        tvParsonaje_ParI.setText(p.getPersonaje().getNombre() + " (Nivel " + p.getPersonaje().getNivel() + ")");

        return itemView;
    }
}
