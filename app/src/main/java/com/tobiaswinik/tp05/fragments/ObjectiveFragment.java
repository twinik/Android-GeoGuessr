package com.tobiaswinik.tp05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tobiaswinik.tp05.MainActivity;
import com.tobiaswinik.tp05.R;

public class ObjectiveFragment extends PrimaryFragment {

    View layoutRoot;
    Button btnComenzar, btnRanking;
    ListView lvRanking;
    TextView tvNombre;
    String strNombreMostrado;

    public ObjectiveFragment() {
        strNombreMostrado = "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_objective, container, false);
            ObtenerReferencias();
        }
        SetearListeners();
        if (layoutRoot != null){
            Log.d("Test", strNombreMostrado);
            tvNombre.setText("Buuenas " + strNombreMostrado);
        }

        this.setearTitulo("Objetivo");

        return layoutRoot;
    }

    private void ObtenerReferencias() {
        btnComenzar = (Button) layoutRoot.findViewById(R.id.btnComenzar);
        btnRanking = (Button) layoutRoot.findViewById(R.id.btnRanking);
        tvNombre = (TextView) layoutRoot.findViewById(R.id.tvNombre);
    }

    private void SetearListeners() {
        btnComenzar.setOnClickListener(btnComenzar_Click);
        btnRanking.setOnClickListener(btnRanking_Click);
    }

    View.OnClickListener btnComenzar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View V){
            MainActivity actividadContenedora = (MainActivity) getActivity();
            actividadContenedora.irAFragmentGame();
        }
    };

    View.OnClickListener btnRanking_Click = new View.OnClickListener() {
        @Override
        public void onClick(View V){
            MainActivity actividadContenedora = (MainActivity) getActivity();
            actividadContenedora.irAFragmentRanking();
        }
    };

    public void setMensaje(String strMensaje){
        strNombreMostrado = strMensaje;
        if (layoutRoot != null){
            tvNombre.setText(strNombreMostrado);
        }

    }
}