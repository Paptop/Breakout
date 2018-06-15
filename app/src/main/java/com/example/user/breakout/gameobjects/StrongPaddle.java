package com.example.user.breakout.gameobjects;

import com.example.user.breakout.Constants;
import com.example.user.breakout.level.Level;

public class StrongPaddle extends  Paddle{

    private int collisionCount = 0;

    @Override
    public boolean onCollision(){
        collisionCount++;
        if(collisionCount == 2){ return true; }
        return  false;
    }

    public StrongPaddle(Level level, int x, int y, String texture) {
        super(level,x,y,texture);
        this.type = PaddleType.STRONG;
        this.width = Constants.DEFAULT_PLAYER_PADDLE_WIDTH;
        this.height = Constants.DEFAULT_PADDLE_HEIGHT;
    }

}
