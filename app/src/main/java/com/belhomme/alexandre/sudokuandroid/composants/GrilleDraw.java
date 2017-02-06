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
import com.belhomme.alexandre.sudokuandroid.objects.DrawableGrille;
import com.belhomme.alexandre.sudokuandroid.objects.Grille;

public class GrilleDraw extends View implements View.OnTouchListener {

    private Grille grille;
    public int fontColor;
    public int gridColor;


    private Cellule celluleEnDeplacement;
    private Cellule celluleHover;

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
        Paint paintMouvement = new Paint();

        final float cellBorder = 4;
        paintText.setTextSize(16 * getResources().getDisplayMetrics().density);

        paintText.setColor(this.fontColor);
        paintGrid.setColor(this.gridColor);
        paintMouvement.setColor(Color.GREEN);
        paintGrid.setStyle(Paint.Style.STROKE);
        paintGrid.setStrokeWidth(cellBorder);

        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();

        topGrille = topGrille != null ? topGrille : new DrawableGrille(canvasWidth, canvasHeight, 9, cellBorder, 0);
        bottomGrille = bottomGrille != null ? bottomGrille : new DrawableGrille(canvasWidth, canvasHeight, 3, cellBorder, topGrille.getGrilleSize() + 2 * cellBorder);

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Paint p;
                if (this.celluleHover != null && this.celluleHover.getX() == x && this.celluleHover.getY() == y) {
                    if (this.grille.canAdd(this.celluleHover))
                        paintMouvement.setColor(Color.GREEN);
                    else if (this.grille.find(x, y).isReadOnly())
                        paintMouvement.setColor(Color.GRAY);
                    else
                        paintMouvement.setColor(Color.RED);
                    p = paintMouvement;
                } else
                    p = paintGrid;
                canvas.drawRect(topGrille.getXposition(x), topGrille.getYposition(y), topGrille.getXposition(x + 1), topGrille.getYposition(y + 1), p);
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
                Paint p;
                if (this.celluleEnDeplacement != null && this.celluleEnDeplacement.getValeur() == value) {
                    paintMouvement.setColor(Color.GREEN);
                    p = paintMouvement;
                } else
                    p = paintGrid;
                String str = "" + value;
                Rect r = bottomGrille.getTextAjust(str, paintText);
                canvas.drawRect(bottomGrille.getXposition(x), +bottomGrille.getYposition(y), bottomGrille.getXposition(x + 1), bottomGrille.getYposition(y + 1), p);
                canvas.drawText(str, bottomGrille.getXposition(x) + r.width(), bottomGrille.getYposition(y + 1) - cellBorder - r.height(), paintText);
            }
        }

        if (celluleEnDeplacement != null) {
            canvas.drawRect(celluleEnDeplacement.getX(), celluleEnDeplacement.getY(), celluleEnDeplacement.getX() + topGrille.getCellSizeWithoutBorder(), celluleEnDeplacement.getY() + topGrille.getCellSizeWithoutBorder(), paintMouvement);
            canvas.drawText("" + celluleEnDeplacement.getValeur(), 0, canvasHeight, paintText);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX() - topGrille.getCellSizeWithoutBorder() / 2;
        float y = event.getY() - topGrille.getCellSizeWithoutBorder() / 2;

        Cellule positionSurGrille = topGrille.getCursorPosition(x, y);
        Cellule positionSurNombres = bottomGrille.getCursorPosition(x, y);

        if (!this.grille.isCleared()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (positionSurNombres != null)
                        this.celluleEnDeplacement = new Cellule((int) x, (int) y, positionSurNombres.getValeur());
                    break;
                case MotionEvent.ACTION_UP:
                    if (this.celluleHover != null && this.grille.canAdd(this.celluleHover)) {
                        this.grille.add(this.celluleHover);
                    }
                    this.celluleEnDeplacement = null;
                    this.celluleHover = null;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (this.celluleEnDeplacement != null) {
                        this.celluleEnDeplacement = new Cellule((int) x, (int) y, this.celluleEnDeplacement.getValeur());
                        if (positionSurGrille != null) {
                            this.celluleHover = new Cellule(positionSurGrille.getX(), positionSurGrille.getY(), celluleEnDeplacement.getValeur());
                        } else {
                            this.celluleHover = null;
                        }
                    }
            }
            this.invalidate();
        }
        return true;
    }

}
