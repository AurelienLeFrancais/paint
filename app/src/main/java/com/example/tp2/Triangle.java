package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends Dessiner {
    private float x1;
    private float y1;
    private float stopX, stopY;
    private Paint crayon;
    private Path trianglePath;

    public Triangle(float x1, float y1, Paint crayon) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.crayon = crayon;
        this.crayon.setStrokeCap(Paint.Cap.ROUND); //pour arrondir les sommets du triangle
        this.trianglePath = new Path();
        this.trianglePath.moveTo(x1, y1);//move au 1er sommet
    }

    @Override
    public void dessiner(Canvas canvas) {
        canvas.drawLine(x1, y1, stopX, stopY, crayon);
        canvas.drawPath(trianglePath, crayon);
    }

    public void pointArrivePremierTrait(float x, float y) {//mettre la valeur de  x et y
        this.stopX = x;
        this.stopY = y;

    }

    public void dessineDeuxiemeTrait(float movetoX, float moveToY, float lineToX, float linteToY) {//movetoX = fin du 1er trait / line to: sommet 3
        this.trianglePath.moveTo(movetoX, moveToY);//pars de la fin du 1er trait
        this.trianglePath.lineTo(lineToX, linteToY);//va au sommet 3 et dessine
        this.trianglePath.moveTo(lineToX, linteToY);//pars du sommet 3
        this.trianglePath.lineTo(this.x1, this.y1); //va au sommet 1
    }

}
