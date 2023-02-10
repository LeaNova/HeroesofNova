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
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Item;

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
        Item item = invItem.getItem();

        TextView tvTipo_InvItem = itemView.findViewById(R.id.tvTipo_InvItem);
        TextView tvNombre_InvItem = itemView.findViewById(R.id.tvNombre_InvItem);

        tvTipo_InvItem.setText("(" + item.getTipo().getNombre() + ") ");
        tvNombre_InvItem.setText(item.getNombre() + " x" + invItem.getCantidad());

        return itemView;
    }
}
