package com.belhomme.alexandre.sudokuandroid.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Grille implements Parcelable {

    private String id;

    private int[][] grille;

    public Grille(String id, int[][] grille) {
        this.id = id;
        this.grille = grille;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeArray(grille);
    }
}
