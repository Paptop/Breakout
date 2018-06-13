package com.example.user.breakout.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {

    private Bitmap[] frames;
    private int frameindex;
    private boolean isPlaying = false;
    private float frameTime;
    private float lastFrame;

    public void start() { this.isPlaying = true; lastFrame = System.currentTimeMillis();}
    public void stop() { this.isPlaying = false; frameindex = 0;}



    public Animation(Bitmap[] frames, float animTime){
        this.frames =frames;
        frameindex = 0;

        this.frameTime = animTime / frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, Rect destination){
       if(!isPlaying)
           return;

       canvas.drawBitmap(frames[frameindex], null, destination, new Paint());
    }

    public void update(){
        if(!isPlaying) return;
        if(System.currentTimeMillis() - lastFrame > frameTime * 1000){
            frameindex++;
            frameindex %=  frames.length;
            lastFrame = System.currentTimeMillis();
        }
    }
}
