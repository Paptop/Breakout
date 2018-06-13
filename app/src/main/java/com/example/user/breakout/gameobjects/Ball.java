package com.example.user.breakout.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.user.breakout.Constants;
import com.example.user.breakout.graphics.AssetManager;
import com.example.user.breakout.level.Level;
import com.example.user.breakout.math.Vector2;

import java.util.Random;

public class Ball implements GObject {

    private Vector2 coord;
    private Vector2 velocity;
    private float speed = 50.0f;
    private Paint paint;
    private Paint boundsPaint;
    private Random rand = new Random();
    private int radius;

    private Level level;
    private PlayerPaddle player;

    private Bitmap texture;

    public Ball(Level level, int x, int y, PlayerPaddle player){
        this(level);
        coord.x = x; coord.y = y;
        this.player = player;
    }

    public Ball(Level level){
        paint = new Paint();
        boundsPaint = new Paint();
        paint.setARGB(255,255,0,0);
        boundsPaint.setARGB(255,255,255,255);
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
        return (cornerDis <= (float)Math.pow(radius,2));
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawCircle(coord.x, coord.y, radius + 10, boundsPaint);
        //canvas.drawCircle(coord.x, coord.y, radius, paint);
        canvas.drawBitmap(texture,coord.x,coord.y,paint);
    }

    @Override
    public void tick() {

        if(coord.x >= (float)Constants.SCREEN_WIDTH  || coord.x < (float)radius ){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity.x = -velocity.x;
        }
        if(coord.y >= (float)Constants.SCREEN_HEIGHT || coord.y < (float)radius ){
            //paint.setARGB(255,rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            velocity.y = -velocity.y;
        }

        double angle = velocity.angle();

        for(int i = 0; i < level.getLevel().size(); i++){
           Paddle p = level.getLevel().get(i);
           if(collision(p)){
              if(coord.x == p.coord.x + p.width/2){
                  if(angle > 0)
                      velocity.setAngle(- (Math.PI / 6) );
                  else
                      velocity.setAngle((Math.PI / 6) );
               }
               else if (coord.x > p.coord.x &&
                       coord.x < p.coord.x + p.width/2){


                  if(angle > 0)
                      velocity.setAngle(- (Math.PI) / 8);
                  else
                      velocity.setAngle( (Math.PI) / 8 );

               }
               else if (coord.x > p.coord.x + p.width/ 2 &&
                       coord.x < (p.coord.x  + p.width)){

                  if(angle > 0)
                      velocity.setAngle(- (Math.PI) / 12);
                  else
                      velocity.setAngle((Math.PI) / 12);
               }
               level.getLevel().remove(i);
           }
        }

        if(collision(player)){

            switch(player.currentState){
                case MOVING_LEFT:
                    if(coord.x == player.coord.x + player.width/2){
                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 4);
                        else
                            velocity.setAngle((Math.PI) / 4);
                    }
                    else if (coord.x > player.coord.x &&
                        coord.x < player.coord.x + player.width/2){

                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 6);
                        else
                            velocity.setAngle((Math.PI) / 6);
                    }
                    else if (coord.x > player.coord.x + player.width/ 2 &&
                        coord.x < (player.coord.x  + player.width)){

                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 2.5);
                        else
                            velocity.setAngle((Math.PI) / 2.5);
                    }
                    break;

                case MOVING_RIGHT:
                    if(coord.x == player.coord.x + player.width/2){
                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 2.5);
                        else
                            velocity.setAngle(Math.PI / 2.5);
                    }
                    else if (coord.x > player.coord.x &&
                        coord.x < player.coord.x + player.width/2){

                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 4);
                        else
                            velocity.setAngle(Math.PI / 4);
                    }
                    else if (coord.x > player.coord.x + player.width/ 2 &&
                        coord.x < (player.coord.x  + player.width)){

                        if(angle > 0)
                            velocity.setAngle(-(Math.PI) / 10);
                        else
                            velocity.setAngle(Math.PI / 10);
                    }
                    break;

            }


        }
        // Move the ball
        coord.x += velocity.x;
        coord.y += velocity.y;
    }
}
