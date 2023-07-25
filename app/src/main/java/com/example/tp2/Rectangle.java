package com.example.tp2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Dessiner {
    private float arriveCoinSupX;
    private float arriveCoinSupY;
    private float x;
    private float y;
    private Paint crayon;

    public Rectangle(float x, float y, Paint crayon) {
        super();
        this.arriveCoinSupX = 0;
        this.arriveCoinSupY = 0;
        this.x = x;
        this.y = y;
        this.crayon = crayon;
    }

    @Override
    public void dessiner(Canvas canvas) {
        canvas.drawRect(x, y, arriveCoinSupX, arriveCoinSupY, crayon);
    }

    public void setArriveCoinSupX(float arriveCoinSupX) {
        this.arriveCoinSupX = arriveCoinSupX;
    }

    public void setArriveCoinSupY(float arriveCoinSupY) {
        this.arriveCoinSupY = arriveCoinSupY;
    }

    public void redimensionnerRectangle(float eventX, float eventY) {

        this.arriveCoinSupX = eventX;
        this.arriveCoinSupY = eventY;
        setArriveCoinSupX(arriveCoinSupX);//modification arriveX
        setArriveCoinSupY(arriveCoinSupY);//modification arriveY

    }


}
