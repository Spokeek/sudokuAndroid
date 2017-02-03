package com.belhomme.alexandre.sudokuandroid.composants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.belhomme.alexandre.sudokuandroid.objects.Cellule;
import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleDraw extends View implements View.OnTouchListener {

    private Grille grille;
    public int fontColor;
    public int gridColor;

    private Cellule celluleEnDeplacement;

    private DrawableGrille topGrille;
    private DrawableGrille bottomGrille;

    public GrilleDraw(Context context, AttributeSet attcellWidth) {
        super(context, attcellWidth);
        setOnTouchListener(this);
        this.gridColor = Color.BLACK;
        this.fontColor = Color.WHITE;
        this.celluleEnDeplacement = null;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paintGrid = new Paint();
        Paint paintText = new Paint();

        final float cellBorder = 4;
        paintText.setTextSize(16 * getResources().getDisplayMetrics().density);

        paintText.setColor(this.fontColor);
        paintGrid.setColor(this.gridColor);
        paintGrid.setStyle(Paint.Style.STROKE);
        paintGrid.setStrokeWidth(cellBorder);

        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();

        topGrille = topGrille != null ? topGrille : new DrawableGrille(canvasWidth, canvasHeight, 9, cellBorder, 0);
        bottomGrille = bottomGrille != null ? bottomGrille : new DrawableGrille(canvasWidth, canvasHeight, 3, cellBorder, topGrille.getGrilleSize() + 3 * cellBorder);

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

        if (celluleEnDeplacement != null) {
            Paint paintMouvement = new Paint();
            paintMouvement.setColor(Color.GREEN);
            canvas.drawRect(celluleEnDeplacement.getX(), celluleEnDeplacement.getY(), celluleEnDeplacement.getX() + 100, celluleEnDeplacement.getY() + 100, paintMouvement);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = (float) event.getX();
        float y = (float) event.getY();

        Rect positionSurGrille = topGrille.getCursorPosition(x, y);
        Rect positionSurNombres = bottomGrille.getCursorPosition(x, y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                celluleEnDeplacement = null;
                break;
            case MotionEvent.ACTION_MOVE:
                celluleEnDeplacement = new Cellule((int) x, (int) y, 5);
                break;
        }
        this.invalidate();
        return true;
    }

}

class DrawableGrille {
    private float cellSizeWithoutBorder;
    private float borderAroundCells;
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

    public Rect getCursorPosition(float positionX, float positionY) {
        for()
    }
}