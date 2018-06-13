package com.example.user.breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.user.breakout.gameobjects.Background;
import com.example.user.breakout.gameobjects.Ball;
import com.example.user.breakout.gameobjects.GObject;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.graphics.AssetManager;
import com.example.user.breakout.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private PlayerPaddle playerPaddle;
    private ArrayList<GObject> gameObjects;



    private Random rand = new Random();

    public GamePanel(Context context){
        super(context);
        Constants.CURRENT_CONTEXT = context;
        AssetManager assetManager = AssetManager.getInstance();
        Level level = new Level();
        playerPaddle = new PlayerPaddle(level);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Background());
        gameObjects.add(playerPaddle); // [0] is the player;
        gameObjects.add(level);
        gameObjects.add(new Ball(level, 200,1200,playerPaddle));
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);



    }





    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(x <= Constants.SCREEN_WIDTH / 2){
                    playerPaddle.controller.setCommand( playerPaddle.moveLeft);
                }
                if(x > Constants.SCREEN_WIDTH/ 2){
                    playerPaddle.controller.setCommand( playerPaddle.moveRight);
                }
                break;
            case MotionEvent.ACTION_UP:
                playerPaddle.controller.getCommand().refresh();
                playerPaddle.controller.setCommand(playerPaddle.moveStop);
                break;

        }
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        // Rendering all gameObjects
        for(GObject gameObj : gameObjects){
            gameObj.draw(canvas);
        }
    }


    public void tick(){
        // Update all gameObjects
        for(GObject gameObj : gameObjects){
            gameObj.tick();
        }
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);

        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            } catch(Exception e){
                e.printStackTrace();;
            }
            retry = false;
        }
    }
}
