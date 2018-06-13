package com.example.user.breakout.commands;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.PlayerState;

public class PlayerMoveRight implements Command {
   private PlayerPaddle player;
   private float acc = 0;

   public PlayerMoveRight(PlayerPaddle paddle){
       this.player = paddle;
   }

    @Override
    public void execute() {
       if( player.getCoord().x + player.getWidth() < Constants.SCREEN_WIDTH) {
           player.currentState = PlayerState.MOVING_RIGHT;
           player.getCoord().x += 20 + acc;
           acc += 10f;
       }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
