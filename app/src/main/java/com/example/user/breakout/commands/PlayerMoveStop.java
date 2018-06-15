package com.example.user.breakout.commands;

import com.example.user.breakout.gameobjects.PlayerPaddle;

public class PlayerMoveStop implements Command {
    private PlayerPaddle paddle;

    public PlayerMoveStop(PlayerPaddle paddle){
       this.paddle = paddle;
    }

    @Override
    public void execute() {
    }

    @Override
    public void refresh() {

    }
}
