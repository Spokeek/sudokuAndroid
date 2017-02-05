package com.belhomme.alexandre.sudokuandroid.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Grille implements Parcelable {

    private String id;

    private ArrayList<Cellule> grille;

    public Grille() {
        this.grille = new ArrayList<Cellule>();
    }

    public Grille(String id, ArrayList<Cellule> grille) {
        this.id = id;
        this.grille = grille;
    }

    public Grille(Parcel in) {
        this.id = in.readString();
        this.grille = in.readArrayList(Cellule.class.getClassLoader());
    }

    public void add(Cellule newCellule) {
        Cellule c = this.find(newCellule.getX(), newCellule.getY());
        if (c != null)
            this.grille.remove(c);
        this.grille.add(newCellule);
    }

    public Cellule find(int x, int y) {
        Cellule cellule = null;
        for (Cellule c : this.grille) {
            if (c.getX() == x && c.getY() == y) {
                cellule = c;
                break;
            }
        }
        return cellule;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeList(this.grille);
    }

    public static final Parcelable.Creator<Grille> CREATOR = new Parcelable.Creator<Grille>() {
        @Override
        public Grille createFromParcel(Parcel source) {
            return new Grille(source);
        }

        @Override
        public Grille[] newArray(int size) {
            return new Grille[size];
        }
    };

    public boolean canAdd(Cellule cellule) {
        if (cellule.getValeur() < 1 || cellule.getValeur() > 9)
            return false;
        Cellule existingCell = this.find(cellule.getX(), cellule.getY());
        if (existingCell == null)
            return true;
        else if (existingCell.isReadOnly() || existingCell.getValeur() == cellule.getValeur())
            return false;
        else {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if (this.find(x, cellule.getY()).getValeur() == cellule.getValeur() || this.find(cellule.getX(), y).getValeur() == cellule.getValeur()) {
                        return false;
                    }
                }
            }
            int marginY = (int) ((cellule.getY() / 3)) * 3;
            int marginX = (int) ((cellule.getX() / 3)) * 3;
            for (int y = marginY; y < marginY + 3; y++) {
                for (int x = marginX; x < marginX + 3; x++) {
                    Cellule c = this.find(x, y);
                    if (c.getValeur() == cellule.getValeur())
                        return false;
                }
            }
        }
        return true;
    }
}
