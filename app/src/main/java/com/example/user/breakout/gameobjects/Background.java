package com.example.user.breakout.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.user.breakout.Constants;
import com.example.user.breakout.graphics.AssetManager;

public class Background implements GObject {

    private Bitmap texture;
    private Paint paint;
    private boolean flag = true;

    public Background(String textureId){
        texture = AssetManager.getInstance().getRecource(textureId);
        texture = Bitmap.createScaledBitmap(texture,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,false);
    }

    @Override
    public void draw(Canvas canvas) {
         canvas.drawBitmap(texture, 0, 0, paint);
    }

    @Override
    public void tick() {

    }
}
