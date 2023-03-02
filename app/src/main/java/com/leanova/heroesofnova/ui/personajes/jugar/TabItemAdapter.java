package com.leanova.heroesofnova.ui.personajes.jugar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Jugar;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabItemAdapter extends ArrayAdapter<InvItem> {
    private LayoutInflater lInflater;
    private List<InvItem> listaInvItems;

    public TabItemAdapter(@NonNull Context context, int resource, @NonNull List<InvItem> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaInvItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_tab_item, parent, false);
        }

        InvItem invItem = listaInvItems.get(position);
        Item item = invItem.getItem();

        TextView tvNombre_TabItem = itemView.findViewById(R.id.tvNombre_TabItem);
        TextView tvCant_TabItem = itemView.findViewById(R.id.tvCant_TabItem);
        Button btUsar_TabItem = itemView.findViewById(R.id.btUsar_TabItem);

        tvNombre_TabItem.setText(item.getNombre());
        tvCant_TabItem.setText("x" + invItem.getCantidad());
        btUsar_TabItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cant = Integer.parseInt(tvCant_TabItem.getText().toString().replace("x", ""));

                if(cant > 0) {
                    consumirItem(invItem);
                    cant--;
                    tvCant_TabItem.setText("x" + cant);
                } else {
                    Toast.makeText(getContext(), "Item agotado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return itemView;
    }

    private void consumirItem(InvItem invItem) {
        Item item = invItem.getItem();
        String token = ApiRetrofit.obtenerToken(getContext());

        Call<InvItem> usarItemPromera = ApiRetrofit.getServiceApi().usarItem(invItem.getMochilaId(), invItem.getPersonajeId(), invItem.getItemId(), token);
        usarItemPromera.enqueue(new Callback<InvItem>() {
            @Override
            public void onResponse(Call<InvItem> call, Response<InvItem> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "usado " + item.getNombre(), Toast.LENGTH_SHORT).show();
                    usarItem(item);
                } else {
                    Toast.makeText(getContext(), "Item agotado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvItem> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void usarItem(Item item) {
        Jugar jugar = PersonajeValues.getJugar();
        jugar.getPersonaje().usarItem(item);
        PersonajeValues.setJugar(jugar);
    }
}
