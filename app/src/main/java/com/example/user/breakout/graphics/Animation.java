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
    private long lastFrame;

    private Paint paint;

    public void play() { this.isPlaying = true; lastFrame = System.currentTimeMillis(); }
    public void stop() { this.isPlaying = false; frameindex = 0;}



    public Animation(Bitmap[] frames, float animTime){
        this.frames =frames;
        this.paint = new Paint();
        frameindex = 0;

        this.frameTime = animTime / frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, int x, int y){
       if(!isPlaying)
           return;

       canvas.drawBitmap(frames[frameindex], x, y, paint);
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
