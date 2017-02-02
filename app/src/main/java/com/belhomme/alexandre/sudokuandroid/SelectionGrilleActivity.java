package com.belhomme.alexandre.sudokuandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class SelectionGrilleActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private int niveau;
    private ListView liste_grilles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_grille);
        Bundle objetbunble = this.getIntent().getExtras();
        this.niveau = objetbunble.getInt("niveau");
        liste_grilles = (ListView) findViewById(R.id.liste_selection_grille);

        String[] listeGrilles = new String[]{"Grille1", "Grille2", "Grille3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listeGrilles);
        this.liste_grilles.setAdapter(adapter);
        this.liste_grilles.setOnItemClickListener(this);


        //temporaire
        TextView t = (TextView) findViewById(R.id.voir_niveau);
        t.setText("niveau " + this.niveau);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intention = new Intent(this, GrilleActivity.class);
        intention.putExtra("id", id);
        startActivity(intention);
    }
}
