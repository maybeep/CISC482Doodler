package com.example.cisc482doodler;

import android.graphics.Path;

public class Brush {
    public String color;
    public int strokeWeight;
    public int strokeAlpha;
    public Path path;

    public Brush(String color, int strokeWidth, int strokeAlpha, Path path) {
        this.color = color;
        this.strokeWeight = strokeWidth;
        this.strokeAlpha = strokeAlpha;
        this.path = path;
    }
}
