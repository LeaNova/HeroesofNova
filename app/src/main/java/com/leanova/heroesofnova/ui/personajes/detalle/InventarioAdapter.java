package com.leanova.heroesofnova.ui.personajes.detalle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.request.ApiRetrofit;
import com.leanova.heroesofnova.request.PersonajeValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioAdapter extends ArrayAdapter<Object> {
    private LayoutInflater lInflater;
    private List<Object> listaInventario;

    public InventarioAdapter(@NonNull Context context, int resource, @NonNull List<Object> objects) {
        super(context, resource, objects);
        this.lInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaInventario = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null) {
            itemView = lInflater.inflate(R.layout.item_inventario, parent, false);
        }

        Object object = listaInventario.get(position);

        TextView tvNombre_IInv = itemView.findViewById(R.id.tvNombre_IInv);
        Button btMinus_IInv = itemView.findViewById(R.id.btMinus_IInv);
        TextView tvCant_IInv = itemView.findViewById(R.id.tvCant_IInv);
        Button btAdd_IInv = itemView.findViewById(R.id.btAdd_IInv);
        ImageButton btAgregar_IInv = itemView.findViewById(R.id.btAgregar_IInv);

        if(object.getClass() == Armadura.class) {
            Armadura armadura = (Armadura) object;
            tvNombre_IInv.setText(armadura.getNombre());

            btAgregar_IInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                    agregarArmadura(armadura.getIdArmadura(), cant);
                }
            });
        }

        if(object.getClass() == Arma.class) {
            Arma arma = (Arma) object;
            tvNombre_IInv.setText("(" + arma.getCategoria().getNombre() + ") " + arma.getNombre());

            btAgregar_IInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                    agregarArma(arma.getIdArma(), cant);
                }
            });
        }

        if(object.getClass() == Item.class) {
            Item item = (Item) object;
            tvNombre_IInv.setText("(" + item.getTipo().getNombre() + ") " + item.getNombre());

            btAgregar_IInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                    agregarItem(item.getIdItem(), cant);
                }
            });
        }

        if(object.getClass() == Artefacto.class) {
            Artefacto artefacto = (Artefacto) object;
            tvNombre_IInv.setText("(" + artefacto.getSeccion().getNombre() + ") " + artefacto.getNombre());

            btAgregar_IInv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                    agregarArtefacto(artefacto.getIdArtefacto(), cant);
                }
            });
        }

        btMinus_IInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                if(cant > 0) {
                    cant--;
                    tvCant_IInv.setText(cant+"");
                }
            }
        });
        tvCant_IInv.setText(1+"");
        btAdd_IInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cant = Integer.parseInt(tvCant_IInv.getText().toString());
                cant++;
                tvCant_IInv.setText(cant+"");
            }
        });

        return itemView;
    }

    private void agregarArmadura(int id, int cant) {
        String token = ApiRetrofit.obtenerToken(getContext());
        Personaje personaje = PersonajeValues.getPersonaje();

        Call<ResponseBody> agregarPromesa = ApiRetrofit.getServiceApi().agregarArmadura(personaje.getMochilaId(), personaje.getIdPersonaje(), id, cant, token);
        agregarPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Armadura agregada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void agregarArma(int id, int cant) {
        String token = ApiRetrofit.obtenerToken(getContext());
        Personaje personaje = PersonajeValues.getPersonaje();

        Call<ResponseBody> agregarPromesa = ApiRetrofit.getServiceApi().agregarArma(personaje.getMochilaId(), personaje.getIdPersonaje(), id, cant, token);
        agregarPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Arma agregado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void agregarItem(int id, int cant) {
        String token = ApiRetrofit.obtenerToken(getContext());
        Personaje personaje = PersonajeValues.getPersonaje();

        Call<ResponseBody> agregarPromesa = ApiRetrofit.getServiceApi().agregarItem(personaje.getMochilaId(), personaje.getIdPersonaje(), id, cant, token);
        agregarPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Item agregado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }

    private void agregarArtefacto(int id, int cant) {
        String token = ApiRetrofit.obtenerToken(getContext());
        Personaje personaje = PersonajeValues.getPersonaje();

        Call<ResponseBody> agregarPromesa = ApiRetrofit.getServiceApi().agregarArtefacto(personaje.getMochilaId(), personaje.getIdPersonaje(), id, cant, token);
        agregarPromesa.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(), "Artefacto agregado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIerror", t.getMessage());
            }
        });
    }
}
