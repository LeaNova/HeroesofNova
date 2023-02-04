package com.leanova.heroesofnova.ui.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private LayoutInflater lInflater;
    private List<Item> listaItems;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = lInflater.inflate(R.layout.item_item, parent, false);
        }

        Item i = listaItems.get(position);

        TextView tvNombre_IItem = itemView.findViewById(R.id.tvNombre_IItem);
        tvNombre_IItem.setText(i.getNombre());

        return itemView;
    }
}
