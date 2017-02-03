package com.belhomme.alexandre.sudokuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;

import java.util.ArrayList;

public class SelectionNiveauActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private ArrayList<View> boutons_niveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);

        this.boutons_niveau = new ArrayList<View>();
        this.boutons_niveau.add(findViewById(R.id.nv_1));
        this.boutons_niveau.add(findViewById(R.id.nv_2));
        this.boutons_niveau.add(findViewById(R.id.nv_3));
        for (View v : this.boutons_niveau) {
            v.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        int niveau = this.boutons_niveau.indexOf(v);
        if (niveau != -1) {
            niveau++;
            Intent intention = new Intent(this, SelectionGrilleActivity.class);
            intention.putExtra("niveau", niveau);
            startActivityForResult(intention, 0);
        }
    }
}
