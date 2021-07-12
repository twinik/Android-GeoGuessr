package com.tobiaswinik.tp05.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tobiaswinik.tp05.R;
import com.tobiaswinik.tp05.Sesion;
import com.tobiaswinik.tp05.adapters.JugadorAdapter;
import com.tobiaswinik.tp05.entities.Jugador;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RankingFragment extends PrimaryFragment {

    View layoutRoot;
    ListView lvRanking;
    ArrayList<Jugador> listaRecibida;
    Button btnVolver;

    public RankingFragment() {
        listaRecibida = Sesion.listaPlayers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_ranking, container, false);
            ObtenerReferencias();
        }
        CargarDatos();
        this.setearTitulo("Ranking");

        return layoutRoot;
    }

    private void ObtenerReferencias() {
        lvRanking = (ListView) layoutRoot.findViewById(R.id.lvRanking);
    }

    private void CargarDatos(){
        ArrayList<Jugador> datosArrayList;
        ArrayAdapter<Jugador> adapter;
        datosArrayList=Sesion.listaPlayers;
        adapter = new JugadorAdapter(getContext(), R.layout.item_jugador, datosArrayList);
        lvRanking.setAdapter(adapter);
    }


}