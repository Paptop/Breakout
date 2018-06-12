package com.example.user.breakout;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

import com.example.user.breakout.math.Vector2;

public class Paddle implements GObject {
    protected Vector2 coord;
    protected float width, height;
    protected Paint paint;
    protected Level level;

    public Vector2 getCoord() { return coord; }

    public void setCoord(Vector2 coord) { this.coord = coord; }

    public float getWidth() { return width; }

    public void setWidth(float width) { this.width = width; }

    public float getHeight() { return height; }

    public void setHeight(float height) { this.height = height; }

    public Paint getPaint() { return paint; }

    public void setPaint(Paint paint) { this.paint = paint; }

    public Paddle(Level level){
        coord = new Vector2();
        paint = new Paint();
        paint.setARGB(255,0,255,0);
        width = Constants.DEFAULT_PADDLE_WIDTH;
        height = Constants.DEFAULT_PADDLE_HEIGHT;
        this.level = level;
    }

    public Paddle(Level level, int x, int y){
       this(level);
       coord.x = x; coord.y = y;
    }

    public Paddle(Level level, int x, int y, int width, int height){
       this(level);
       coord.x = x; coord.y = y;
       this.width = width; this.height = height;
    }

    public Paddle(Level level, int x, int y, int a, int r, int g, int b){
       this(level);
       coord.x = x; coord.y = y;
       paint.setARGB(a,r,g,b);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(coord.x,coord.y,coord.x + width, coord.y + height, paint);
    }

    @Override
    public void tick() {

    }
}
