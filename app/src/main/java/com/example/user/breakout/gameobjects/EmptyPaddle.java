package com.example.user.breakout.gameobjects;

import android.graphics.Canvas;

import com.example.user.breakout.level.Level;

public class EmptyPaddle extends Paddle {

    public EmptyPaddle(Level level) {
        super(level);
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void tick(){
    }

}
