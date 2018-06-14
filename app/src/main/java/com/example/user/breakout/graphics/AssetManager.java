package com.example.user.breakout.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.user.breakout.Constants;
import com.example.user.breakout.R;

import java.util.HashMap;

// Singleton

public class AssetManager {

    private static AssetManager instance;
    private HashMap<String,Bitmap> allResources;
    private Bitmap paddleAssetA;
    private Bitmap paddleAssetPlayer;


    public Bitmap getRecource(String key) {
        Bitmap png = allResources.get(key);
        if(png == null){
            return allResources.get("PaddleRed");
        }
        return allResources.get(key);
    }

    public static AssetManager getInstance() {
        if(instance != null) return instance;
        instance = new AssetManager();
        return instance;
    }



    private AssetManager(){
        allResources = new HashMap<>();
        BitmapFactory  bf = new BitmapFactory();
        // Backgrounds
        Bitmap background = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.marsbg);
        Bitmap guibg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.guibg);
        // Player and objects
        Bitmap player = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.paddleplayer);
        Bitmap paddlered = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paddlered);
        Bitmap paddlegreen = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paddlegreen);
        Bitmap paddleblue = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paddleblue);
        Bitmap paddlegold = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paddlegold);
        Bitmap paddlepurple = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paddlepurple);

        Bitmap pred = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pred);
        Bitmap pgreen = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pgreen);
        Bitmap pblue = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pblue);
        Bitmap pgold = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.pgold);
        Bitmap ppurple = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ppurple);
        Bitmap ball = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ball);

        // HUD
        Bitmap health1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.health1);
        Bitmap health2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.health2);
        Bitmap health3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.health3);
        Bitmap health4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.health4);


        allResources.put("MarsBg",Bitmap.createScaledBitmap(background, Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT - Constants.GUI_OFFSET, false));

        // Player
        allResources.put("DefaultPlayer", Bitmap.createScaledBitmap(player,Constants.DEFAULT_PLAYER_PADDLE_WIDTH,
                Constants.DEFAULT_PLAYER_PADDLE_HEIGHT, false));
        // Paddles
        allResources.put("PaddleRed", Bitmap.createScaledBitmap(paddlered,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PaddleBlue", Bitmap.createScaledBitmap(paddleblue,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PaddleGreen", Bitmap.createScaledBitmap(paddlegreen,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PaddlePurple", Bitmap.createScaledBitmap(paddlepurple,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PaddleGold", Bitmap.createScaledBitmap(paddlegold,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));

        allResources.put("PRed", Bitmap.createScaledBitmap(pred,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PBlue", Bitmap.createScaledBitmap(pblue,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PGreen", Bitmap.createScaledBitmap(pgreen,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PPurple", Bitmap.createScaledBitmap(ppurple,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        allResources.put("PGold", Bitmap.createScaledBitmap(pgold,
                Constants.DEFAULT_PADDLE_WIDTH,
                Constants.DEFAULT_PADDLE_HEIGHT,
                false));
        // Ball
        allResources.put("Ball", Bitmap.createScaledBitmap(ball,
                Constants.BALL_RADIUS * 2,
                Constants.BALL_RADIUS * 2,
                false));

        // Hud

        allResources.put("Health1", Bitmap.createScaledBitmap(health1,
                Constants.GUI_HIT_POINTS_WIDTH,
                Constants.GUI_HIT_POINTS_HEIGHT,
                false));

        allResources.put("Health2", Bitmap.createScaledBitmap(health2,
                Constants.GUI_HIT_POINTS_WIDTH,
                Constants.GUI_HIT_POINTS_HEIGHT,
                false));

        allResources.put("Health3", Bitmap.createScaledBitmap(health3,
                Constants.GUI_HIT_POINTS_WIDTH,
                Constants.GUI_HIT_POINTS_HEIGHT,
                false));

        allResources.put("Health4", Bitmap.createScaledBitmap(health4,
                Constants.GUI_HIT_POINTS_WIDTH,
                Constants.GUI_HIT_POINTS_HEIGHT,
                false));

        allResources.put("GuiBg", Bitmap.createScaledBitmap(guibg,
                Constants.SCREEN_WIDTH,
                Constants.GUI_OFFSET,
                false));
        /*
        allResources.put("DefaultBall0",
                Bitmap.createScaledBitmap(Bitmap.createBitmap(paddleAssetPlayer,0,0,32,32),
                        Constants.BALL_RADIUS * 2,
                        Constants.BALL_RADIUS * 2,true));
                        */
    }
}
