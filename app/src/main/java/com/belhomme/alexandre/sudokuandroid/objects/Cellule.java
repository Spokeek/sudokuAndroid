package com.belhomme.alexandre.sudokuandroid.objects;

public class Cellule {
    private int x;
    private int y;
    private int valeur;

    public Cellule(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return this.valeur;
    }
}
