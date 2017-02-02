package com.belhomme.alexandre.sudokuandroid.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Grille implements Parcelable {

    private String id;

    private ArrayList<Cellule> grille;

    public Grille(String id, ArrayList<Cellule> grille) {
        this.id = id;
        this.grille = grille;
    }

    public Grille(Parcel in) {
        this.id = in.readString();
        this.grille = in.readArrayList(Cellule.class.getClassLoader());
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

}
