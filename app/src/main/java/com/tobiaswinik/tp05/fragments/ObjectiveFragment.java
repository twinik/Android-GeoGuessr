package com.tobiaswinik.tp05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobiaswinik.tp05.R;

public class ObjectiveFragment extends PrimaryFragment {

    View layoutRoot;

    public ObjectiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (layoutRoot == null){
            layoutRoot = inflater.inflate(R.layout.fragment_objective, container, false);
        }

        ObtenerReferencias();

        SetearListeners();

        return layoutRoot;
    }

    private void ObtenerReferencias() {

    }

    private void SetearListeners() {

    }
}