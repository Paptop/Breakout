package com.example.user.breakout.math;

public class Vector2 {
    private float x, y;
    
    public void setPoint(float x, float y){ this.x = x; this.y = y;}
    public void setX(float x) { this.x = x;}
    public void setY(float y) { this.y = y;}
    public float X() { return x;}
    public float Y() { return y;}
    public Vector2(){}
    public Vector2(float x, float y) { this.x = x; this.y = y;}

    /* Arithmetic */
    public void incX(float amount) { this.x += amount;}
    public void decX(float amount) { this.x -= amount;}
    public void multX(float amount) {this.x *= amount;}

    public void incY(float amount) { this.y += amount;}
    public void decY(float amount) { this.y -= amount;}
    public void multY(float amount) {this.y *= amount;}

    /* Neg */
    public void negX() { this.x = -this.x; }
    public void negY() { this.y = -this.y; }
}
