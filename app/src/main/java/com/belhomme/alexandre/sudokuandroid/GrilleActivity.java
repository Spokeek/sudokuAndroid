package com.belhomme.alexandre.sudokuandroid;

import android.os.Bundle;
import android.widget.TextView;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;
import com.belhomme.alexandre.sudokuandroid.composants.GrilleDraw;

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
        this.id_grille.setText("" + this.id);
        this.grille_draw.bgColor = this.bgColor;
        this.grille_draw.test = "coucou n° " + id;
    }

}