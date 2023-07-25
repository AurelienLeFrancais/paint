package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Vector;

public class AjouterElement extends Dessiner {
    private Vector<Dessiner> vecAvecTout;
    private Vector vecDessiner;
    private Dessiner d;

    public AjouterElement(Vector vecteurContenantTout, Vector dessiner) {
        super();
        this.vecAvecTout = vecteurContenantTout; //a toutes les formes dessinees meme celles supprimees
        this.vecDessiner = dessiner; //a le nombre de rectangle mis a jour ex : 1
    }

    @Override
    public void dessiner(Canvas canvas) {

        if (this.vecDessiner.size() < this.vecAvecTout.size()) {
            this.d = this.vecAvecTout.get(this.vecDessiner.size());
            this.vecDessiner.add(this.d);//je re remplis le vecteur dessiner
            this.d.dessiner(canvas);//jaffiche
        }

    }

}
