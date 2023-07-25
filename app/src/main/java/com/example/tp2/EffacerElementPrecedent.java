package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Vector;

public class EffacerElementPrecedent extends Dessiner {
    private Vector vecDessiner;
    private Vector<Dessiner> vectorReDessiner;


    public EffacerElementPrecedent(Vector dessiner) {// dessiner contient ce que lon a dessiner dans le vecteur + quand jenleve un rect ca enleve dans dessiner
        super();
        this.vecDessiner = dessiner;
        this.vectorReDessiner = new Vector<>();
        this.vecDessiner.remove(vecDessiner.size() - 1);//on enleve le dernier element pour qu'il n'apparaisse pas quand on redessine
    }


    @Override
    public void dessiner(Canvas canvas) {
        if (this.vecDessiner.size() > 0) {
            this.vectorReDessiner = vecDessiner;
            for (Dessiner dessiner : vectorReDessiner) {
                dessiner.dessiner(canvas);
            }

        }
    }

}
