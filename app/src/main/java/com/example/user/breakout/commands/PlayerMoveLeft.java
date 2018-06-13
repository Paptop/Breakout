package com.example.user.breakout.commands;

import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.PlayerState;

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
            acc += 10f;
        }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
