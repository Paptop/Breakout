package com.example.user.breakout.hud;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.user.breakout.gameobjects.GObject;
import com.example.user.breakout.graphics.Animation;
import com.example.user.breakout.graphics.AssetManager;

public class GameOverScreen implements GObject {

    private Animation animation;
    private Bitmap [] anim;
    private Paint paint;

    public GameOverScreen(){
       this.paint = new Paint();
       anim = new Bitmap[2];
       anim[0] = AssetManager.getInstance().getRecource("GameOver1");
       anim[1] = AssetManager.getInstance().getRecource("GameOver2");
       this.animation = new Animation(anim,2f);
       animation.play();
    }

    @Override
    public void draw(Canvas canvas) {
        animation.draw(canvas,0,0);
    }

    @Override
    public void tick() {
        //animation.play();
        animation.update();
    }
}
