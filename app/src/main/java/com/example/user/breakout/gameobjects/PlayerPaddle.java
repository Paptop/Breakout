package com.example.user.breakout.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.user.breakout.Constants;
import com.example.user.breakout.graphics.Animation;
import com.example.user.breakout.graphics.AssetManager;
import com.example.user.breakout.level.Level;
import com.example.user.breakout.PlayerState;
import com.example.user.breakout.commands.Command;
import com.example.user.breakout.commands.CommandController;
import com.example.user.breakout.commands.PlayerMoveLeft;
import com.example.user.breakout.commands.PlayerMoveRight;
import com.example.user.breakout.commands.PlayerMoveStop;
import com.example.user.breakout.sound.SoundPlayer;

public class PlayerPaddle extends Paddle {

    public CommandController controller = new CommandController();

    public Command moveRight = new PlayerMoveRight(this);
    public Command moveLeft = new PlayerMoveLeft(this);
    public Command moveStop  = new PlayerMoveStop(this);

    public PlayerState currentState = PlayerState.MOVING_STOP;

    private int hitpoints = 4;
    public int getHitpoints() { return hitpoints; }

    public boolean damagePlayer(){
        hitpoints--;
        if(hitpoints == 0) return true;
        else return false;
    }

    public void healPlayer(){
        if(hitpoints < 4)
        hitpoints++;
    }


    public Bitmap texture;
    public PlayerPaddle(Level level){
        super(level, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 100,
                Constants.DEFAULT_PLAYER_PADDLE_WIDTH,
                Constants.DEFAULT_PLAYER_PADDLE_HEIGHT);
        controller.setCommand(moveStop);
        texture = AssetManager.getInstance().getRecource("DefaultPlayer");
    }


    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(coord.x,coord.y,coord.x + width, coord.y + height, paint);
        canvas.drawBitmap(texture,coord.x, coord.y,paint);
    }

    @Override
    public void tick() {
        controller.pressButton();
    }
}
