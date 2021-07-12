package com.tobiaswinik.tp05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tobiaswinik.tp05.MainActivity;
import com.tobiaswinik.tp05.R;
import com.tobiaswinik.tp05.Sesion;

public class NameFragment extends PrimaryFragment {

    View layoutRoot;
    EditText edtNombre;
    Button btnSiguiente;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_name, container, false);
        }

        ObtenerReferencias();

        SetearListeners();

        this.setearTitulo("Inicio");

        return layoutRoot;
    }

    private void ObtenerReferencias() {
        edtNombre = (EditText) layoutRoot.findViewById(R.id.edtNombre);
        btnSiguiente = (Button) layoutRoot.findViewById(R.id.btnSiguiente);
    }

    private void SetearListeners() {
        btnSiguiente.setOnClickListener(btnSiguiente_Click);
    }

    View.OnClickListener btnSiguiente_Click = new View.OnClickListener() {
        @Override
        public void onClick(View V){
            String strNombre;

            MainActivity actividadContenedora = (MainActivity) getActivity();

            strNombre = edtNombre.getText().toString();

            Sesion.nombreActual = strNombre;

            Log.d("Test", strNombre);

            actividadContenedora.irAFragmentObjective();
        }
    };


}