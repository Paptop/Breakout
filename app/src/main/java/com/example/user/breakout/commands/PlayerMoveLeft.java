package com.example.user.breakout.commands;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.PlayerState;
import com.example.user.breakout.sound.SoundPlayer;

public class PlayerMoveLeft implements Command {
    private PlayerPaddle player;
    public float acc = 0f;
    public PlayerMoveLeft(PlayerPaddle paddle){
        this.player = paddle;
    }

    @Override
    public void execute() {
        if(player.getCoord().x > 0) {
            player.currentState = PlayerState.MOVING_LEFT;
            player.getCoord().x -= 20 + acc;
            if(player.getCoord().x < 0){
                player.getCoord().x = 1;
            }
            acc += 10f;
        }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
