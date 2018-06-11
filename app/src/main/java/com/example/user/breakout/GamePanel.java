package com.example.user.breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private ArrayList<GObject> gameObjects;

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }





    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                gameObjects.add(new Ball(120, 120));
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
        gameObjects = new ArrayList<>();
        gameObjects.add(new Ball(200,200));
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
