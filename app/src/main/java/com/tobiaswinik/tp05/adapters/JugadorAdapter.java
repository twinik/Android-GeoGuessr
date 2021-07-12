package com.tobiaswinik.tp05.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tobiaswinik.tp05.R;
import com.tobiaswinik.tp05.Sesion;
import com.tobiaswinik.tp05.entities.Jugador;

import java.util.ArrayList;

public class JugadorAdapter extends ArrayAdapter<Jugador> {
    Context context;
    private int resource;


    public JugadorAdapter(Context context, int resource, ArrayList<Jugador> listaPlayers){
        super(context,resource,listaPlayers);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context) ;
        View view = inflater.inflate(resource,null);
        TextView tvJugador = view.findViewById(R.id.tvJugador);
        TextView tvPuntaje = view.findViewById(R.id.tvPuntaje);
        TextView tvTiempo = view.findViewById(R.id.tvTiempo);

        Jugador player = Sesion.listaPlayers.get(position);

        //Log.d("AccesoAPI", "Nombre player: " + Sesion.nombreActual);

        tvJugador.setText(Sesion.nombreActual);
        tvPuntaje.setText(String.valueOf(player.Puntaje));
        tvTiempo.setText(String.valueOf(player.Tiempo));

        return view;
    }
}
