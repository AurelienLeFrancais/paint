package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Tracer extends Dessiner {
    private Path path;
    private Paint crayon;


    public Tracer(float x, float y, Paint crayon){
        super();
        this.crayon = crayon;
        this.path = new Path();
        this.path.moveTo(x,y);
    }

    @Override
    public void dessiner(Canvas canvas) {
        canvas.drawPath(this.path,this.crayon);
    }

    public Path getPath() {
        return this.path;
    }

}
