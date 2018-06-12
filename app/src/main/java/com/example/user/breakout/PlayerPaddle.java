package com.example.user.breakout;

import android.graphics.Canvas;

public class PlayerPaddle extends Paddle {

    public CommandController controller = new CommandController();

    public Command moveRight = new PlayerMoveRight(this);
    public Command moveLeft = new PlayerMoveLeft(this);
    public Command moveStop  = new PlayerMoveStop(this);


    public PlayerState currentState;

    public PlayerPaddle(Level level){
        super(level,Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 100,
                Constants.DEFAULT_PLAYER_PADDLE_WIDTH,
                Constants.DEFAULT_PLAYER_PADDLE_HEIGHT);
        controller.setCommand(moveStop);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(coord.x,coord.y,coord.x + width, coord.y + height, paint);
    }

    @Override
    public void tick() {
        controller.pressButton();
    }
}
