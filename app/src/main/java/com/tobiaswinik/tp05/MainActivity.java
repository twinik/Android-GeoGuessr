package com.tobiaswinik.tp05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.tobiaswinik.tp05.fragments.GameFragment;
import com.tobiaswinik.tp05.fragments.MapsFragment;
import com.tobiaswinik.tp05.fragments.NameFragment;

public class MainActivity extends AppCompatActivity {

    MapsFragment fragmentMaps;
    NameFragment fragmentName;
    GameFragment fragmentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrearFragments();

        reemplazarFragment(fragmentName, false);
    }

    private void CrearFragments() {
        fragmentName = new NameFragment();
        fragmentMaps = new MapsFragment();
        fragmentGame = new GameFragment();
    }

    public void reemplazarFragment(Fragment fragmento){
        reemplazarFragment(fragmento, true);
    }

    public void reemplazarFragment(Fragment fragmento, boolean blnAddToBackStack) {
        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frameContainer, fragmento, null);
        if (blnAddToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void EnviarMensaje(String strMensaje){
        fragmentGame.setMensaje(strMensaje);
    }

    public void irAFragmentMaps(){
        reemplazarFragment(fragmentMaps);
    }

    public void irAFragmentGame(){
        reemplazarFragment(fragmentGame);
    }

}