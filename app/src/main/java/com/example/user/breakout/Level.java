package com.example.user.breakout;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class Level implements GObject {
    protected ArrayList<Paddle> paddles;
    protected int width, height;

    public Random rand;
    public ArrayList<Paddle> getLevel() { return paddles;}

    public Level(){
        this.width = Constants.SCREEN_WIDTH / Constants.DEFAULT_PADDLE_WIDTH - 1;
        this.height = Constants.DEFAULT_LEVEL_HEIGHT + 5;
        this.rand = new Random();
        //this.height = Constants.SCREEN_HEIGHT / Constants.DEFAULT_PADDLE_HEIGHT;
        this.paddles = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            if(i > Constants.DEFAULT_LEVEL_HEIGHT){
                for(int j = 0; j < width; j += 3){
                    paddles.add(new Paddle(this, j * (Constants.DEFAULT_PADDLE_WIDTH + 10) + Constants.DEFAULT_PADDLE_WIDTH / 2,
                            i * (Constants.DEFAULT_PADDLE_HEIGHT + 10), 255, rand.nextInt(255), rand.nextInt(255),
                            rand.nextInt(255)));
                }
            }else {
                for (int j = 0; j < width; j++) {
                    paddles.add(new Paddle(this, j * (Constants.DEFAULT_PADDLE_WIDTH + 10) + Constants.DEFAULT_PADDLE_WIDTH / 2,
                            i * (Constants.DEFAULT_PADDLE_HEIGHT + 10), 255, rand.nextInt(255), rand.nextInt(255),
                            rand.nextInt(255)));
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(Paddle p : paddles){
            p.draw(canvas);
        }
    }

    @Override
    public void tick() {

    }
}
