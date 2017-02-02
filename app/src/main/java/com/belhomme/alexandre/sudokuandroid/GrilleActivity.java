package com.belhomme.alexandre.sudokuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GrilleActivity extends AppCompatActivity {
    private long id;
    private TextView id_grille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);

        Bundle objetbunble = this.getIntent().getExtras();
        this.id = objetbunble.getLong("id");
        this.id_grille = (TextView) findViewById(R.id.id_grille);
        this.id_grille.setText("" + this.id);
    }
}
