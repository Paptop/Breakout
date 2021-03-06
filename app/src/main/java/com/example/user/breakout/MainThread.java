package com.example.user.breakout;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.user.breakout.sound.SoundPlayer;

public class MainThread extends Thread{
    public static final int MAX_FPS = 30;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private boolean paused;
    private double averageFps;
    public Canvas canvas;



    public void setRunning(boolean bool){ this.running = bool; }
    public void setPaused(boolean bool){ this.paused = bool; }
    public boolean isRunning(){ return this.running;}

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    /* Main loop of a game */
    public void run(){
       long starttime;
       long timeMillis = 1000/MAX_FPS;
       long waitTime;
       int frameCount = 0;
       long totalTime = 0;
       long targetTime = 1000/MAX_FPS;

       while(running){
           starttime = System.nanoTime();
           canvas = null;

           try{
              canvas = this.surfaceHolder.lockCanvas();
              synchronized (surfaceHolder){

                  /* Meat of the loop */
                  if(!paused) {
                      this.gamePanel.tick();
                      this.gamePanel.draw(canvas);
                  }
              }
           }catch(Exception e) {
               e.printStackTrace();
           }finally {
               if(canvas != null){
                   try{
                       surfaceHolder.unlockCanvasAndPost(canvas);
                   }catch(Exception e) {e.printStackTrace();}
               }
           }

           /* Force the game loop to run for a specific fps */
           timeMillis = (System.nanoTime() - starttime)/ 1000000;
           waitTime = targetTime - timeMillis;
           try{
               if(waitTime > 0)
                   this.sleep(waitTime);
           }catch (Exception e) { e.printStackTrace(); }
           totalTime += System.nanoTime() - starttime;
           frameCount++;
           if(frameCount == MAX_FPS){
               averageFps = 1000/ ((totalTime/frameCount) / 1000000);
               frameCount = 0;
               totalTime = 0;
               System.out.println(averageFps);
           }
       }

    }
}
