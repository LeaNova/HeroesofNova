package com.leanova.heroesofnova.ui.artefactos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Rareza;

import java.util.ArrayList;
import java.util.List;

public class ArtefactoAdapter extends ArrayAdapter<Artefacto> implements Filterable {
    private LayoutInflater lInflater;
    private List<Artefacto> listaArtefactos;
    private List<Artefacto> listaFiltrada;

    public ArtefactoAdapter(@NonNull Context context, int resource, @NonNull List<Artefacto> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaArtefactos = objects;
        this.listaFiltrada = objects;
    }

    @Override
    public int getCount() {
        return listaFiltrada.size();
    }

    @Nullable
    @Override
    public Artefacto getItem(int position) {
        return listaFiltrada.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_artefacto, parent, false);
        }

        Artefacto a = listaFiltrada.get(position);
        Rareza r = a.getRareza();

        TextView tvRareza_IArtefacto = itemView.findViewById(R.id.tvRareza_IArtefacto);
        TextView tvNombre_IArtefacto = itemView.findViewById(R.id.tvNombre_IArtefacto);
        tvRareza_IArtefacto.setText(r.getIniciales() + " ");
        tvRareza_IArtefacto.setTextColor(Color.parseColor(r.getCodColor()));
        tvNombre_IArtefacto.setText(a.getNombre());

        return itemView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                //If there's nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.length() == 0) {
                    results.values = listaArtefactos;
                    results.count = listaArtefactos.size();
                } else {
                    ArrayList<Artefacto> filterResultsData = new ArrayList<Artefacto>();

                    for(Artefacto data : listaArtefactos) {
                        //In this loop, you'll filter through originalData and compare each item to charSequence.
                        //If you find a match, add it to your new ArrayList
                        //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional

                        if(data.getNombre().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filterResultsData.add(data);
                        }
                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listaFiltrada = (ArrayList<Artefacto>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
