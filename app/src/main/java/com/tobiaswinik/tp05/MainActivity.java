package com.tobiaswinik.tp05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.tobiaswinik.tp05.entities.Jugador;
import com.tobiaswinik.tp05.fragments.GameFragment;
import com.tobiaswinik.tp05.fragments.NameFragment;
import com.tobiaswinik.tp05.fragments.ObjectiveFragment;
import com.tobiaswinik.tp05.fragments.RankingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NameFragment fragmentName;
    GameFragment fragmentGame;
    RankingFragment fragmentRanking;
    ObjectiveFragment fragmentObjective;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrearFragments();

        reemplazarFragment(fragmentName, false);
    }

    private void CrearFragments() {
        fragmentName = new NameFragment();
        fragmentRanking = new RankingFragment();
        fragmentGame = new GameFragment();
        fragmentObjective = new ObjectiveFragment();
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

    public void irAFragmentObjective(){
        reemplazarFragment(fragmentObjective, true);
    }

    public void irAFragmentGame(){
        reemplazarFragment(fragmentGame, true);
    }

    public void irAFragmentRanking(){
        reemplazarFragment(fragmentRanking, true);
    }

}