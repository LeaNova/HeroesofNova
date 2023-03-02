package com.leanova.heroesofnova.ui.items;

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
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Rareza;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> implements Filterable {
    private LayoutInflater lInflater;
    private List<Item> listaItems;
    private List<Item> listaFiltrada;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaItems = objects;
        this.listaFiltrada = objects;
    }

    @Override
    public int getCount() {
        return listaFiltrada.size();
    }

    @Nullable
    @Override
    public Item getItem(int position) {
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

        if (itemView == null) {
            itemView = lInflater.inflate(R.layout.item_item, parent, false);
        }

        Item i = listaFiltrada.get(position);
        Rareza r = i.getRareza();

        TextView tvRareza_IItem = itemView.findViewById(R.id.tvRareza_IItem);
        TextView tvNombre_IItem = itemView.findViewById(R.id.tvNombre_IItem);
        tvRareza_IItem.setText(r.getIniciales() + " ");
        tvRareza_IItem.setTextColor(Color.parseColor(r.getCodColor()));
        tvNombre_IItem.setText(i.getNombre());

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
                    results.values = listaItems;
                    results.count = listaItems.size();
                } else {
                    ArrayList<Item> filterResultsData = new ArrayList<Item>();

                    for(Item data : listaItems) {
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
                listaFiltrada = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
