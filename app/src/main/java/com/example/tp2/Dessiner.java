package com.example.tp2;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Dessiner {
    @SuppressLint("ResourceAsColor")
    public Dessiner(){

    }

    public abstract void dessiner(Canvas canvas); //prototype de dessiner

}
