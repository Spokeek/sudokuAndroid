package com.belhomme.alexandre.sudokuandroid.composants;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

public abstract class BaseAppCompatActivity extends Activity {

    public final int bgColor;
    public final int position;
    //public final int ftColor;

    public BaseAppCompatActivity() {
        this.bgColor = Color.parseColor("#e67e22");
        this.position = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setRequestedOrientation(position);
        this.getWindow().getDecorView().setBackgroundColor(this.bgColor);
    }
}
