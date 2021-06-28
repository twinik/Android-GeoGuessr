package com.tobiaswinik.tp05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobiaswinik.tp05.R;


public class GameFragment extends PrimaryFragment {

    View layoutRoot;
    TextView tvNombre;
    String strNombreMostrado;

    public GameFragment() {
        strNombreMostrado = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_juego, container, false);
            ObtenerReferencias();
        }

        tvNombre.setText(strNombreMostrado);

        SetearListeners();

        this.setearTitulo("Juego");

        return layoutRoot;

    }

    private void SetearListeners() {
    }

    private void ObtenerReferencias() {
        tvNombre = (TextView) layoutRoot.findViewById(R.id.tvNombre);
    }

    public void setMensaje(String strMensaje){
        strNombreMostrado = strMensaje;
        if (layoutRoot != null){
            tvNombre.setText(strNombreMostrado);
        }

    }
}