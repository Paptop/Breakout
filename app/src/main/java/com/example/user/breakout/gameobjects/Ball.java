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
        canvas.drawCircle(coord.x, coord.y, radius, paint);
        canvas.drawBitmap(texture,coord.x - radius,coord.y - radius,paint);
    }

    @Override
    public void tick() {
        cornerCollision = false;
        if(coord.x >= (float)Constants.LEVEL_WIDTH|| coord.x < (float)radius ){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            SoundPlayer.playWallSound();
            velocity.x = -velocity.x;
        }
        if( coord.y < (float)Constants.GUI_OFFSET){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            SoundPlayer.playWallSound();
            velocity.y = -velocity.y;
        }
        if( coord.y >= (float)Constants.SCREEN_HEIGHT - 10){
            level.damageToPlayer();
            return;
        }

        double angle = velocity.angle();

        for(int i = 0; i < level.getLevel().size(); i++){
           Paddle p = level.getLevel().get(i);
           if(collision(p)){
              SoundPlayer.playPaddleHitSound();
              if(coord.x == p.coord.x + p.width/2){
                  /*
                  if(angle > 0)
                      velocity.setAngle(- (Math.PI / 6) );
                  else
                      velocity.setAngle((Math.PI / 6) );
                      */
                  velocity.setAngle(-angle);
               }
               else if (coord.x > p.coord.x &&
                       coord.x < p.coord.x + p.width/2){

                  /*
                  if(angle > 0)
                      velocity.setAngle(- (Math.PI) / 8);
                  else
                      velocity.setAngle( (Math.PI) / 8 );
                      */

                  velocity.setAngle(-angle);
               }
               else if (coord.x > p.coord.x + p.width/ 2 &&
                       coord.x < (p.coord.x  + p.width)){
                  /*
                  if(angle > 0)
                      velocity.setAngle(- (Math.PI) / 12);
                  else
                      velocity.setAngle((Math.PI) / 12);
                      */
                  velocity.setAngle(-angle);
               }
               level.getLevel().remove(i);
           }
        }

        if(collision(player)){
            SoundPlayer.playPlayerHitSound();
            if(cornerCollision){
                velocity.setAngle(-(Math.PI - angle));
                coord.x += velocity.x;
                coord.y += velocity.y;
                return;
            }
            /*
            if(angle > 0 && angle < Math.PI / 2)
                velocity.setAngle(-(Math.PI / 2.5));
            else if (angle > 0 && angle > Math.PI / 2)
                velocity.setAngle(-( Math.PI - Math.PI / 2.5));
                */

            switch(player.currentState){
                case MOVING_RIGHT:
                    //if(coord.x > player.getCoord().x && coord.x < player.coord.x + player.width/3){
                        if(angle > 0 && angle <= Math.PI / 2)
                            //velocity.setAngle(- 2 * Math.PI / 3);
                            velocity.setAngle(-Math.PI / 2.7);
                        else if (angle > 0 && angle >= Math.PI / 2)
                            velocity.setAngle(-( Math.PI - Math.PI / 2.5));
                    //}
                    break;

                case MOVING_LEFT:
                    //if(coord.x > player.getCoord().x && coord.x < player.coord.x + player.width/3){
                        if(angle > 0 && angle <= Math.PI / 2)
                            velocity.setAngle(-Math.PI / 2.7);
                        else if (angle > 0 && angle >= Math.PI / 2)
                            //velocity.setAngle(-(Math.PI * 2 / 3));
                            velocity.setAngle(-( Math.PI - Math.PI / 2.5));
                    //}
                    break;
                    /*
                    if(coord.x == player.coord.x + player.width/2){
                        velocity.setAngle(-angle);
                    }
                    else if (coord.x > player.coord.x &&
                        coord.x < player.coord.x + player.width/2){
                        velocity.setAngle(-angle);
                    }
                    else if (coord.x > player.coord.x + player.width/ 2 &&
                        coord.x < (player.coord.x  + player.width)){
                        velocity.setAngle(-angle);
                    }
                    break;
                    */

            }


        }
        // Move the ball
        coord.x += velocity.x;
        coord.y += velocity.y;
    }
}
