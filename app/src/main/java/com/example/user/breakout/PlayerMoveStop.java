package com.example.user.breakout;

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
