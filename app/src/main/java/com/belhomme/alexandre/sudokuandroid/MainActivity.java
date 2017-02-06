package com.belhomme.alexandre.sudokuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.belhomme.alexandre.sudokuandroid.composants.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private View boutton_lancement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.boutton_lancement = findViewById(R.id.button_lancement);
        this.boutton_lancement.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == this.boutton_lancement) {
            Intent intention = new Intent(this, SelectionNiveauActivity.class);
            startActivityForResult(intention, 0);
        }
    }
}
