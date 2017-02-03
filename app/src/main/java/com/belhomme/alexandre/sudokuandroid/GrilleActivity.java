package com.belhomme.alexandre.sudokuandroid;

import android.os.Bundle;
import android.widget.TextView;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;
import com.belhomme.alexandre.sudokuandroid.composants.GrilleDraw;
import com.belhomme.alexandre.sudokuandroid.objects.Cellule;
import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleActivity extends BaseAppCompatActivity {
    private long id;
    private TextView id_grille;
    private GrilleDraw grille_draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);

        this.id_grille = (TextView) findViewById(R.id.id_grille);
        this.grille_draw = (GrilleDraw) findViewById(R.id.grille_draw);

        Bundle objetbunble = this.getIntent().getExtras();
        this.id = objetbunble.getLong("id");

        Grille g = new Grille();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x == y)
                    g.add(new Cellule(x, y, x + 1));
                else
                    g.add(new Cellule(x, y));
            }
        }
        this.grille_draw.setGrille(g);
        this.id_grille.setText("" + this.id);
    }

}