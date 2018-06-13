package com.example.user.breakout.gameobjects;

import android.graphics.Canvas;

public interface GObject {
    void draw(Canvas canvas);
    void tick();
}
