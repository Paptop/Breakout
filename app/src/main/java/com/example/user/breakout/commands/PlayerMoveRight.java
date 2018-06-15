package com.example.user.breakout.commands;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.PlayerState;
import com.example.user.breakout.sound.SoundPlayer;

public class PlayerMoveRight implements Command {
   private PlayerPaddle player;
   private float acc = 0;

   public PlayerMoveRight(PlayerPaddle paddle){
       this.player = paddle;
   }

    @Override
    public void execute() {
       float index = 20f + acc;
       if( player.getCoord().x + player.getWidth()   < Constants.SCREEN_WIDTH) {
           player.currentState = PlayerState.MOVING_RIGHT;
           player.getCoord().x += 20f + acc;
           // If we acc for too much
           if(player.getCoord().x + player.getWidth() > Constants.SCREEN_WIDTH){
               player.getCoord().x = Constants.SCREEN_WIDTH - player.getWidth();
           }
           acc += 10f;
       }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
