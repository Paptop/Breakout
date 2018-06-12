package com.example.user.breakout;

public class PlayerMoveLeft implements Command{
    private PlayerPaddle player;
    public float acc = 0f;
    public PlayerMoveLeft(PlayerPaddle paddle){
        this.player = paddle;
    }

    @Override
    public void execute() {
        if(player.coord.x > 0) {
            player.currentState = PlayerState.MOVING_LEFT;
            player.coord.x -= 20 + acc;
            acc += 10f;
        }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
