package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Effaceur extends Dessiner {
    private Path path;//car pour Tracer on a beosin d<un path
    private Paint crayon;


    public Effaceur(float x, float y, Paint crayon){
        super();
        this.crayon = crayon;
        this.path = new Path();
        this.path.moveTo(x,y);
    }

    @Override
    public void dessiner(Canvas canvas) {
        canvas.drawPath(this.path,this.crayon);

    }
    public void setCrayon(Paint crayon) {
        this.crayon = crayon;
    }

    public Path getPath() {
        return this.path;
    }

}
