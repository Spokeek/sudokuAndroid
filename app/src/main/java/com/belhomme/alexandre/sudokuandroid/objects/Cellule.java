package com.belhomme.alexandre.sudokuandroid.objects;

import java.util.Objects;

public class Cellule {
    private int x;
    private int y;
    private int valeur;
    private boolean readOnly = false;

    public Cellule(int x, int y) {
        this.x = x;
        this.y = y;
        this.valeur = 0;
    }

    public Cellule(int x, int y, int valeur) {
        this.x = x;
        this.y = y;
        this.valeur = valeur;
    }

    public Cellule(int x, int y, int valeur, boolean readOnly) {
        this.x = x;
        this.y = y;
        this.valeur = valeur;
        this.readOnly = readOnly;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getValeur() {
        return valeur;
    }

    public boolean setValeur(int valeur) {
        if (valeur >= 0 && valeur < 10) {
            this.valeur = valeur;
            return true;
        } else
            return false;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
}
