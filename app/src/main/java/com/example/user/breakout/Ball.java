package com.example.user.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.user.breakout.math.Vector2;

import java.util.Random;

public class Ball implements GObject {

    private Vector2 coord;
    private Vector2 speed;
    private Paint paint;
    private Random rand = new Random();
    private int radius;

    public Ball(int x, int y){
        this();
        coord.setPoint(x ,y);
    }

    public Ball(){
        paint = new Paint();
        paint.setARGB(255,255,0,0);
        radius = 100;
        coord = new Vector2();
        speed = new Vector2();
        speed.setX(10); speed.setY(10);

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(coord.X(), coord.Y(), radius, paint);
    }

    @Override
    public void tick() {
        if(coord.X() >= Constants.SCREEN_WIDTH - radius || coord.X() < radius){
            paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            speed.negX();
        }
        if(coord.Y() >= Constants.SCREEN_HEIGHT - radius || coord.Y() < radius){
            paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            speed.negY();
        }

        // Move the ball
        coord.incX(speed.X());
        coord.incY(speed.Y());
    }
}
