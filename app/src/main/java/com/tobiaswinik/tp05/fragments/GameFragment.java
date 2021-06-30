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

    public GameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_juego, container, false);
            ObtenerReferencias();
        }

        SetearListeners();

        this.setearTitulo("Juego");

        return layoutRoot;

    }

    private void SetearListeners() {
    }

    private void ObtenerReferencias() {

    }

    public void ParseandoJSON(){

    }
}