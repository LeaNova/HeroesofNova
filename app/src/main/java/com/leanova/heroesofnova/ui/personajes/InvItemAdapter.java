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
import com.leanova.heroesofnova.modelos.InvItem;

import java.util.List;

public class InvItemAdapter extends ArrayAdapter<InvItem> {
    private LayoutInflater lInflater;
    private List<InvItem> listaInvItems;

    public InvItemAdapter(@NonNull Context context, int resource, @NonNull List<InvItem> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaInvItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_inv_item, parent, false);
        }

        InvItem invItem = listaInvItems.get(position);

        TextView tvNombre_InvItem = itemView.findViewById(R.id.tvNombre_InvItem);
        tvNombre_InvItem.setText(invItem.getItem().getNombre() + " x" + invItem.getCantidad());

        return itemView;
    }
}
