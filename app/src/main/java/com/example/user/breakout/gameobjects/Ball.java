package com.example.user.breakout.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.user.breakout.Constants;
import com.example.user.breakout.graphics.AssetManager;
import com.example.user.breakout.level.Level;
import com.example.user.breakout.math.Vector2;
import com.example.user.breakout.sound.SoundPlayer;

import java.util.Random;

public class Ball implements GObject {

    public Vector2 coord;
    public Vector2 velocity;
    private Paint paint;
    private Paint boundsPaint;
    private int radius;
    private Level level;
    private PlayerPaddle player;
    private Bitmap texture;
    private int collisionCount = 0;

    private boolean cornerCollision ;

    public Ball(Level level, int x, int y, PlayerPaddle player){
        this(level);
        coord.x = x; coord.y = y;
        this.player = player;
    }

    public Ball(Level level){
        paint = new Paint();
        boundsPaint = new Paint();
        paint.setARGB(255,0,255,0);
        paint.setStyle(Paint.Style.STROKE);
        radius = Constants.BALL_RADIUS;
        coord = new Vector2();
        velocity = new Vector2();
        velocity.x = 20f ;velocity.y = 20f ;
        this.level = level;
        texture = AssetManager.getInstance().getRecource("Ball");

    }


    public boolean collision(Paddle paddle){
        float disX = Math.abs(coord.x - (paddle.getCoord().x + paddle.width/2));
        float disY = Math.abs(coord.y - (paddle.getCoord().y + paddle.height/2));

        if(disX > (paddle.width/2 + radius) ) return false;
        if(disY > (paddle.height/2 + radius)) return false;

        if(disX <= (paddle.width /2)) return true;
        if(disY <= (paddle.height/2)) return true;

        float cornerDis = (float)Math.pow( (disX - paddle.width/2), 2) + (float)Math.pow( (disY - paddle.height/2), 2);
        if(cornerDis <= (float)Math.pow(radius,2)) cornerCollision = true;
        else cornerCollision = false;

        return cornerCollision;
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawCircle(coord.x, coord.y, radius + 10, boundsPaint);
        //canvas.drawCircle(coord.x, coord.y, radius, paint);
        canvas.drawBitmap(texture,coord.x - radius,coord.y - radius,paint);
    }


    @Override
    public void tick() {

        if(coord.x > (float)Constants.LEVEL_WIDTH - radius || coord.x < (float)radius ){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            SoundPlayer.playWallSound();
            velocity.x = -velocity.x;
        }
        if( coord.y < (float)Constants.GUI_OFFSET + radius){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            SoundPlayer.playWallSound();
            velocity.y = -velocity.y;
        }
        if( coord.y >= (float)Constants.SCREEN_HEIGHT - 10){
            level.damageToPlayer();
            return;
        }

        double angle = velocity.angle();
        
        // Check collision for all paddles in the level
        // Using naive approach
        for(int i = 0; i < level.getLevel().size(); i++){
           Paddle p = level.getLevel().get(i);
           cornerCollision = false;
           if(collision(p)){
              collisionCount++;
              // increase speed over time
              if(collisionCount % 3 == 0)
                  velocity.setMag( velocity.mag() + Constants.SPEED_INCREASE);
              SoundPlayer.playPaddleHitSound();
              player.incScore(1);
              level.playerScored();
              // Special case corner
              if(cornerCollision){
                  velocity.setAngle(-(Math.PI - angle));
                  coord.x += velocity.x;
                  coord.y += velocity.y;
                  level.onCollision(p,i);
                  return;
              }
              if(coord.x == p.coord.x + p.width/2){
                  velocity.setAngle(-angle);
               }
               else if (coord.x > p.coord.x &&
                       coord.x < p.coord.x + p.width/2){

                  velocity.setAngle(-angle);
               }
               else if (coord.x > p.coord.x + p.width/ 2 &&
                       coord.x < (p.coord.x  + p.width)){

                  velocity.setAngle(-angle);
               }
               level.onCollision(p,i);
           }
        }
        //Check collision with the player
        cornerCollision = false;
        if(collision(player)){
            SoundPlayer.playPlayerHitSound();
            // Ball direction left or right false means right, true left
            boolean direction = false;
            if(angle > 0 && angle <= Math.PI / 2)
                direction = false;
            else
                direction = true;
            if(cornerCollision){
                if(!direction) {
                    velocity.setAngle(-(Math.PI - angle));
                }else{
                    velocity.setAngle(-(Math.PI - angle));
                }
                coord.x += velocity.x;
                coord.y += velocity.y;
                return;
            }
/*
            if(angle > 0 && angle < Math.PI / 2)
                velocity.setAngle(-(Math.PI / 2.5));
            else if (angle > 0 && angle > Math.PI / 2)
                velocity.setAngle(-( 5 * Math.PI / 6));
                */


            switch(player.currentState) {
                case MOVING_RIGHT:
                    if (coord.x > player.getCoord().x && coord.x < player.coord.x + player.width / 3) {
                        //velocity.setAngle(- 2 * Math.PI / 3);
                        //velocity.setAngle(-Math.PI / 2.3);
                        if (!direction) {
                            velocity.setAngle(-(Math.PI / 3));
                        } else {
                            velocity.setAngle(-(Math.PI * 2 / 2.3));
                        }
                    } else if (coord.x > player.coord.x + player.width / 3 &&
                            coord.x < ((player.coord.x + player.width) * 2) / 3) {
                        if (!direction) {
                            velocity.setAngle(-angle);
                        } else {
                            velocity.setAngle(-(2 * Math.PI / 2.2));
                        }

                    } else if (coord.x > ((player.coord.x + player.width) * 2) / 3 &&
                            coord.x < (player.coord.x + player.width)) {
                        if (!direction) {
                            velocity.setAngle(-Math.PI / 6);
                        } else {
                            //velocity.setAngle(-( Math.PI - Math.PI / 2.5));
                            velocity.setAngle(-(Math.PI / 2.2));
                        }
                    }

                    break;

                case MOVING_LEFT:
                    if (coord.x > player.getCoord().x && coord.x < player.coord.x + player.width / 3) {
                        if (!direction) {
                            velocity.setAngle(-(Math.PI / 2.5));
                        } else {
                            velocity.setAngle(-(5 * Math.PI / 6));
                        }
                    } else if (coord.x > player.coord.x + player.width / 3 &&
                            coord.x < ((player.coord.x + player.width) * 2) / 3) {
                        if (!direction) {
                            velocity.setAngle(-(Math.PI / 2.3));
                        } else {
                            velocity.setAngle(-angle);
                        }
                    } else if (coord.x > ((player.coord.x + player.width) * 2) / 3 &&
                            coord.x < (player.coord.x + player.width)) {
                        if (!direction) {
                            velocity.setAngle(-(Math.PI / 2.3));
                        } else {
                            velocity.setAngle(-(Math.PI * 2 / 3));
                        }
                    }
                    break;
            }



        }
        // Move the ball
        coord.x += velocity.x;
        coord.y += velocity.y;
    }
}
