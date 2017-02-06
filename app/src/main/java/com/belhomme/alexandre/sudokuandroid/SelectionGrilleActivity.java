package com.belhomme.alexandre.sudokuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SelectionGrilleActivity extends BaseAppCompatActivity implements ListView.OnItemClickListener {
    private int niveau;
    private ListView liste_grilles;
    private ArrayList<String> grilles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_grille);
        Bundle objetbunble = this.getIntent().getExtras();
        this.niveau = objetbunble.getInt("niveau");
        liste_grilles = (ListView) findViewById(R.id.liste_selection_grille);
        this.grilles = new ArrayList<>();
        try {
            String levels = this.readFileAsString(getResources().getAssets().open(this.niveau + ".txt"));
            for (String lvl : levels.split("\r\n")) {
                this.grilles.add(lvl);
            }
        } catch (IOException e) {
        }

        String[] labels = new String[this.grilles.size()];

        for (int i = 0; i < grilles.size(); i++) {
            labels[i] = "niveau " + (i + 1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, labels);
        this.liste_grilles.setAdapter(adapter);
        this.liste_grilles.setOnItemClickListener(this);


        String[] niveauLabels = new String[]{"faciles", "moyens", "difficiles"};

        TextView t = (TextView) findViewById(R.id.voir_niveau);
        t.setText("niveau " + niveauLabels[(int) this.niveau]);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intention = new Intent(this, GrilleActivity.class);
        intention.putExtra("niveau", this.grilles.get(position));
        startActivity(intention);
    }

    private String readFileAsString(InputStream filePath)
            throws java.io.IOException {
        StringBuilder donneFichier = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(filePath));
        char[] buf = new char[2];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != 1) {
            donneFichier.append(buf, 0, numRead);
        }
        reader.close();
        return donneFichier.toString();

    }
}
