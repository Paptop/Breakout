package com.example.user.breakout.math;

public class GMath {
    public static float normalise(float value, float min, float max){
        return (value - min) / (max - min);
    }
}
