package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Cercle extends Dessiner {
    private int rayon;
    private float posArEnX;
    private float x;
    private float y;
    private int v1;
    private Paint crayon;

    public Cercle(float x, float y, Paint crayon) {
        super();
        this.rayon = 0;
        this.posArEnX = 0;
        this.v1 = 0;
        this.x = x;
        this.y = y;
        this.crayon = crayon;
    }

    public void dessiner(Canvas canvas) {
        canvas.drawCircle(x, y, rayon, crayon);
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    //la fonction redimensionnerCercle() permet de dessiner un cercle quand on bouge la souris vers la droite et vers la gauche
    public void redimensionnerCercle(float posDepEnX, float posSourisX) {

        this.posArEnX = posSourisX;
        if (this.posArEnX > posDepEnX) {//donc je vais a droite
            this.v1 = (int) (posSourisX - posDepEnX);
        }

        //quand je vais a gauche
        else if (this.posArEnX < posDepEnX) {
            this.v1 = ((int) (posSourisX - posDepEnX)) * -1;
        }

        setRayon(this.v1);//modification du rayon

    }

}
