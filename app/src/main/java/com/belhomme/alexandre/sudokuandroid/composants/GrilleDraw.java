package com.belhomme.alexandre.sudokuandroid.composants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleDraw extends View implements View.OnTouchListener {

    private Grille grille;
    public int fontColor;
    public int gridColor;
    private int nombreEncours;

    public GrilleDraw(Context context, AttributeSet attcellWidth) {
        super(context, attcellWidth);
        this.gridColor = Color.BLACK;
        this.fontColor = Color.WHITE;
        this.nombreEncours = 0;

    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paintGrid = new Paint();
        Paint paintText = new Paint();

        paintText.setTextSize(16 * getResources().getDisplayMetrics().density);
        final float cellBorder = 4; // rectBorder
        final float canvasWidth = canvas.getWidth();
        final float canvasHeight = canvas.getHeight();

        paintText.setColor(this.fontColor);
        paintGrid.setColor(this.gridColor);
        paintGrid.setStyle(Paint.Style.STROKE);
        paintGrid.setStrokeWidth(cellBorder);

        DrawableGrille topGrille = new DrawableGrille(canvasWidth, canvasHeight, 9, cellBorder, 0);
        DrawableGrille bottomGrille = new DrawableGrille(canvasWidth, canvasHeight, 3, cellBorder, topGrille.getGrilleSize() + 3 * cellBorder);

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                canvas.drawRect(topGrille.getXposition(x), topGrille.getYposition(y), topGrille.getXposition(x + 1), topGrille.getYposition(y + 1), paintGrid);
                if (grille != null && grille.find(x, y) != null && grille.find(x, y).getValeur() != 0) {
                    String str = "" + grille.find(x, y).getValeur();
                    Rect r = topGrille.getTextAjust(str, paintText);
                    canvas.drawText(str, topGrille.getXposition(x) + r.width(), topGrille.getYposition(y + 1) - cellBorder - r.height(), paintText);
                }
            }
        }

        int value = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                value++;
                String str = "" + value;
                Rect r = bottomGrille.getTextAjust(str, paintText);
                canvas.drawRect(bottomGrille.getXposition(x), +bottomGrille.getYposition(y), bottomGrille.getXposition(x + 1), bottomGrille.getYposition(y + 1), paintGrid);
                canvas.drawText(str, bottomGrille.getXposition(x) + r.width(), bottomGrille.getYposition(y + 1) - cellBorder - r.height(), paintText);
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = (float) event.getX();
        float y = (float) event.getY();
        switch (event.getAction()) {
            /*case MotionEvent.ACTION_DOWN:
                cercles.add(new Cercle(x, y, 50));
                break;
            case MotionEvent.ACTION_MOVE:
                Cercle c = cercles.getLast();
                c.rayon = (int) Math.sqrt(Math.pow(x - c.x, 2) + Math.pow(y - c.y, 2));
                break;*/
        }
        this.invalidate();
        return true;
    }
}

class DrawableGrille {
    private float cellSizeWithoutBorder;
    private float borderAroundCells;
    private float numberOfCells;
    private float widthMargin;
    private float heightM;
    private float grilleSize;
    private Rect r;

    public DrawableGrille(float widthContainer, float heightContainer, float numberOfCells, float border, float heightMargin) {
        heightM = heightMargin;
        heightContainer = heightContainer - heightM;
        borderAroundCells = border;
        this.r = new Rect();
        final float cellSizeFromWidth = (widthContainer / numberOfCells) - borderAroundCells;
        final float cellSizeFromHeight = (heightContainer / numberOfCells) - borderAroundCells;
        cellSizeWithoutBorder = cellSizeFromHeight > cellSizeFromWidth ? cellSizeFromWidth : cellSizeFromHeight;
        cellSizeWithoutBorder = cellSizeWithoutBorder - 2 * borderAroundCells;
        grilleSize = numberOfCells * (borderAroundCells + cellSizeWithoutBorder) + borderAroundCells;
        widthMargin = (widthContainer - grilleSize) / 2;
    }

    public float getGrilleSize() {
        return grilleSize;
    }


    public float getCellSizeWithoutBorder() {
        return cellSizeWithoutBorder;
    }

    public float getNumberOfCells() {
        return numberOfCells;
    }

    public float getXposition(float x) {
        return widthMargin + x * (cellSizeWithoutBorder + borderAroundCells) + borderAroundCells;
    }

    public float getYposition(float y) {
        return heightM + y * (cellSizeWithoutBorder + borderAroundCells) + borderAroundCells;
    }

    public Rect getTextAjust(String texte, Paint paint) {
        paint.getTextBounds(texte, 0, 1, r);
        return new Rect(0, 0, (int) (cellSizeWithoutBorder - 1.5 * r.width()) / 2, (int) (cellSizeWithoutBorder - r.height()) / 2);
    }


}