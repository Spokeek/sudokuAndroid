package com.belhomme.alexandre.sudokuandroid.objects;

import android.graphics.Paint;
import android.graphics.Rect;

public class DrawableGrille {
    private float cellSizeWithoutBorder;
    private float borderAroundCells;
    private float widthMargin;
    private float heightM;
    private float grilleSize;
    private float numberOfCells;
    private Rect r;

    public DrawableGrille(float widthContainer, float heightContainer, float numberOfCells, float border, float heightMargin) {
        this.heightM = heightMargin;
        this.numberOfCells = numberOfCells;
        heightContainer = heightContainer - heightM;
        this.borderAroundCells = border;
        this.r = new Rect();
        final float cellSizeFromWidth = (widthContainer / this.numberOfCells) - this.borderAroundCells;
        final float cellSizeFromHeight = (heightContainer / this.numberOfCells) - this.borderAroundCells;
        this.cellSizeWithoutBorder = cellSizeFromHeight > cellSizeFromWidth ? cellSizeFromWidth : cellSizeFromHeight;
        this.cellSizeWithoutBorder = cellSizeWithoutBorder - 2 * borderAroundCells;
        this.grilleSize = this.numberOfCells * (borderAroundCells + cellSizeWithoutBorder) + this.borderAroundCells;
        this.widthMargin = (widthContainer - this.grilleSize) / 2;
    }

    public float getGrilleSize() {
        return grilleSize;
    }

    public float getCellSizeWithoutBorder() {
        return this.cellSizeWithoutBorder;
    }

    public float getXposition(float x) {
        return widthMargin + x * (this.cellSizeWithoutBorder + this.borderAroundCells) + this.borderAroundCells;
    }

    public float getYposition(float y) {
        return heightM + y * (this.cellSizeWithoutBorder + this.borderAroundCells) + this.borderAroundCells;
    }

    public Rect getTextAjust(String texte, Paint paint) {
        paint.getTextBounds(texte, 0, 1, r);
        return new Rect(0, 0, (int) (this.cellSizeWithoutBorder - 1.5 * this.r.width()) / 2, (int) (this.cellSizeWithoutBorder - this.r.height()) / 2);
    }

    public Cellule getCursorPosition(float positionX, float positionY) {
        if (positionX < this.widthMargin || positionX > this.widthMargin + this.grilleSize || positionY < this.heightM || positionY > this.heightM + this.grilleSize)
            return null;
        positionX = positionX - this.widthMargin;
        positionY = positionY - this.heightM;
        int finalPositionX = (int) (positionX / (this.getCellSizeWithoutBorder() + this.borderAroundCells));
        int finalPositionY = (int) (positionY / (this.getCellSizeWithoutBorder() + this.borderAroundCells));
        return new Cellule(finalPositionX, finalPositionY, finalPositionY * 3 + finalPositionX + 1);
    }
}