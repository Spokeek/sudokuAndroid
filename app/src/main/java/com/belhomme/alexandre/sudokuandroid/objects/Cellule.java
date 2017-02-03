package com.belhomme.alexandre.sudokuandroid.objects;

import java.util.Objects;

public class Cellule {
    private int x;
    private int y;
    private int valeur;

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
}
