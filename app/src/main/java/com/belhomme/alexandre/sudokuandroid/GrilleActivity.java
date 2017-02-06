package com.belhomme.alexandre.sudokuandroid;

import android.os.Bundle;
import android.widget.TextView;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;
import com.belhomme.alexandre.sudokuandroid.composants.GrilleDraw;
import com.belhomme.alexandre.sudokuandroid.objects.Cellule;
import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleActivity extends BaseAppCompatActivity {
    private GrilleDraw grille_draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);

        this.grille_draw = (GrilleDraw) findViewById(R.id.grille_draw);

        Bundle objetbunble = this.getIntent().getExtras();
        String niveau = objetbunble.getString("niveau");

        Grille g = new Grille();
        int position = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int valeur;
                try {
                    valeur = Integer.parseInt("" + niveau.charAt(position));
                } catch (Exception e) {
                    valeur = 0;
                }
                g.add(new Cellule(x, y, valeur, valeur != 0));
                position++;
            }
        }
        this.grille_draw.setGrille(g);
    }

}