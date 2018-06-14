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
import com.example.user.breakout.sound.SoundPlayer;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private SoundPlayer soundPlayer;
    private PlayerPaddle playerPaddle;
    private Level level;

    public MainThread getMainThread() { return thread; }

    public GamePanel(Context context){
        super(context);
        Constants.CURRENT_CONTEXT = context;
        AssetManager.getInstance();
        level = new Level();
        playerPaddle = level.getPlayer();
        soundPlayer = new SoundPlayer();
        getHolder().addCallback(this);
       // thread = new MainThread(getHolder(), this);
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
        level.draw(canvas);
    }


    public void tick(){
        // Update all gameObjects
        level.tick();
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        /*
        if(thread == null) {
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }else {
            if(!thread.isRunning()) {
                thread.setRunning(true);
                thread.start();
            }
            */

        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try{
            thread.setRunning(false);
            thread.join();
        } catch(Exception e){
             e.printStackTrace();
        }
    }
}
