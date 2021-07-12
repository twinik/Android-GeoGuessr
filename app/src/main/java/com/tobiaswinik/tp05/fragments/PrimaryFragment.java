package com.tobiaswinik.tp05.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobiaswinik.tp05.MainActivity;
import com.tobiaswinik.tp05.R;

public class PrimaryFragment extends Fragment {

    public void setearTitulo(String strTitulo) {
        MainActivity actividadContenedora;
        actividadContenedora = (MainActivity) getActivity();
        assert actividadContenedora != null;
        actividadContenedora.setTitle(strTitulo);
    }
}