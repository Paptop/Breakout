package com.example.user.breakout.math;

public class Vector2 {
    public float x, y;
    public Vector2(){}
    public Vector2(float x, float y) { this.x = x; this.y = y;}
    public float mag(){  return (float)Math.sqrt((float)Math.pow(x,2) + (float) Math.pow(y,2)); }
    public double angle() { return Math.atan2(y,x); }

    public void setAngle(double angle){
        float length = mag();
        x = (float)(Math.cos(angle) * length);
        y = (float)(Math.sin(angle) * length);
    }

    public void setMag(float mag){
        double angle = angle();
        x = (float)(Math.cos(angle) * mag);
        y = (float)(Math.sin(angle) * mag);
    }



}
