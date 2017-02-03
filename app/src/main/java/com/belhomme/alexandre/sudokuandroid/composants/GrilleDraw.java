package com.belhomme.alexandre.sudokuandroid.composants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleDraw extends View {

    private Grille grille;
    public int bgColor;
    public String test;

    public GrilleDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(bgColor);
        canvas.drawCircle(50, 50, 50, paint);
        canvas.drawText(test, 100, 100, paint);
        /*for (Cercle c : this.cercles) {
            c.draw(canvas);
        }*/
    }
}